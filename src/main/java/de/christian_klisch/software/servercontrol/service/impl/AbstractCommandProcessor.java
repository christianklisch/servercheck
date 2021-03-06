package de.christian_klisch.software.servercontrol.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.IOptionName;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;
import net.neoremind.sshxcute.task.impl.ExecShellScript;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import de.christian_klisch.software.servercontrol.config.Configuration;
import de.christian_klisch.software.servercontrol.model.Command;
import de.christian_klisch.software.servercontrol.model.CommandView;
import de.christian_klisch.software.servercontrol.model.Task;

/**
 * Processor for abstract commands
 * 
 * @author Christian Klisch
 * 
 *         License:
 * 
 *         This program is free software; you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License version 2 as
 *         published by the Free Software Foundation.
 * 
 *         This program is distributed in the hope that it will be useful, but
 *         WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *         General Public License for more details.
 * 
 *         You should have received a copy of the GNU General Public License
 *         along with this program; if not, write to the Free Software
 *         Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
public class AbstractCommandProcessor implements Configuration {

    protected void execute(Task task) {
	Command v = (Command) task;
	String returnvalue = "";
	long timestamp = new Date().getTime();

	try {
	    java.lang.Process p = null;
	    if (((CommandView) v).getTargetOS().equals(LINOS)) {
		FileUtils.writeStringToFile(new File(SCRIPTDIR + timestamp + SCRIPTFILELIN), v.getCommand());
		if (((CommandView) v).getSshserver() != null && !((CommandView) v).getSshserver().isEmpty()) {
		    returnvalue = this.executeOnSSH((Command) v, timestamp + SCRIPTFILELIN);
		    new File(SCRIPTDIR + timestamp + SCRIPTFILELIN).delete();
		} else {
		    Runtime.getRuntime().exec("chmod 775 " + SCRIPTDIR + timestamp + SCRIPTFILELIN);
		    p = Runtime.getRuntime().exec(SCRIPTDIR + timestamp + SCRIPTFILELIN);
		    p.waitFor();
		    new File(SCRIPTDIR + timestamp + SCRIPTFILELIN).delete();
		}
	    }
	    if (((CommandView) v).getTargetOS().equals(WINOS)) {
		FileUtils.writeStringToFile(new File(SCRIPTDIR + timestamp + SCRIPTFILEWIN), v.getCommand());
		if (((CommandView) v).getSshserver() != null && !((CommandView) v).getSshserver().isEmpty()) {
		    returnvalue = this.executeOnSSH((Command) v, timestamp + SCRIPTFILEWIN);
		    new File(SCRIPTDIR + timestamp + SCRIPTFILEWIN).delete();
		} else {
		    p = Runtime.getRuntime().exec(SCRIPTDIR + timestamp + SCRIPTFILEWIN);
		    p.waitFor();
		    new File(SCRIPTDIR + timestamp + SCRIPTFILEWIN).delete();
		}
	    }

	    if (p != null) {
		returnvalue = IOUtils.toString(p.getInputStream(), "UTF-8");
		if (returnvalue != null) {
		    returnvalue = returnvalue.trim();
		    returnvalue = returnvalue.replaceAll("[\\r\\n]", "");
		}
	    }
	    if (returnvalue != null && !returnvalue.isEmpty()) {
		returnvalue = returnvalue.trim();
		returnvalue = returnvalue.replaceAll("[\\r\\n]", "");
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	v.setLastExecute(new GregorianCalendar());
	v.setLastResult(returnvalue);
    }

    protected String executeOnSSH(Command s, String scriptname) {

	String returnvalue = "";
	ConnBean cb = new ConnBean(s.getSshserver(), s.getSshuser(), s.getSshpassword());
	SSHExec ssh = SSHExec.getInstance(cb);
	SSHExec.setOption(IOptionName.HALT_ON_FAILURE, false);
	SSHExec.setOption(IOptionName.INTEVAL_TIME_BETWEEN_TASKS, 500l);
	SSHExec.setOption(IOptionName.TIMEOUT, 36000l);
	ssh.connect();

	try {
	    // upload
	    ssh.uploadSingleDataToServer(SCRIPTDIR + scriptname, s.getSshdirectory());

	    Thread.sleep(500l);

	    CustomTask ct1 = new ExecCommand("chmod 775 " + s.getSshdirectory() + scriptname);
	    ssh.exec(ct1);

	    Thread.sleep(500l);

	    CustomTask ct2 = new ExecShellScript(s.getSshdirectory() + scriptname);
	    Result r = ssh.exec(ct2);
	    
	    //ExecShellScript ct4 = new ExecShellScript(s.getSshdirectory() + scriptname);
	    //
	    //ExecCommand ct5 = new ExecCommand("chmod 775 " + s.getSshdirectory() + scriptname);
	   
	    
	    Thread.sleep(500l);
	    System.out.println(s.getCommand() + " +++ " + r.sysout);

	    if (r.isSuccess) {
		returnvalue = r.sysout;
	    } else {
		returnvalue = r.error_msg;
	    }

	    // returnvalue = r.sysout;

	    // delete
	    CustomTask ct3 = new ExecCommand("rm " + s.getSshdirectory() + scriptname);
	    ssh.exec(ct3);
	} catch (TaskExecFailException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    ssh.disconnect();
	}

	return returnvalue;
    }
}

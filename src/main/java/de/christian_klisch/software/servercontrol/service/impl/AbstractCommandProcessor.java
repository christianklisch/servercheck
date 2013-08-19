package de.christian_klisch.software.servercontrol.service.impl;

import java.io.File;
import java.io.IOException;
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
import de.christian_klisch.software.servercontrol.model.Process;

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
    
    protected void execute(Process task) {
	Command v = (Command) task;
	String returnvalue = "";

	try {
	    java.lang.Process p = null;
	    if (((CommandView) v).getTargetOS().equals(LINOS)) {
		FileUtils.writeStringToFile(new File(SCRIPTDIR + SCRIPTFILELIN), v.getCommand());
		if (((CommandView) v).getSshserver() != null && !((CommandView) v).getSshserver().isEmpty()) {
		    returnvalue = this.executeOnSSH((Command) v, SCRIPTFILELIN);
		} else {
		    Runtime.getRuntime().exec("chmod 775 " + SCRIPTDIR + SCRIPTFILELIN);
		    p = Runtime.getRuntime().exec(SCRIPTDIR + SCRIPTFILELIN);
		}
	    }
	    if (((CommandView) v).getTargetOS().equals(WINOS)) {
		FileUtils.writeStringToFile(new File(SCRIPTDIR + SCRIPTFILEWIN), v.getCommand());
		if (((CommandView) v).getSshserver() != null && !((CommandView) v).getSshserver().isEmpty()) {
		    returnvalue = this.executeOnSSH((Command) v, SCRIPTFILEWIN);
		} else {
		    p = Runtime.getRuntime().exec(SCRIPTDIR + SCRIPTFILEWIN);
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
	ssh.connect();

	try {
	    // upload
	    ssh.uploadSingleDataToServer(SCRIPTDIR + scriptname, s.getSshdirectory());

	    CustomTask ct1 = new ExecCommand("chmod 775 " + s.getSshdirectory() + scriptname);
	    ssh.exec(ct1);

	    CustomTask ct2 = new ExecShellScript(s.getSshdirectory() + scriptname);

	    Result r = ssh.exec(ct2);
	    returnvalue = r.sysout;

	    // delete
	    CustomTask ct3 = new ExecCommand("rm " + s.getSshdirectory() + scriptname);
	    ssh.exec(ct3);
	} catch (TaskExecFailException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	ssh.disconnect();
	return returnvalue;
    }
}

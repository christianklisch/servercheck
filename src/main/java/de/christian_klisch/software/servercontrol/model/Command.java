package de.christian_klisch.software.servercontrol.model;

import java.util.GregorianCalendar;

/**
 * Abstract command model.
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
public abstract class Command extends AbstractTask implements Process {

    protected String filename;

    protected String id;

    protected String description;

    protected String command;

    protected String lastResult;

    protected GregorianCalendar lastExecute;

    protected String sshserver;

    protected String sshuser;

    protected String sshpassword;

    protected String sshdirectory;

    protected String regexOk;

    protected String regexWarn;

    protected String targetOS;

    public String getSshserver() {
	return sshserver;
    }

    public void setSshserver(String sshserver) {
	this.sshserver = sshserver;
    }

    public String getSshuser() {
	return sshuser;
    }

    public void setSshuser(String sshuser) {
	this.sshuser = sshuser;
    }

    public String getSshpassword() {
	return sshpassword;
    }

    public void setSshpassword(String sshpassword) {
	this.sshpassword = sshpassword;
    }

    public String getSshdirectory() {
	return sshdirectory;
    }

    public void setSshdirectory(String sshdirectory) {
	this.sshdirectory = sshdirectory;
    }

    public String getTargetOS() {
	return targetOS;
    }

    public void setTargetOS(String targetOS) {
	this.targetOS = targetOS;
    }

}

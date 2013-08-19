package de.christian_klisch.software.servercontrol.model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Abstract task model.
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
public abstract class AbstractTask {

    protected String filename;

    protected String id;

    protected String description;

    protected String command;

    protected String lastResult;

    protected GregorianCalendar lastExecute;

    protected String regexOk;

    protected String regexWarn;

    public String getFilename() {
	return filename;
    }

    public void setFilename(String filename) {
	this.filename = filename;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getCommand() {
	return command;
    }

    public void setCommand(String command) {
	this.command = command;
    }

    public String getLastResult() {
	return lastResult;
    }

    public void setLastResult(String lastResult) {
	this.lastResult = lastResult;
    }

    public GregorianCalendar getLastExecute() {
	return lastExecute;
    }

    public void setLastExecute(GregorianCalendar lastExecute) {
	this.lastExecute = lastExecute;
    }

    public String getRegexOk() {
	return regexOk;
    }

    public void setRegexOk(String regexOk) {
	this.regexOk = regexOk;
    }

    public String getRegexWarn() {
	return regexWarn;
    }

    public void setRegexWarn(String regexWarn) {
	this.regexWarn = regexWarn;
    }

    public String getStatusImage() {
	String image = "<span class=\"badge badge-important\"><i class=\"icon-remove icon-white\"></i></span>";

	if (this.lastResult.matches(this.regexWarn))
	    image = "<span class=\"badge badge-warning\"><i class=\"icon-warning-sign\"></i></span>";

	if (this.lastResult.matches(this.regexOk))
	    image = "<span class=\"badge badge-success\"><i class=\"icon-ok icon-white\"></i></span>";

	return image;
    }

    public String getTimeString() {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	return df.format(lastExecute.getTime());
    }

}

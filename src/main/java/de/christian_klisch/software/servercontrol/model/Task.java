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
public abstract class Task {

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

    @Deprecated
    public String getStatusImage() {
	String image = "<span class=\"badge badge-important\"><i class=\"icon-remove icon-white\"></i></span>";

	if (this.lastResult != null) {
	    if (this.regexWarn != null && this.lastResult.matches(this.regexWarn))
		image = "<span class=\"badge badge-warning\"><i class=\"icon-warning-sign\"></i></span>";

	    if (this.regexOk != null && this.lastResult.matches(this.regexOk))
		image = "<span class=\"badge badge-success\"><i class=\"icon-ok icon-white\"></i></span>";
	}
	return image;
    }

    @Deprecated
    public String getRequestButton() {
	String button = "";

	if (this instanceof ProcessExec) {
	    button = "<div class=\"btn-group\"><form method=\"post\" class=\"taskform\"><input type=\"hidden\" name=\"process\" value=\"" + this.getId()
		    + "\">"+this.getId()+"</input><button class=\"btn task\" type=\"submit\"><i class=\"icon-play\"></i></button></form>" +
		    "<a href=\"#modal"+this.id+"\" role=\"button\" class=\"btn\" data-toggle=\"modal\">Launch demo modal</a></div>";
	    
	    /* 
	    button = button + "    <div id=\"modal"+this.id+"\" class=\"modal hide fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">"+
    "<div class=\"modal-header\">"+
    "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>"+
    "<h3>Modal header</h3>"+
    "</div>"+
    "<div class=\"modal-body\">"+
    "<p>One fine body…</p>"+
    "</div>"+
    "<div class=\"modal-footer\">"+
    "<a href=\"#\" class=\"btn\">Close</a>"+
    "<a href=\"#\" class=\"btn btn-primary\">Save changes</a>"+
    "</div>"+
    "</div>";*/
	    
	}
	if (this instanceof ProcessView) {
	    button = "<div class=\"btn-group\"><form method=\"post\" class=\"taskform\"><input type=\"hidden\" name=\"process\" value=\"" + this.getId()
		    + "\"/><button class=\"btn task\" type=\"submit\"><i class=\"icon-refresh\"></i></button></form></div>";
	}

	return button;
    }

    @Deprecated
    public String getTimeStampString() {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	return df.format(lastExecute.getTime());
    }

    @Deprecated
    public String getDateString() {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	return df.format(lastExecute.getTime());
    }

    @Deprecated
    public String getTimeString() {
	SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.S");
	return df.format(lastExecute.getTime());
    }
    
    @Deprecated
    public String toString() {
	String t = "			    <dl>" +
                        "			    	<dt>ID</dt>" +
                        "			    	<dd>"+ this.id +"</dd>" +
                        "			    	<dt>Description</dt>" +
                        "			    	<dd>"+ this.description+"</dd>	" +
                        "			    	<dt>Last Check</dt>" +
                        "			    	<dd>"+this.getTimeString()+"</dd>" +		
                        "			    	<dt>Last Result</dt>" +
                        "			    	<dd>" + this.getStatusImage() + " " + this.lastResult+"</dd>" +				    				    			    	
                        "		   </dl>"+
                        "		   " + this.getRequestButton();
	return t;
    }        

}

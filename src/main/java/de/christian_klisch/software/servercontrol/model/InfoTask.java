package de.christian_klisch.software.servercontrol.model;

/**
 * Webinformation about task model.
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
public class InfoTask {
    protected String idText;

    protected String commandText;

    protected String descriptionText;

    protected String lastResultText;

    protected String lastDateText;

    protected String lastTimeText;

    protected String statusText;

    public String getCommandText() {
	return commandText;
    }

    public void setCommandText(String commandText) {
	this.commandText = commandText;
    }

    public String getIdText() {
	return idText;
    }

    public void setIdText(String idText) {
	this.idText = idText;
    }

    public String getDescriptionText() {
	return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
	this.descriptionText = descriptionText;
    }

    public String getLastResultText() {
	return lastResultText;
    }

    public void setLastResultText(String lastResultText) {
	this.lastResultText = lastResultText;
    }

    public String getLastDateText() {
	return lastDateText;
    }

    public void setLastDateText(String lastDateText) {
	this.lastDateText = lastDateText;
    }

    public String getLastTimeText() {
	return lastTimeText;
    }

    public void setLastTimeText(String lastTimeText) {
	this.lastTimeText = lastTimeText;
    }

    public String getStatusText() {
	return statusText;
    }

    public void setStatusText(String statusText) {
	this.statusText = statusText;
    }

    public String toString() {
	String t = "			    <dl>" +
                        "			    	<dt>ID</dt>" +
                        "			    	<dd>"+ this.getIdText() +"</dd>" +
                        "			    	<dt>Description</dt>" +
                        "			    	<dd>"+ this.getDescriptionText()+"</dd>	" +
                        "			    	<dt>Last Check</dt>" +
                        "			    	<dd>"+this.getLastDateText()+" "+this.getLastTimeText() + "</dd>" +		
                        "			    	<dt>Last Result</dt>" +
                        "			    	<dd>" + this.getStatusText() + " " + this.getLastResultText()+"</dd>" +				    				    			    	
                        "		   </dl>";
	return t;
    }        
    
}

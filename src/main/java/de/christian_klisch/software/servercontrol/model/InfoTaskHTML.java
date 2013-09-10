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
public class InfoTaskHTML extends InfoTask {

    protected String statusImageHTML;

    protected String statusHTML;

    protected String lastResultHTML;

    protected String lastDateHTML;

    protected String lastTimeHTML;

    protected String requestButtonHTML;

    public String getStatusImageHTML() {
	return statusImageHTML;
    }

    public void setStatusImageHTML(String statusImageHTML) {
	this.statusImageHTML = statusImageHTML;
    }

    public String getStatusHTML() {
	return statusHTML;
    }

    public void setStatusHTML(String statusHTML) {
	this.statusHTML = statusHTML;
    }

    public String getLastResultHTML() {
	return lastResultHTML;
    }

    public void setLastResultHTML(String lastResultHTML) {
	this.lastResultHTML = lastResultHTML;
    }

    public String getLastDateHTML() {
	return lastDateHTML;
    }

    public void setLastDateHTML(String lastDateHTML) {
	this.lastDateHTML = lastDateHTML;
    }

    public String getLastTimeHTML() {
	return lastTimeHTML;
    }

    public void setLastTimeHTML(String lastTimeHTML) {
	this.lastTimeHTML = lastTimeHTML;
    }

    public String getRequestButtonHTML() {
	return requestButtonHTML;
    }

    public void setRequestButtonHTML(String requestButtonHTML) {
	this.requestButtonHTML = requestButtonHTML;
    }
    
    public String toString() {
	String t = "			    <dl>" +
                        "			    	<dt>ID</dt>" +
                        "			    	<dd>"+ this.getIdText() +"</dd>" +
                        "			    	<dt>Description</dt>" +
                        "			    	<dd>"+ this.getDescriptionText()+"</dd>	" +
                        "			    	<dt>Last Check</dt>" +
                        "			    	<dd>"+this.getLastDateHTML()+" "+this.getLastTimeHTML() + "</dd>" +		
                        "			    	<dt>Last Result</dt>" +
                        "			    	<dd>" + this.getStatusImageHTML() + " " + this.getLastResultHTML()+"</dd>" +		
                        "		   </dl>"+
                        "		   " + this.getRequestButtonHTML();
	return t;
    }        


}

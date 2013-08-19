package de.christian_klisch.software.servercontrol.model;

/**
 * Task model.
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
public class CommandExec extends Command implements ProcessExec {

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
                        "			    </dl>"+
                        "			    " +
                        "			    <div class=\"btn-group\">" +
                        "				    <!--<button class=\"btn\"><i class=\"icon-refresh\"></i></button>-->" +
                        "				    <form method=\"post\">" +                        
                        "				    <input type=\"hidden\" name=\"process\" value=\"" + this.getId() + "\"/><button class=\"btn\"><i class=\"icon-play\"></i></button></form>" +                        
                        "			    </div>";
	return t;
	//return "ID: " + this.id + " / Command: " + this.command + " / Last: " + this.getTimeString();
    }    
    
   
}

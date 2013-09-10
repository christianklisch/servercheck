package de.christian_klisch.software.servercontrol.util;

import java.text.SimpleDateFormat;

import de.christian_klisch.software.servercontrol.model.InfoTask;
import de.christian_klisch.software.servercontrol.model.InfoTaskHTML;
import de.christian_klisch.software.servercontrol.model.ProcessExec;
import de.christian_klisch.software.servercontrol.model.ProcessView;
import de.christian_klisch.software.servercontrol.model.Task;

/**
 * Utility creating public web information for abstract task
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
public class ModelUtil {

    public static InfoTaskHTML[] convert2InfoTaskHTML(Object[] task) {
	InfoTaskHTML[] ita = null;
	if (task != null) {
	    ita = new InfoTaskHTML[task.length];
	    int i = 0;
	    for (Object t : task)
		if (t instanceof Task)
		    ita[i++] = convert2InfoTaskHTML((Task) t);
	}
	return ita;
    }

    public static InfoTask[] convert2InfoTask(Object[] task) {
	InfoTask[] ita = null;
	if (task != null) {
	    ita = new InfoTask[task.length];
	    int i = 0;
	    for (Object t : task)
		if (t instanceof Task)
		    ita[i++] = convert2InfoTask((Task) t);
	}
	return ita;
    }

    public static InfoTaskHTML convert2InfoTaskHTML(Task task) {
	InfoTaskHTML infotaskHTML = new InfoTaskHTML();
	if (task != null) {
	    copyTaskData(infotaskHTML, task);

	    String image = "<span class=\"badge badge-important\"><i class=\"icon-remove icon-white\"></i></span>";
	    if (infotaskHTML.getLastResultText() != null) {
		if (task.getRegexWarn() != null && task.getLastResult().matches(task.getRegexWarn()))
		    image = "<span class=\"badge badge-warning\"><i class=\"icon-warning-sign\"></i></span>";
		if (task.getRegexWarn() != null && task.getLastResult().matches(task.getRegexOk()))
		    image = "<span class=\"badge badge-success\"><i class=\"icon-ok icon-white\"></i></span>";
	    }
	    infotaskHTML.setStatusImageHTML(image);

	    infotaskHTML.setLastDateHTML("<div id=\"lastDate_" + infotaskHTML.getIdText() + "\">" + infotaskHTML.getLastDateText() + "</div>");
	    infotaskHTML.setLastTimeHTML("<div id=\"lastTime_" + infotaskHTML.getIdText() + "\">" + infotaskHTML.getLastTimeText() + "</div>");
	    infotaskHTML.setLastResultHTML("<div id=\"lastResult_" + infotaskHTML.getIdText() + "\">" + infotaskHTML.getLastResultText() + "</div>");
	    infotaskHTML.setStatusHTML("<div id=\"status_" + infotaskHTML.getIdText() + "\">" + infotaskHTML.getStatusText() + "</div>");
	    infotaskHTML.setStatusImageHTML("<div id=\"statusImage_" + infotaskHTML.getIdText() + "\">" + infotaskHTML.getStatusImageHTML()
		    + "</div>");

	    String button = "";
	    if (task instanceof ProcessExec) {
		button = "<div class=\"btn-group\"><form method=\"post\" class=\"taskform\"><input type=\"hidden\" name=\"process\" value=\""
			+ task.getId() + "\">" + task.getId()
			+ "</input><button class=\"btn task\" type=\"submit\"><i class=\"icon-play\"></i></button></form>" + "<a href=\"#modal"
			+ task.getId() + "\" role=\"button\" class=\"btn\" data-toggle=\"modal\">Launch demo modal</a></div>";

	    }
	    if (task instanceof ProcessView) {
		button = "<div class=\"btn-group\"><form method=\"post\" class=\"taskform\"><input type=\"hidden\" name=\"process\" value=\""
			+ task.getId() + "\"/><button class=\"btn task\" type=\"submit\"><i class=\"icon-refresh\"></i></button></form></div>";
	    }

	    infotaskHTML.setRequestButtonHTML(button);

	}
	return infotaskHTML;
    }

    public static InfoTask convert2InfoTask(Task task) {
	InfoTask infotask = new InfoTask();
	if (task != null)
	    copyTaskData(infotask, task);
	return infotask;
    }

    public static void copyTaskData(InfoTask infotask, Task task) {
	infotask.setIdText(task.getId());
	infotask.setCommandText(task.getCommand());
	infotask.setDescriptionText(task.getDescription());
	infotask.setLastResultText(task.getLastResult());
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	if (task.getLastExecute() != null)
	    infotask.setLastDateText(df.format(task.getLastExecute().getTime()));
	df = new SimpleDateFormat("HH:mm:ss.S");
	if (task.getLastExecute() != null)
	    infotask.setLastTimeText(df.format(task.getLastExecute().getTime()));

	String status = "ERROR";
	if (infotask.getLastResultText() != null) {
	    if (task.getRegexWarn() != null && task.getLastResult().matches(task.getRegexWarn()))
		status = "WARNING";

	    if (task.getRegexWarn() != null && task.getLastResult().matches(task.getRegexOk()))
		status = "OK";
	}

	infotask.setStatusText(status);
    }

}

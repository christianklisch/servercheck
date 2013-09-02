package de.christian_klisch.software.servercontrol.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.thoughtworks.xstream.XStream;

import de.christian_klisch.software.servercontrol.config.Configuration;
import de.christian_klisch.software.servercontrol.model.Command;
import de.christian_klisch.software.servercontrol.model.CommandExec;
import de.christian_klisch.software.servercontrol.model.CommandView;
import de.christian_klisch.software.servercontrol.model.ProcessExec;
import de.christian_klisch.software.servercontrol.model.ProcessView;
import de.christian_klisch.software.servercontrol.model.SqlExec;
import de.christian_klisch.software.servercontrol.model.SqlView;
import de.christian_klisch.software.servercontrol.model.Task;
import de.christian_klisch.software.servercontrol.service.ProcessorIF;
import de.christian_klisch.software.servercontrol.service.impl.CommandExecProcessor;
import de.christian_klisch.software.servercontrol.service.impl.CommandViewProcessor;
import de.christian_klisch.software.servercontrol.service.impl.SqlViewProcessor;

/**
 * Controller handling tasks.
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
@Controller("app")
public class Application implements Configuration {

    private static String xmlPath = "./xml/";

    private static Map<String, Task> requestMap = new HashMap<String, Task>();
    private static Map<String, ProcessorIF> processorMap = new HashMap<String, ProcessorIF>();
    private static String template = null;

    private XStream xstream = new XStream();

    public Application() {
	xstream.alias("commandView", CommandView.class);
	xstream.alias("commandExec", CommandExec.class);
	xstream.alias("sqlView", SqlView.class);
	xstream.alias("sqlExec", SqlExec.class);

	xstream.aliasField("command", Command.class, "command");

	this.initTemplateFile();
	this.initProcessors();

    }

    public void initTemplateFile() {
	try {
	    template = FileUtils.readFileToString(new File(TEMPLATEFILE));

	} catch (IOException e) {
	    e.printStackTrace();
	    template = "";
	}
    }

    private void initProcessors() {
	ProcessorIF p1 = new CommandViewProcessor();
	processorMap.put(p1.getClassType(), p1);

	ProcessorIF p2 = new CommandExecProcessor();
	processorMap.put(p2.getClassType(), p2);
    
	ProcessorIF p3 = new SqlViewProcessor();
	processorMap.put(p3.getClassType(), p3);    
    }

    public String getTemplate() {
	return template;
    }

    public static Map<String, Task> getRequestMap() {
	return requestMap;
    }

    public String getFilledTemplate() {
	Map<String, Object> params = new HashMap<String, Object>();
	MustacheFactory mf = new DefaultMustacheFactory();
	Mustache mustache = mf.compile(new StringReader(this.getTemplate()), TEMPLATEFILE);

	params.put("views", this.getAllViews());
	params.put("execs", this.getAllExecs());
	params.put("processes", this.getAllProcesses());

	return mustache.execute(new StringWriter(), params).toString();
    }

    public void saveTaskAsXML(Task task) {

	if (task.getFilename() == null)
	    task.setFilename(task.getId() + FILETYPE);

	String xml = xstream.toXML(task);

	try {
	    FileUtils.writeStringToFile(new File(xmlPath + task.getFilename()), xml);
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void removeDeletedTasks() {
	File xmldir = new File(xmlPath);
	String[] files = xmldir.list();

	List<String> list = Arrays.asList(files);

	Iterator<String> keys = requestMap.keySet().iterator();

	while (keys.hasNext()) {
	    Task task = requestMap.get(keys.next());
	    if (!list.contains(task.getId() + FILETYPE)) {
		requestMap.remove(task.getId());
	    }
	}
    }

    public Map<String, Task> readTaskFromXML() {
	File xmldir = new File(xmlPath);
	String[] files = xmldir.list();

	Map<String, Task> tmpMap = new HashMap<String, Task>();

	for (String file : files) {
	    if (file.endsWith(FILETYPE)) {
		String xml = "";
		try {
		    xml = FileUtils.readFileToString(new File(xmlPath + file));
		} catch (IOException e) {
		    e.printStackTrace();
		}

		Task t = (Task) xstream.fromXML(xml);
		tmpMap.put(t.getId(), t);
	    }
	}

	return tmpMap;
    }

    public void copyTasksInMap(Map<String, Task> tasks) {
	Iterator<String> keys = tasks.keySet().iterator();

	while (keys.hasNext()) {
	    Task task = tasks.get(keys.next());
	    if (!requestMap.containsKey(task.getId())) {
		requestMap.put(task.getId(), task);
	    }
	}
    }

    public void executeAll() {
	Iterator<String> keys = requestMap.keySet().iterator();

	while (keys.hasNext()) {
	    try {
		Task task = requestMap.get(keys.next());
		if (task instanceof ProcessView)
		    this.executeProcess(task);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public void executeProcess(Task process) {
	ProcessorIF processor = processorMap.get(process.getClass().toString());
	processor.execute(process);
	this.saveTaskAsXML(process);
    }

    public void executeFromWeb(String key) {
	Task process = requestMap.get(key);
	this.executeProcess(process);
    }

    public Map<String, Task> getAllViews() {
	Map<String, Task> viewMap = new HashMap<String, Task>();

	Iterator<String> keys = requestMap.keySet().iterator();

	while (keys.hasNext()) {
	    Task task = requestMap.get(keys.next());
	    if (task instanceof ProcessView) {
		viewMap.put(task.getId(), task);
	    }
	}

	return viewMap;
    }

    public Map<String, Task> getAllExecs() {
	Map<String, Task> execMap = new HashMap<String, Task>();

	Iterator<String> keys = requestMap.keySet().iterator();

	while (keys.hasNext()) {
	    Task task = requestMap.get(keys.next());
	    if (task instanceof ProcessExec) {
		execMap.put(task.getId(), task);
	    }
	}

	return execMap;
    }

    public Map<String, Task> getAllProcesses() {
	return getRequestMap();
    }

    public static void setXmlPath(String xmlPath) {
	Application.xmlPath = xmlPath;
    }

}

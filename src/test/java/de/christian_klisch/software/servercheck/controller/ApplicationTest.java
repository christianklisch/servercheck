package de.christian_klisch.software.servercheck.controller;

import java.util.GregorianCalendar;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import de.christian_klisch.software.servercontrol.controller.Application;
import de.christian_klisch.software.servercontrol.model.Command;
import de.christian_klisch.software.servercontrol.model.CommandView;
import de.christian_klisch.software.servercontrol.model.Task;

/**
 * JUnit Test.
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
public class ApplicationTest extends TestCase {

    private Application app = new Application();

    @Before
    public void testCreateExampleXML() {

	Command t = new CommandView();

	//if (SystemUtils.IS_OS_WINDOWS) {
	if (true) {
	    t.setCommand("@echo off\necho 0");
	    t.setDescription("Skript returning value 0 WIN-ONLY");
	    t.setId("r0");
	    t.setLastExecute(new GregorianCalendar());
	    t.setLastResult("1");
	    // t.setType(Task.Types.VIEW);
	    t.setFilename("r0.xml");
	    t.setRegexOk("0");
	    t.setRegexWarn("1");
	    t.setTargetOS("Windows");
	 
	}

	//if (SystemUtils.IS_OS_LINUX) {
	if (2*50 < 1) {	    
	    t.setCommand("echo 123");
	    t.setDescription("Skript returning value 0 LIN-ONLY");
	    t.setId("r0");
	    t.setLastExecute(new GregorianCalendar());
	    t.setLastResult("bla");
	    // t.setType(Task.Types.VIEW);
	    t.setFilename("r0.xml");
	    t.setRegexOk("0");
	    t.setRegexWarn("1");
	    t.setTargetOS("Linux");

	    if (t instanceof Command) {
		Command c = (Command) t;
		c.setSshpassword("pass");
		c.setSshserver("server");
		c.setSshuser("root");
		c.setSshdirectory("/tmp/");
	    }
	}

	app.saveTaskAsXML(t);

    }

    @Test
    public void testReadTaskFromXML() {
	Map<String, Task> m = app.readTaskFromXML();
	assertTrue(m.size() > 0);
    }

    @Test
    public void testRemoveDeletedTasks() {

	Application.getRequestMap().clear();

	Task t = new CommandView();

	t.setCommand("echo 0");
	t.setDescription("Deleting skript");
	t.setId("d1");
	t.setLastExecute(new GregorianCalendar());
	t.setLastResult("1");
	// t.setType(Task.Types.VIEW);
	t.setFilename("d1.xml");

	Application.getRequestMap().put(t.getId(), t);

	app.removeDeletedTasks();

	System.out.println(Application.getRequestMap().containsKey(t.getId()));
	System.out.println(t.getId());

	assertFalse(Application.getRequestMap().containsKey(t.getId()));
    }

    @Test
    public void testProcess() {
	long g1 = 0, g2 = 0;
	testCreateExampleXML();

	app.copyTasksInMap(app.readTaskFromXML());

	Task t1 = Application.getRequestMap().get("r0");
	if (t1 != null)
	    g1 = t1.getLastExecute().getTimeInMillis();

	try {
	    Thread.sleep(10);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	    fail();
	}

	app.executeAll();

	Task t2 = Application.getRequestMap().get("r0");
	if (t2 != null)
	    g2 = t2.getLastExecute().getTimeInMillis();

	assertTrue(g1 < g2);
    }

}

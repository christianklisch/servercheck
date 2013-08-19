package de.christian_klisch.software.servercontrol.job;

import de.christian_klisch.software.servercontrol.controller.Application;
/**
 * Jobrunner starts tasks in controller class.
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
public class JobRunner extends Thread implements Runnable {

    private static JobRunner instance = null;
    private static int idle_time = 5000;

    public void run() {
	try {
	    while (true) {
		Thread.sleep(idle_time);
		Application app = new Application();
		app.initTemplateFile();

		app.copyTasksInMap(app.readTaskFromXML());
		app.removeDeletedTasks();
		app.executeAll();

		System.out.println("läuft" + System.currentTimeMillis());
	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    private JobRunner() {
    }

    public static JobRunner getInstance() {
	if (instance == null) {
	    instance = new JobRunner();
	    instance.start();
	}
	return instance;
    }

    public static void setIdle_time(int idle_time) {
	JobRunner.idle_time = idle_time;
    }

}

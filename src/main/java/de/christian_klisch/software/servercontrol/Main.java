package de.christian_klisch.software.servercontrol;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import de.christian_klisch.software.servercontrol.config.Configuration;
import de.christian_klisch.software.servercontrol.controller.Application;
import de.christian_klisch.software.servercontrol.job.JobRunner;

/**
 * Main class to start webserver an initialize controller.
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
public class Main implements Configuration {

    private WebServer server;

    public static void main(String[] args) throws Exception {

	Properties prop = new Properties();

	try {
	    prop.load(new FileInputStream(CONFIGFILE));

	    String sport = prop.getProperty("port");
	    String sidletime = prop.getProperty("idle_time");
	    String xmlpath = prop.getProperty("xml_path");

	    int port = Integer.parseInt(sport);
	    int idletime = Integer.parseInt(sidletime);

	    Application.setXmlPath(xmlpath);

	    new Main(port, idletime).start();

	} catch (IOException ex) {
	    ex.printStackTrace();
	}

    }

    public Main(int port, int idletime) {
	server = new WebServer(port);

	JobRunner.getInstance();
	JobRunner.setIdle_time(idletime);
    }

    public void start() throws Exception {
	server.start();
	server.join();
    }
    
    public void stop() throws Exception{
	server.stop();
    }
}

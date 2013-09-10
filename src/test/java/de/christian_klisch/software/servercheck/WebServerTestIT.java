package de.christian_klisch.software.servercheck;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import de.christian_klisch.software.servercontrol.Main;
import de.christian_klisch.software.servercontrol.config.Configuration;
import de.christian_klisch.software.servercontrol.controller.Application;

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
public class WebServerTestIT implements Configuration {

    @Test
    public void appShouldBeRunningAndMainPageIsAvailable() throws Exception {

	Properties prop = new Properties();

	prop.load(new FileInputStream(CONFIGFILE));

	String sport = prop.getProperty("port");
	String sidletime = prop.getProperty("idle_time");
	String xmlpath = prop.getProperty("xml_path");

	int port = Integer.parseInt(sport);
	int idletime = Integer.parseInt(sidletime);

	Application.setXmlPath(xmlpath);

	final Main servercontrol = new Main(port, idletime);

	new Thread() {
	    public void run() {
		try {
		    servercontrol.start();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}.start();

	Thread.sleep(10000);

	HttpClient client = new DefaultHttpClient();
	HttpGet GET_MainPage = new HttpGet("http://localhost:8080/list");

	HttpResponse response = client.execute(GET_MainPage);

	servercontrol.stop();

	assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

}

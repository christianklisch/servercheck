package de.christian_klisch.software.servercheck;

import java.io.FileInputStream;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import de.christian_klisch.software.servercontrol.Main;
import de.christian_klisch.software.servercontrol.config.Configuration;
import de.christian_klisch.software.servercontrol.controller.Application;

public class WebServerTest  extends TestCase implements Configuration {

    
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
    
    public void testTruetest()
    {
	assertTrue(true);
    }

}

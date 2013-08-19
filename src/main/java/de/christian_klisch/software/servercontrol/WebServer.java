package de.christian_klisch.software.servercontrol;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.webapp.WebAppContext;

import de.christian_klisch.software.servercontrol.config.Configuration;

/**
 * Jetty Webserver for using web interface
 * 
 * @author original jetty8 steve liles / changes to jetty9 by Christian Klisch
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
public class WebServer implements Configuration {

    public static interface WebContext {
	public File getWarPath();

	public String getContextPath();
    }

    private Server server;
    private int port;

    public WebServer(int aPort) {
	this(aPort, null);
    }

    public WebServer(int aPort, String aBindInterface) {
	port = aPort;
    }

    public void start() throws Exception {
	System.setProperty(DISABLEJSR199, "true");
	server = new Server(this.port);

	server.setHandler(createHandlers());
	server.setStopAtShutdown(true);

	server.start();
    }

    public void join() throws InterruptedException {
	server.join();
    }

    public void stop() throws Exception {
	server.stop();
    }

    private HandlerCollection createHandlers() {
	WebAppContext _ctx = new WebAppContext();
	_ctx.setContextPath("/");

	if (isRunningInShadedJar()) {
	    _ctx.setWar(getShadedWarUrl());
	} else {
	    _ctx.setWar(this.getClass().getClassLoader().getResource(WEPPAPPDIR).toExternalForm());
	}

	List<Handler> _handlers = new ArrayList<Handler>();

	_handlers.add(_ctx);

	HandlerList _contexts = new HandlerList();
	_contexts.setHandlers(_handlers.toArray(new Handler[0]));

	RequestLogHandler _log = new RequestLogHandler();
	_log.setRequestLog(createRequestLog());

	HandlerCollection _result = new HandlerCollection();
	_result.setHandlers(new Handler[] { _contexts, _log });

	return _result;
    }

    private RequestLog createRequestLog() {
	NCSARequestLog _log = new NCSARequestLog();

	File _logPath = new File(LOG_PATH);
	_logPath.getParentFile().mkdirs();

	_log.setFilename(_logPath.getPath());
	_log.setRetainDays(90);
	_log.setExtended(false);
	_log.setAppend(true);
	_log.setLogTimeZone("GMT");
	_log.setLogLatency(true);
	return _log;
    }

    private boolean isRunningInShadedJar() {
	try {
	    Class.forName(CLASS_ONLY_IDE);
	    return false;
	} catch (ClassNotFoundException anExc) {
	    return true;
	}
    }

    private URL getResource(String aResource) {
	return Thread.currentThread().getContextClassLoader().getResource(aResource);
    }

    private String getShadedWarUrl() {
	String _urlStr = getResource(WEB_XML).toString();
	return _urlStr.substring(0, _urlStr.length() - 15);
    }
}

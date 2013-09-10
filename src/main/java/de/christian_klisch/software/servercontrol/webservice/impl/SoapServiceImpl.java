package de.christian_klisch.software.servercontrol.webservice.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import de.christian_klisch.software.servercontrol.controller.Application;
import de.christian_klisch.software.servercontrol.model.InfoTask;
import de.christian_klisch.software.servercontrol.util.ModelUtil;
import de.christian_klisch.software.servercontrol.webservice.SoapServiceIF;

/**
 * Soap Webservice implementation
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

@WebService(serviceName = "soapService")
public class SoapServiceImpl extends SpringBeanAutowiringSupport implements SoapServiceIF {

    Application application;

    @WebMethod
    @WebResult(name = "task")
    public InfoTask getTaskDetail(@XmlElement(required = true) @WebParam(name = "id") String id) {
	return ModelUtil.convert2InfoTask(application.readTaskFromMap(id));
    }

    @WebMethod
    @WebResult(name = "tasks")
    public Object[] getAllViews() {
	return ModelUtil.convert2InfoTask(application.getAllViews().values().toArray());
    }

    @WebMethod(exclude = true)
    public void setApplication(Application application) {
	this.application = application;
    }

}

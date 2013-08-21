package de.christian_klisch.software.servercontrol.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import de.christian_klisch.software.servercontrol.config.Configuration;
import de.christian_klisch.software.servercontrol.model.Sql;
import de.christian_klisch.software.servercontrol.model.Task;

/**
 * Processor for abstract sql
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
public class AbstractSqlProcessor implements Configuration {

    protected void execute(Task task) {
	Sql v = (Sql) task;
	String returnvalue = "";

	try {
	    Connection connection = getConnection(v);
	    Statement statement = connection.createStatement();

	    String query = v.getCommand();
	    ResultSet resultSet = statement.executeQuery(query);

	    resultSet.next();
	    returnvalue = resultSet.getString(1);

	    if (returnvalue != null && !returnvalue.isEmpty()) {
		returnvalue = returnvalue.trim();
		returnvalue = returnvalue.replaceAll("[\\r\\n]", "");
	    }

	    statement.close();
	    resultSet.close();
	    connection.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	v.setLastExecute(new GregorianCalendar());
	v.setLastResult(returnvalue);
    }

    protected Connection getConnection(Sql v) {

	try {
	    if (v.getDatabaseType().equals("Oracle")) {
		Class.forName(DBDRIVER_ORACLE);
		return DriverManager.getConnection("jdbc:oracle:thin:@" + v.getDbhost() + ":" + v.getDbport() + ":" + v.getDbname(), v.getDbuser(),
			v.getDbpassword());
	    }
	    if (v.getDatabaseType().equals("MySQL")) {
		Class.forName(DBDRIVER_MYSQL);
		return DriverManager.getConnection("jdbc:mysql://" + v.getDbhost() + ":" + v.getDbport() + "/" + v.getDbname(), v.getDbuser(),
			v.getDbpassword());
	    }
	    if (v.getDatabaseType().equals("H2")) {
		Class.forName(DBDRIVER_H2);
		return DriverManager.getConnection("jdbc:h2:tcp://" + v.getDbhost() + ":" + v.getDbport() + "/" + v.getDbname(), v.getDbuser(),
			v.getDbpassword());
	    }
	    if (v.getDatabaseType().equals("Postgre")) {
		Class.forName(DBDRIVER_POSTGRES);
		return DriverManager.getConnection("jdbc:postgresql://" + v.getDbhost() + ":" + v.getDbport() + "/" + v.getDbname(), v.getDbuser(),
			v.getDbpassword());
	    }

	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return null;
    }

}

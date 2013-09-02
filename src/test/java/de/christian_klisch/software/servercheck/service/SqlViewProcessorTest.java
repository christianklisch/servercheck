package de.christian_klisch.software.servercheck.service;

import junit.framework.TestCase;
import de.christian_klisch.software.servercontrol.controller.Application;
import de.christian_klisch.software.servercontrol.model.Sql;
import de.christian_klisch.software.servercontrol.model.SqlView;
import de.christian_klisch.software.servercontrol.service.ProcessorIF;
import de.christian_klisch.software.servercontrol.service.impl.SqlViewProcessor;

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
public class SqlViewProcessorTest extends TestCase  {   
   
    public void testViewSqk()
    {
	Sql sql = new SqlView();
    
	sql.setCommand("SELECT COUNT(*) FROM actions");
	sql.setDatabaseType("MySQL");
	sql.setDbhost("localhost");
	sql.setDbname("drupal");
	sql.setDbport("3306");
	sql.setDbuser("root");
	sql.setDbpassword("");
	
	sql.setId("s1");
	sql.setFilename("s1.xml");
	
	ProcessorIF processor = new SqlViewProcessor();
	processor.execute(sql);
	
	assertNotNull(sql.getLastExecute());
	assertNotNull(sql.getLastResult());
	assertTrue(sql.getStatusImage().indexOf("important") > 0);
	assertTrue(sql.getRequestButton().indexOf("refresh") > 0);
	
	Application app = new Application();
	app.saveTaskAsXML(sql);
	
	
	
    }
    
}

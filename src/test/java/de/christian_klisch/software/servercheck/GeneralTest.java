package de.christian_klisch.software.servercheck;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import de.christian_klisch.software.servercontrol.model.CommandView;

import junit.framework.TestCase;

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
public class GeneralTest extends TestCase {

    @Test
    public void testCommandline()
    {
	System.out.println(CommandView.class.toString());
	try {
	    Process p = Runtime.getRuntime().exec("echo 0");
	    System.out.println(IOUtils.toString(p.getInputStream(),"UTF-8"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	assertTrue(true);
    }
    
}

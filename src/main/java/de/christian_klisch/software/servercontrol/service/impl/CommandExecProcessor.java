package de.christian_klisch.software.servercontrol.service.impl;

import de.christian_klisch.software.servercontrol.model.CommandExec;
import de.christian_klisch.software.servercontrol.model.Process;
import de.christian_klisch.software.servercontrol.service.ProcessorIF;

/**
 * Processor for execute commands
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
public class CommandExecProcessor extends AbstractCommandProcessor implements ProcessorIF {

    public void execute(Process task) {
	if (task instanceof CommandExec)
	    super.execute(task);
    }

    public String getClassType() {
	return CommandExec.class.toString();
    }

}

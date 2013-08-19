package de.christian_klisch.software.servercontrol.model;

import java.util.GregorianCalendar;

/**
 * Interface for task model.
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
public interface Process {
    public String getId();

    public String getFilename();

    public void setFilename(String filename);

    public void setId(String id);

    public String getDescription();

    public void setDescription(String description);

    public String getCommand();

    public void setCommand(String command);

    public String getLastResult();

    public void setLastResult(String lastResult);

    public GregorianCalendar getLastExecute();

    public void setLastExecute(GregorianCalendar lastExecute);
    
    public String getRegexWarn();

    public void setRegexWarn(String regexWarn);
    
    public String getRegexOk();

    public void setRegexOk(String regexOk);
}

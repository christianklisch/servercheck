package de.christian_klisch.software.servercontrol.model;

/**
 * Abstract sql model.
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
public class Sql extends Task {

    protected String databaseType;

    protected String dbuser;

    protected String dbhost;

    protected String dbpassword;

    protected String dbport;

    protected String dbname;

    public String getDbport() {
	return dbport;
    }

    public void setDbport(String dbport) {
	this.dbport = dbport;
    }

    public String getDbname() {
	return dbname;
    }

    public void setDbname(String dbname) {
	this.dbname = dbname;
    }

    public String getDatabaseType() {
	return databaseType;
    }

    public void setDatabaseType(String databaseType) {
	this.databaseType = databaseType;
    }

    public String getDbuser() {
	return dbuser;
    }

    public void setDbuser(String dbuser) {
	this.dbuser = dbuser;
    }

    public String getDbhost() {
	return dbhost;
    }

    public void setDbhost(String dbhost) {
	this.dbhost = dbhost;
    }

    public String getDbpassword() {
	return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
	this.dbpassword = dbpassword;
    }

}

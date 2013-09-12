package de.christian_klisch.software.servercontrol.config;

public interface Configuration {
    public static final String CONFIGFILE = "./config/config.properties";
    public static final String LOG_PATH = "./logs/access/yyyy_mm_dd.servercontrol.log";
    public static final String WEB_XML = "META-INF/webapp/WEB-INF/web.xml";
    public static final String CLASS_ONLY_IDE = "de.christian_klisch.software.servercontrol.Main";
    public static final String TEMPLATEFILE = "./config/template.xhtml";
    public static final String SCRIPTDIR = "./tmp/";
    public static final String SCRIPTFILEWIN = "script.bat";
    public static final String SCRIPTFILELIN = "script.sh";
    public static final String FILETYPE = ".xml";
    public static final String WINOS = "Windows";
    public static final String LINOS = "Linux";    
    public static final String DRIVERDIR = "./lib/";
    public static final String DISABLEJSR199 = "org.apache.jasper.compiler.disablejsr199";
    public static final String WEPPAPPDIR = "META-INF/webapp";
    
    public static final String DBDRIVER_MYSQL = "com.mysql.jdbc.Driver";
    public static final String DBDRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
    public static final String DBDRIVER_H2 = "org.h2.Driver";
    public static final String DBDRIVER_POSTGRES = "org.postgresql.Driver";
    
    public static final String PARAMETER_EXPRESSION = "%PARAMETER";
}

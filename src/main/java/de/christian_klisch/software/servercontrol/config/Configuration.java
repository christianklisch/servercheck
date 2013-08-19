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
}

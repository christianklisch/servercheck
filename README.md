# ServerControl

Open Source tool to view server and service state and control server in your company created and maintained by Christian Klisch.

## Quick start

There are three options to use ServerControl

* Download the latest release
* Build from sourcecode

Don't forget to configure
* ServerControl configuration
* XML Tasks

## Download and Installation

Download the latest release from github. 

### Start ServerControl

Switch to your install directory 'servercontrol'. Start from commandline with:
```
# Linux
sh ./bin/servercontrol

# Windows:
.\bin\servercontrol.bat

```

## Build from sourcecode

Clone sourcecode and rebuild the project:

```
mvn clean validate package appassembler:assemble
```


### Start ServerControl

After assembling sourcecode you have to copy the 'config' and the 'xml' directories to target/appassembler/bin/. After copy you can start the tool with commandline:

```
# Linux
sh target/appassembler/bin/servercontrol

# Windows:
target\appassembler\bin\servercontrol.bat

```

## Configuration

### Tool ServerControl in config.properties

You'll find the tool configuration in file './bin/config/config.properties' with parameters:
* port = TCP-Port of webserver for controlling server and services (e.g. 8080)
* idle_time = Pause between service checks in ms (e.g. 5000 ms)
* xml_path = Path to your XML-files with task configurations (e.g. ./xml/ or /nas/servercontrol/xml/)

### Task configuration in XML-file

In the xml-directory you can create a config for each task. 

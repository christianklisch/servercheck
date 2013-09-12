# KServerControl [![Build Status](https://travis-ci.org/christianklisch/servercontrol.png?branch=master)](https://travis-ci.org/christianklisch/servercontrol)

Open Source tool to view server and service state and control and monitor server in your company created and maintained by Christian Klisch.

## Quick start

There are two options to use KServerControl

* Download the latest release
* Build from sourcecode

Don't forget to configure
* KServerControl configuration
* XML Tasks
* Webinterface template

Who use it?
* Serveradministrator/Operator - Install tool and configure tasks monitoring server and processes
* Serveradministrator/Operator - View server and service status / control services
* User/Department - View process status / control processes

## Download and Installation

Download the latest release from [github project page](http://christianklisch.github.io/servercontrol). 

### Start KServerControl

Switch to your install directory 'kservercontrol'. Start from commandline with (first make linux executeable with: chmod 775 ./bin/kservercontrol):
```
# Linux
$ sh ./bin/kservercontrol

# Windows:
> .\bin\kservercontrol.bat

```

## Build from sourcecode

Clone sourcecode and rebuild the project with maven:

```
$ mvn clean validate package appassembler:assemble
```


### Start KServerControl

After assembling sourcecode you have to copy the 'config' and the 'xml' directories to target/appassembler/bin/. After copy you can start the tool with commandline:

```
# Linux
$ sh target/appassembler/bin/kservercontrol

# Windows:
> target\appassembler\bin\kservercontrol.bat

```
### Launch in Browser

Open the webinterface on your server at URL: http://your-server:port/list

### Launch via SOAP

Access via SOAP at URL: http://your-server:port/SoapService?wsdl

This webservice includes following methods:
* getAllViews() - returns a list with common details to each view task
* getTaskDetail(id) - returns details to specific task id (set id as string in parameter)

Following attributes are provided (only text):
* commandText
* descriptionText
* idText
* lastDateText (date only)
* lastTimeText (time only)
* lastResultText
* statusText

## Configuration

### Tool KServerControl in config.properties

You'll find the tool configuration in file './bin/config/config.properties' with parameters:
* port = TCP-Port of webserver for controlling server and services (e.g. 8080)
* idle_time = Pause between service checks in ms (e.g. 5000 ms)
* xml_path = Path to your XML-files with task configurations (e.g. ./xml/ or /nas/kservercontrol/xml/)

### Task configuration in XML-file

In the xml-directory you can create a config for each task. There are different types of tasks:
* View-command to execute a command to check a service
* Execute-command to execute a command to control a service
* View-SQL to execute a SELECT-SQL
* Execute-SQL to run a SQL-Command

#### View-command

An example XML-File for a view-command:

```
<CommandView>
  <filename>r0.xml</filename>
  <id>r0</id>
  <description>Skript returning value 0 WIN-ONLY</description>
  <command>
    @echo off
    echo 0
  </command>
  <lastResult>0</lastResult>
  <lastExecute>
    <time>137677706722</time>
    <timezone>Europe/Berlin</timezone>
  </lastExecute>
  <regexOk>0</regexOk>
  <regexWarn>1</regexWarn>
  <targetOS>Windows</targetOS>
</CommandView>
```

Description of the tags:
* filename = Filename of the XML-configuration, is set automatically
* id = first part of filename without extension, must be the same name
* description = Description of the task
* command = Command or small skript to execute
* lastResult = last commandresult, set by KServerControl
* lastExecute = last timestamp of execution, set by KServerControl
* regexOK = regular expression to check for for correct execution result (lastResult)
* regexWarn = regular expression to check for for warning execution result (lastResult)
* targetOS = system, where to run script (Windows / Linux)

If the regular expressions of 'regexOK' and 'regexWarn' doesn't match the 'lastResult', an error-icon will be shown on webinterface.


#### Execute-command

An example XML-File for a execute-command:

```
<CommandExec>
  <filename>r1.xml</filename>
  <id>r1</id>
  <description>Skript starting calculator WIN-ONLY</description>
  <command>
    @echo off
    start calc
  </command>
  <lastResult>0</lastResult>
  <lastExecute>
    <time>137677709722</time>
    <timezone>Europe/Berlin</timezone>
  </lastExecute>
  <regexOk>0</regexOk>
  <regexWarn>1</regexWarn>
  <targetOS>Windows</targetOS>
</CommandView>
```

Description of the tags:
* filename = Filename of the XML-configuration, is set automatically
* id = first part of filename without extension, must be the same name
* description = Description of the task
* command = Command or small skript to execute
* lastResult = last commandresult, set by KServerControl
* lastExecute = last timestamp of execution, set by KServerControl
* regexOK = regular expression to check for for correct execution result (lastResult)
* regexWarn = regular expression to check for for warning execution result (lastResult)
* targetOS = system, where to run script (Windows / Linux)

If the regular expressions of 'regexOK' and 'regexWarn' doesn't match the 'lastResult', an error-icon will be shown on webinterface.

#### Remote execution

To execute a command on a remote server add following tags:
```
  ...
  <sshserver>my-ssh-server.domain</sshserver>
  <sshuser>root</sshuser>
  <sshpassword>password</sshpassword>
  <sshdirectory>/home/sshuser/tmp/</sshdirectory>
  ...
```

Description of the tags:
* sshserver = hostname of the remote system
* sshuser = loginname for ssh
* sshpassword = password for sshuser
* sshdirectory = directory for execution of given command

The command of the command-tag will be copied via ssh to the remote system and execute on it. The command result will be saved in the attribute 'lastResult'. Only Linux remote systems supported.


#### View SQL

An example XML-File for a sql-select-command:

```
<sqlView>
  <filename>s1.xml</filename>
  <id>s1</id>  
  <command>SELECT COUNT(*) FROM testtable</command>
  <description>View the entry count in table testtable</description>  
  <lastResult>12</lastResult>
  <lastExecute>
    <time>137709614110</time>
    <timezone>Europe/Berlin</timezone>
  </lastExecute>
  <databaseType>MySQL</databaseType>
  <dbuser>root</dbuser>
  <dbhost>localhost</dbhost>
  <dbpassword></dbpassword>
  <dbport>3306</dbport>
  <dbname>testdb</dbname>
  <regexOk>0</regexOk>
  <regexWarn>1</regexWarn>  
</sqlView>
```

Description of the tags:
* filename = Filename of the XML-configuration, is set automatically
* id = first part of filename without extension, must be the same name
* description = Description of the SQL
* command = SQL select-command to execute
* lastResult = last sql-result, set by KServerControl
* lastExecute = last timestamp of execution, set by KServerControl
* regexOK = regular expression to check for for correct execution result (lastResult)
* regexWarn = regular expression to check for for warning execution result (lastResult)
* databaseType = Kind of DB: (MySQL, Oracle, Postgre, H2)
* dbuser = username for DB
* dbpassword = password for dbuser
* dbhost = hostname of DB
* dbname = name of DB on host
* dbport = TCP Port of DB host

Note: Oracle can only connect to instances and not to services.

#### Execute SQL

An example XML-File for a sql-execute-command:

```
<sqlExec>
  <filename>s1.xml</filename>
  <id>s1</id>  
  <command>DELETE FROM testtable</command>
  <description>Delete all entries in table testtable</description>  
  <lastResult>12</lastResult>
  <lastExecute>
    <time>137709614110</time>
    <timezone>Europe/Berlin</timezone>
  </lastExecute>
  <databaseType>MySQL</databaseType>
  <dbuser>root</dbuser>
  <dbhost>localhost</dbhost>
  <dbpassword></dbpassword>
  <dbport>3306</dbport>
  <dbname>testdb</dbname>
  <regexOk>0</regexOk>
  <regexWarn>1</regexWarn>  
</sqlExec>
```

Description of the tags:
* filename = Filename of the XML-configuration, is set automatically
* id = first part of filename without extension, must be the same name
* description = Description of the SQL
* command = SQL execute-command to execute
* lastResult = last sql-result, set by KServerControl
* lastExecute = last timestamp of execution, set by KServerControl
* regexOK = regular expression to check for for correct execution result (lastResult)
* regexWarn = regular expression to check for for warning execution result (lastResult)
* databaseType = Kind of DB: (MySQL, Oracle, Postgre, H2)
* dbuser = username for DB
* dbpassword = password for dbuser
* dbhost = hostname of DB
* dbname = name of DB on host
* dbport = TCP Port of DB host

Note: Oracle can only connect to instances and not to services.

#### Parameter in execution tasks

Requesting a execution task in the webinterface will open a modal window asking for a parameter to set. Use one parameter (expression = %PARAMETER) in the command-Tag of every execution task:
```
	<command>start programm checkit.exe -p %PARAMETER</command>
```


### Webinterface template

The webinterface template is saved in './bin/config/template.xhtml'. You can use HTML-Tags with style-attributes to design you own overview. The layout is embedded into the [Bootstrap 2.3.2](http://getbootstrap.com/2.3.2/) div-class 'container' (look the markup for more details). To insert task information or control buttons use the [moustache](http://mustache.github.io/) syntax like in following examples.  

You can design the template.xhtml for your needs. This markup will be included between the body-tags of the webinterface.
With the mustache syntax you can put task information on the webinterface. The default view can be included like the task r0.xml (e.g. in a bootstrap div-tag):

```
			<div class="well well-small span5">
			{{# views }}
			  {{{ r0 }}}
			{{/ views }}	
			</div>	
```

You have to use the container 'views' with the tags '{{# views }}' and '{{/ views }}' around the area showing task information. The tag '{{{ r0 }}}' prints the default view on your webinterface.
To customize the order or count of information call attributes of the task (e.g. r0):

```
			{{# views }}
			  {{{ r0 }}}
			  <p>The command: {{r0.commandText}}</p>
			  <p>Last execution time: {{r0.lastTimeText}}</p>
			{{/ views }}	
```

You can access following task attributes like '{{r0.commandText}}' (task id + dot + attribute):
* commandText
* descriptionText
* idText
* lastDateText (date only)
* lastTimeText (time only)
* lastResultText
* statusText
* lastResultHTML
* requestButtonHTML (shows button to refresh result or restart execution command)
* statusImageHTML (shows a red, yellow or green icon - matching regular expressions)
* lastDateHTML (date only)
* lastTimeHTML (time only)

The postfixes 'Text' and 'HTML' differs in additional HTML-DIV-Tags for webpage

Be free to build own views with lists, tables, sections, tabs, ... For more syntax details read the manual and documentation of mustache and bootstrap. 

### More Information

Why is the first letter of KServerControl a 'K'? I added this letter for better SEO because of the generic tool name.

## Roadmap

Version 0.1
* XML configuration
* Database tasks
* Commandline tasks
* Commandline via SSH

Version 0.2 (not ready)
* Request task status via SOAP
* Add user-parameter for execution task commands
* Add Ajax support
* Add parallel processing of Commandline tasks

## Authors

**Christian Klisch - [Homepage](http://www.christian-klisch.de)**

+ [http://github.com/christianklisch](http://github.com/christianklisch) 


## Copyright and license

Copyright 2013 Christian Klisch, released under [the GPL](LICENSE).

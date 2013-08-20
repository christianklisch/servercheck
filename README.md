# ServerControl

Open Source tool to view server and service state and control server in your company created and maintained by Christian Klisch.

## Quick start

There are two options to use ServerControl

* Download the latest release
* Build from sourcecode

Don't forget to configure
* ServerControl configuration
* XML Tasks
* Webinterface template

## Download and Installation

Download the latest release from [github project page](http://christianklisch.github.io/servercontrol). 

### Start ServerControl

Switch to your install directory 'servercontrol'. Start from commandline with:
```
# Linux
$ sh ./bin/servercontrol

# Windows:
> .\bin\servercontrol.bat

```

## Build from sourcecode

Clone sourcecode and rebuild the project:

```
$ mvn clean validate package appassembler:assemble
```


### Start ServerControl

After assembling sourcecode you have to copy the 'config' and the 'xml' directories to target/appassembler/bin/. After copy you can start the tool with commandline:

```
# Linux
$ sh target/appassembler/bin/servercontrol

# Windows:
> target\appassembler\bin\servercontrol.bat

```

## Configuration

### Tool ServerControl in config.properties

You'll find the tool configuration in file './bin/config/config.properties' with parameters:
* port = TCP-Port of webserver for controlling server and services (e.g. 8080)
* idle_time = Pause between service checks in ms (e.g. 5000 ms)
* xml_path = Path to your XML-files with task configurations (e.g. ./xml/ or /nas/servercontrol/xml/)

### Task configuration in XML-file

In the xml-directory you can create a config for each task. There are different types of tasks:
* View-command to execute a command to check a service
* Execute-command to execute a command to control a service

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
    <time>1376777069722</time>
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
* lastResult = last commandresult, set by ServerControl
* lastExecute = last timestamp of execution, set by ServerControl
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
    <time>1376777069722</time>
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
* lastResult = last commandresult, set by ServerControl
* lastExecute = last timestamp of execution, set by ServerControl
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

The command of the command-tag will be copied via ssh to the remote system and execute on it. The command result will be saved in the attribute 'lastResult'.

### Webinterface template

tbd

## Authors

**Christian Klisch**

+ [http://github.com/christianklisch](http://github.com/christianklisch)


## Copyright and license

Copyright 2013 Christian Klsich, Inc under [the GPL](LICENSE).

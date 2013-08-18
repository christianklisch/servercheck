# ServerControl
===========

Open Source tool to view server and service state and control server in your company created and maintained by Christian Klisch.

## Quick start

There are three options to use ServerControl

* Download the latest release
* Build from sourcecode

## Download and Installation

Download the latest release from github. 

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

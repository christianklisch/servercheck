package de.christian_klisch.software.servercontrol.service.impl;

import de.christian_klisch.software.servercontrol.model.SqlExec;
import de.christian_klisch.software.servercontrol.model.Task;
import de.christian_klisch.software.servercontrol.service.ProcessorIF;

public class SqlExecProcessor extends AbstractSqlProcessor implements ProcessorIF {

    public void execute(Task task) {
	// TODO Auto-generated method stub

    }

    public String getClassType() {
	return SqlExec.class.toString();
    }
}

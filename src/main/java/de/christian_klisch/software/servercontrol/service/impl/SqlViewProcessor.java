package de.christian_klisch.software.servercontrol.service.impl;

import de.christian_klisch.software.servercontrol.model.SqlView;
import de.christian_klisch.software.servercontrol.model.Task;
import de.christian_klisch.software.servercontrol.service.ProcessorIF;

public class SqlViewProcessor extends AbstractSqlProcessor implements ProcessorIF {

    public void execute(Task task) {
	super.execute(task);
    }

    public String getClassType() {
	return SqlView.class.toString();
    }

}

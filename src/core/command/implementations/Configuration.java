package core.command.implementations;

import core.command.CmdExecutor;
import core.command.data.Cmd;
import core.command.events.CmdEvent;

public class Configuration extends CmdExecutor {

	@Override
	protected boolean mainCommand(CmdEvent event) {
		sendHelpPage(event);
		return true;
	}

	@Override
	protected boolean defaultCommand(CmdEvent event) {
		return true;
	}

	@Cmd(args = {
			"save" }, permission = "configuration.save", description = "This saves the configuration files that are registered")
	public boolean save(CmdEvent event) {
		_core.saveConfigurations();
		event.sendMessage("You have saved the configuration");
		return true;
	}

	@Cmd(args = {
			"save" }, permission = "configuration.save", overLength = true, description = "This saves the configuration files that are registered")
	public boolean saveExact(CmdEvent event) {
		if (_core.saveConfiguration(event.getArgs(1))) {
			event.sendMessage("You have saved the configuration");
		} else {
			event.sendMessage("That configuration does not exist!");
		}
		return true;
	}

	@Cmd(args = { "list" }, permission = "configuration.list", description = "This lists all configuration files")
	public boolean list(CmdEvent event) {
		StringBuilder list = new StringBuilder();
		for (core.config.Configuration config : _core.getConfigurations()) {
			list.append(", " + config.getFileName());
		}
		event.sendMessage("Configuration Name List: " + list.toString().replaceFirst(", ", ""));
		return true;
	}
}
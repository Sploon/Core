package core.exceptions.command;

import core.command.data.CmdType;

/**
 * This is used when the command type and your event paramater do not match
 * 
 * @author PhantomUnicorns
 */
@SuppressWarnings("serial")
public class InvalidCommandTypeEvent extends RuntimeException {

	public InvalidCommandTypeEvent(CmdType expected, CmdType got) {
		super("Expecting [" + expected.name() + "] but got [" + got.name() + "]");
	}
}
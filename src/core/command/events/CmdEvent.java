package core.command.events;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import core.access.MainAccess;

/**
 * When a command is executed this is ran
 * 
 * @author PhantomUnicorns
 */
public class CmdEvent extends MainAccess {

	protected CommandSender _sender;
	protected Command _command;
	protected String[] _args;

	protected boolean _return;

	public CmdEvent(CmdEvent event) {
		this._sender = event._sender;
		this._command = event._command;
		this._args = event._args;
		this._return = event._return;
	}

	public CmdEvent(CommandSender sender, Command command, String... args) {
		this._sender = sender;
		this._command = command;
		this._args = args;
		this._return = true;
	}

	/**
	 * @return The sender
	 */
	public CommandSender getSender() {
		return this._sender;
	}

	/**
	 * @return The command
	 */
	public Command getCommand() {
		return this._command;
	}

	/**
	 * @return The label
	 */
	public String getLabel() {
		return this._command.getLabel();
	}

	/**
	 * @return The arguments
	 */
	public String[] getArgs() {
		return this._args;
	}
	
	/**
	 * @return The arguments as one string
	 */
	public String getArgs(int start) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < this._args.length; ++i) {
			sb.append(" " + this._args[i]);
		}
		return sb.toString().replaceFirst(" ", "");
	}
	
	/**
	 * @return The arguments as one string
	 */
	public String getArgs(int start, int end) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i <= end; ++i) {
			sb.append(" " + this._args[i]);
		}
		return sb.toString().replaceFirst(" ", "");
	}

	/**
	 * @return The arguments length
	 */
	public int getArgsLength() {
		return this._args.length;
	}

	/**
	 * Tests if the length of the arguments is equal to length
	 * 
	 * @param length
	 *            The length to test
	 * @return If true
	 */
	public boolean isLength(int length) {
		return this._args.length == length;
	}

	/**
	 * Tests if the length of the arguments is equal to or greater then length
	 * 
	 * @param length
	 *            The length to test
	 * @return If true
	 */
	public boolean isAtLeastLength(int length) {
		return this._args.length >= length;
	}

	/**
	 * Sets the return value (default is true)
	 * 
	 * @param newReturn
	 *            The new return value
	 */
	public void setReturn(boolean newReturn) {
		this._return = newReturn;
	}

	/**
	 * @return The current return value
	 */
	public boolean getReturn() {
		return this._return;
	}
	
	public void sendMessage(String message) {
		this._sender.sendMessage(ChatColor.translateAlternateColorCodes('&', _MAIN_COLOR + message));
	}
}
package core.command.data;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import core.command.events.CmdEvent;

/**
 * The meta data for methods; their arguments, length, overlength, and command
 * type
 * 
 * @author PhantomUnicorns
 */
public class CmdMetaData {

	private String[] _args;
	private String _desc, _permission;
	private CmdType _type;

	private int _length;
	private boolean _overLength, _sendDefaultMsgs;

	public CmdMetaData(String[] args, String desc, String permission, int length, boolean overLength,
			boolean sendDefaultMsgs, CmdType type) {
		this._args = args;
		this._desc = desc;
		this._permission = permission;
		this._length = length;
		this._overLength = overLength;
		this._sendDefaultMsgs = sendDefaultMsgs;
		this._type = type;
	}

	/**
	 * @return The args required to be done in the command
	 */
	public String[] getArgs() {
		return this._args;
	}

	/**
	 * @return The desc of the command
	 */
	public String getDesc() {
		return this._desc;
	}

	/**
	 * @return The permission of the command
	 */
	public String getPermission() {
		return this._permission;
	}

	/**
	 * @return The length of the args required
	 */
	public int getLength() {
		return this._length;
	}

	/**
	 * @return If the command can use more args then required
	 */
	public boolean getOverLength() {
		return this._overLength;
	}

	/**
	 * @return If you it should send default messages like no permission
	 */
	public boolean getSendDefaultMsgs() {
		return this._sendDefaultMsgs;
	}

	/**
	 * @return Whom is the command for
	 */
	public CmdType getCommandType() {
		return this._type;
	}

	public boolean hasPermission(CommandSender sender) {
		if (!this._permission.equals("")) {
			return sender.hasPermission(this._permission);
		} else {
			return true;
		}
	}

	/**
	 * If this CmdMetaData is applicable to the CmdEvent
	 * 
	 * @param event
	 *            The event
	 * @return If true
	 */
	public boolean isSimilar(CmdEvent event) {
		String[] args = event.getArgs();
		CommandSender sender = event.getSender();
		if (this._type == CmdType.PLAYER && !(sender instanceof Player)) {
			return false;
		}
		if (this._type == CmdType.CONSOLE && !(sender instanceof ConsoleCommandSender)) {
			return false;
		}
		if (this._overLength) {
			if (_length <= args.length) {
				for (int i = 0; i < _length; ++i) {
					if (!this._args[i].equalsIgnoreCase(args[i])) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			if (_length == args.length) {
				for (int i = 0; i < _length; ++i) {
					if (!this._args[i].equalsIgnoreCase(args[i])) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		}
	}
}
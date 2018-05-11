package core.command.events;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import core.command.data.CmdType;
import core.exceptions.command.InvalidCommandTypeEvent;

/**
 * A type of CmdEvent; this is used when the command type equals player
 * 
 * @author PhantomUnicorns
 */
public class PlayerCmdEvent extends CmdEvent {

	private Player _player;

	public PlayerCmdEvent(CmdEvent event) {
		super(event);
		if (this._sender instanceof Player) {
			this._player = (Player) this._sender;
		} else {
			throw new InvalidCommandTypeEvent(CmdType.PLAYER, CmdType.CONSOLE);
		}
	}

	public PlayerCmdEvent(CommandSender sender, Command command, String[] args) {
		super(sender, command, args);
		if (sender instanceof Player) {
			this._player = (Player) sender;
		} else {
			throw new InvalidCommandTypeEvent(CmdType.PLAYER, CmdType.CONSOLE);
		}
	}

	/**
	 * @return The player that executed the command
	 */
	public Player getPlayer() {
		return this._player;
	}
}
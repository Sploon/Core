package core.command;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import core.access.CommandAccess;
import core.command.data.Cmd;
import core.command.data.CmdMetaData;
import core.command.data.CmdType;
import core.command.events.CmdEvent;
import core.utils.MiscMethods;

/**
 * The main command executor abstract class
 * 
 * @author PhantomUnicorns
 */
public abstract class CmdExecutor extends CommandAccess implements CommandExecutor {

	private Map<CmdMetaData, Method> _methods;

	public CmdExecutor() {
		this._methods = new HashMap<>();
		for (Method method : this.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(Cmd.class)) {
				Parameter[] params = method.getParameters();
				if (params.length == 1) {
					Class<?> type = params[0].getType();
					if (type == CmdEvent.class || type.getSuperclass() == CmdEvent.class) {
						method.setAccessible(true);
						Cmd annotation = method.getAnnotation(Cmd.class);
						this._methods.put(new CmdMetaData(annotation.args(), annotation.description(),
								annotation.permission(), annotation.args().length, annotation.overLength(),
								annotation.sendDefaultMessages(), annotation.type()), method);
					}
				}
			}
		}
		_core.registerCommand(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {
		CmdEvent event = new CmdEvent(sender, command, args);
		boolean done = false;
		for (CmdMetaData data : this._methods.keySet()) {
			if (data.isSimilar(event)) {
				if (data.hasPermission(sender)) {
					Method method = this._methods.get(data);
					try {
						Class<?> type = method.getParameters()[0].getType();
						if (method.getReturnType() == boolean.class) {
							return (boolean) method.invoke(this, getEvent(type, sender, command, args));
						} else {
							method.invoke(this, getEvent(type, sender, command, args));
							done = true;
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					if (data.getSendDefaultMsgs()) {
						event.sendMessage("&cYou do not have permission to do that!");
					}
					return true;
				}
			}
		}
		if (!done) {
			if (args.length == 0) {
				return mainCommand(event);
			} else {
				return defaultCommand(event);
			}
		} else {
			return true;
		}
	}

	/**
	 * The main command, if args.length equals 0 and no other method is ran
	 * 
	 * @param event
	 *            The event
	 * @return If the command was successful in giving output
	 */
	protected abstract boolean mainCommand(CmdEvent event);

	/**
	 * The main command, if args.length greater then or equal to 1 and no other
	 * method is ran
	 * 
	 * @param event
	 *            The event
	 * @return If the command was successful in giving output
	 */
	protected abstract boolean defaultCommand(CmdEvent event);

	/**
	 * Sends the help page to the user in sender. Automatically gets the data from
	 * the @Cmd annotation in methods
	 * 
	 * @param event
	 *            The event; this is used to ensure that the sender has access to
	 *            the command
	 * @param format
	 *            The format of the help message
	 */
	protected void sendHelpPage(CmdEvent event) {
		sendHelpPage(event, "%cmd &a= %mc%desc");
	}

	/**
	 * Sends the help page to the user in sender. Automatically gets the data from
	 * the @Cmd annotation in methods
	 * 
	 * @param event
	 *            The event; this is used to ensure that the sender has access to
	 *            the command
	 * @param format
	 *            The format of the help message
	 */
	protected void sendHelpPage(CmdEvent event, int page) {
		sendHelpPage(event, "%cmd &a= %mc%desc", page);
	}

	/**
	 * Sends the help page to the user in sender. Automatically gets the data from
	 * the @Cmd annotation in methods
	 * 
	 * @param event
	 *            The event; this is used to ensure that the sender has access to
	 *            the command
	 * @param format
	 *            The format of the help message
	 */
	protected void sendHelpPage(CmdEvent event, String format) {
		sendHelpPage(event, format, 1);
	}

	/**
	 * Sends the help page to the user in sender. Automatically gets the data from
	 * the @Cmd annotation in methods
	 * 
	 * @param event
	 *            The event; this is used to ensure that the sender has access to
	 *            the command
	 * @param format
	 *            The format of the help message
	 */
	protected void sendHelpPage(CmdEvent event, String format, int page) {
		List<CmdMetaData> metaDatas = new ArrayList<>(this._methods.keySet());
		int maxPages = metaDatas.size() / _CONTENT_PER_PAGE + (metaDatas.size() % _CONTENT_PER_PAGE == 0 ? 0 : 1);
		event.sendMessage("&a<&8---&aHelp Page " + page + "/" + maxPages + "&8---&a>");
		for (int i = _CONTENT_PER_PAGE * (page - 1); i < _CONTENT_PER_PAGE * page; ++i) {
			if (metaDatas.size() > i) {
				CmdMetaData metaData = metaDatas.get(i);
				if (!metaData.getDesc().equals("")) {
					if (metaData.hasPermission(event.getSender())) {
						if (metaData.getCommandType() == CmdType.PLAYER) {
							if (event.getSender() instanceof Player) {
								StringBuilder args = new StringBuilder();
								for (String arg : metaData.getArgs()) {
									args.append(" " + arg);
								}
								if (metaData.getOverLength()) {
									args.append(" <args>");
								}
								event.sendMessage(format.replaceAll("%cmd", event.getLabel() + args.toString())
										.replaceAll("%desc", metaData.getDesc()).replaceAll("%mc", MAIN_COLOR));
							}
						} else if (metaData.getCommandType() == CmdType.CONSOLE) {
							if (event.getSender() instanceof ConsoleCommandSender) {
								StringBuilder args = new StringBuilder();
								for (String arg : metaData.getArgs()) {
									args.append(" " + arg);
								}
								if (metaData.getOverLength()) {
									args.append(" <args>");
								}
								event.sendMessage(format.replaceAll("%cmd", event.getLabel() + args.toString())
										.replaceAll("%desc", metaData.getDesc()).replaceAll("%mc", MAIN_COLOR));
							}
						} else {
							StringBuilder args = new StringBuilder();
							for (String arg : metaData.getArgs()) {
								args.append(" " + arg);
							}
							if (metaData.getOverLength()) {
								args.append(" <args>");
							}
							event.sendMessage(format.replaceAll("%cmd", event.getLabel() + args.toString())
									.replaceAll("%desc", metaData.getDesc()).replaceAll("%mc", MAIN_COLOR));
						}
					}
				}
			}
		}
		event.sendMessage("&a<&8---&aHelp Page " + page + "/" + maxPages + "&8---&a>");
	}

	/**
	 * Gets the event associated with the parameters
	 * 
	 * @param clazz
	 *            The event class
	 * @param sender
	 *            The sender
	 * @param command
	 *            The command
	 * @param args
	 *            The arguments
	 * @return The event in type of clazz
	 */
	private Object getEvent(Class<?> clazz, CommandSender sender, Command command, String[] args) {
		try {
			return clazz.getConstructor(CommandSender.class, Command.class, String[].class).newInstance(sender, command,
					args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Cmd(args = { "help" }, overLength = true, description = "This is the help page for this command")
	public boolean help(CmdEvent event) {
		if (event.getArgsLength() == 1) {
			sendHelpPage(event, 1);
		} else {
			String page = event.getArgs()[0];
			if (MiscMethods.isInteger(page)) {
				sendHelpPage(event, Integer.parseInt(page));
			} else {
				event.sendMessage("&cThat is an invalid number!");
			}
		}
		return true;
	}
}
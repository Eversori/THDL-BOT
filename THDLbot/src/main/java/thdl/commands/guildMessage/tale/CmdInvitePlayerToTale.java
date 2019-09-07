package thdl.commands.guildMessage.tale;


import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import thdl.commands.guildMessage.Command;
import thdl.commands.guildMessage.ILogGuildCmd;
import thdl.lib.discord.ThdlMember;
import thdl.lib.factories.discord.ThdlMemberFactory;
import thdl.lib.factories.rpg.TaleFactory;
import thdl.lib.rpg.Tale;
import thdl.util.DirectWriter;
import thdl.util.DiscordWriter;
import thdl.util.IdParser;
import thdl.util.log.LogMessageType;
import thdl.util.log.Logger;
import thdl.util.log.LoggerManager;


public class CmdInvitePlayerToTale implements Command
{

	private Logger			log		= null;
	private DiscordWriter	writer	= null;
	private Tale			tale	= null;

	/**
	 * -invite [Ping User] ([Ping User] ...)
	 */

	@Override
	public boolean called(String[] args, GuildMessageReceivedEvent e)
	{
		log = LoggerManager.getLogger(ILogGuildCmd.NUM, ILogGuildCmd.NAME);
		writer = new DiscordWriter(e);

		boolean isOk = false;
		String authorId = e.getAuthor().getId();
		DirectWriter authorWrite = null;

		tale = TaleFactory.getInstance().getTale(e.getChannel());

		try
		{
			authorWrite = new DirectWriter(e.getAuthor());
		}
		catch (Exception e1)
		{
			log.logException(this.toString(), ILogGuildCmd.OPEN_DM_CHANNEL, e1.getMessage());
		}

		if (tale != null)
		{
			if (tale.isStoryteller(authorId))
			{
				if (!tale.isStarted())
				{
					if (args.length >= 1)
					{
						isOk = true;
					}
					else
					{
						log.logInfo(this.toString(), ILogGuildCmd.WRONG_FORMAT, ILogGuildCmd.WRONG_PATTERN_CMD);
						writer.writeInfo("You should use the format -invite [@username] ([@username] ...");
						isOk = false;
					}
				}
				else
				{
					log.logInfo(this.toString(), ILogGuildCmd.TALE_STARTED,
							"Tried to invite users to the tale, even so its running");
					writer.writeInfo("I'm sorry, but you can't invite any people as long the tale is running");
					isOk = false;
				}

			}
			else
			{
				log.logErrorWithoutMsg(this.toString(), ILogGuildCmd.UNAUTHORIZED_USE);
				writer.writeError(ILogGuildCmd.ERROR_NOT_AUTHORIZED_IN_TALE);
				isOk = false;
			}
		}
		else
		{
			log.logErrorWithoutMsg(this.toString(), ILogGuildCmd.NO_TALE_FOUND);
			authorWrite.writeMsg(ILogGuildCmd.ERROR_NOT_A_TALE_CHANNEL);
			isOk = false;
		}

		return isOk;
	}

	/**
	 * TODO: SAVE
	 */
	@Override
	public void action(String[] args, GuildMessageReceivedEvent e) throws Exception
	{
		String logMsg = "";
		String msg = "";

		for (int i = 0; i < args.length; i++)
		{
			String id = IdParser.parse(args[i]);
			ThdlMember member = ThdlMemberFactory.getInstance().getMember(id);

			if (member != null)
			{
				if (member.isAllowed())
				{

					if (!member.isInvitedTo(tale))
					{
						String dmMsg = "";
						member.addInvitedTo(tale);
						DirectWriter dm = new DirectWriter(member);

						dmMsg = "Hello! You were invited to play " + tale.getTaleName() + "\n";
						dmMsg = "Please answer the invitation.\n";
						dmMsg = "If you want to join, type 'accept " + tale.getTaleName() + "', if not, type 'decline"
								+ tale.getTaleName() + "' under this message";

						dm.writeMsg(dmMsg);

						msg = msg + args[i] + " was invited to the tale" + "\n";

						logMsg = args[i] + ILogGuildCmd.INVITED + tale.getTaleName();
						log.logState(this.toString(), logMsg);
					}
				}
				else
				{
					logMsg = args[i] + ILogGuildCmd.CANT_PLAY;
					log.logErrorWithoutMsg(this.toString(), logMsg);

					msg = msg + args[i] + " couln't be invited, because he/she is not a ~Fighter or higher\n";
				}
			}
			else
			{
				logMsg = args[i] + ILogGuildCmd.NOT_A_MEMBER;
				log.logErrorWithoutMsg(this.toString(), logMsg);

				msg = msg + args[i] + " couln't be invited, because this is not a member!\n";
			}
		}

		writer.writeInfo(msg);
	}

	@Override
	public void executed(boolean success, GuildMessageReceivedEvent event)
	{
		if (success)
		{
			log.addMessageToLog(this.toString(), LogMessageType.STATE, ILogGuildCmd.CMD_EXE,
					ILogGuildCmd.CMD_INVITE_SUCCESS);
		}
		else
		{
			log.addMessageToLog(this.toString(), LogMessageType.STATE, ILogGuildCmd.CMD_EXE,
					ILogGuildCmd.CMD_INVITE_FAILED);
		}

		writer = null;
		log = null;
		tale = null;
	}

	@Override
	public String help()
	{
		// TODO Auto-generated method stub
		return null;
	}

}

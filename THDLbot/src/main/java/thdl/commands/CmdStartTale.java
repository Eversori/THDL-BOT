package thdl.commands;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import thdl.bot.DiscordWriter;
import thdl.rpg.lib.RoundTurn;
import thdl.rpg.lib.Tale;
import thdl.rpg.lib.Tales;
import thdl.util.ErrorMessages;

public class CmdStartTale implements Command
{
	private DiscordWriter	writer	= null;
	private Tale			tale	= null;

	@Override
	public boolean called(String[] args, GuildMessageReceivedEvent e)
	{
		boolean isOk = false;
		writer = openWriter(e);
		TextChannel txt = e.getChannel();
		tale = Tales.getTale(txt);
		User author = e.getAuthor();

		if (tale != null)
		{
			if (tale.isThisMyStoryTeller(author))
			{
				if (!tale.getIsStarted())
				{
					if (tale.getPlayerNumber() >= 2)
					{
						isOk = true;
					}
					else
					{
						System.out.println("Not enough players");
						writer.writeError("You don't have enough player in your tale");
					}
				}
				else
				{
					System.out.println(ErrorMessages.STARTED_ERROR);
					writer.writeError("This tale is already started");
				}
			}
			else
			{
				System.out.println(ErrorMessages.PERMISSION_ERROR);
				writer.writeError("You cannot start the tale, if you are not its storyteller");
			}
		}
		else
		{
			System.out.println(ErrorMessages.CHANNEL_ERROR);
			writer.writePrivate("This command was used in the wrong textchannel", author);
		}

		return isOk;
	}

	@Override
	public void action(String[] args, GuildMessageReceivedEvent e) throws Exception
	{
		tale.orderTurns();
		tale.incrementTurnCounter();
		RoundTurn turn = tale.getCurrentTurn();
		tale.setIsStarted(true);
		tale.safe();
		turn.safe();
		turn.getOwner().safePlayer();

		writer.writeInfo("The tale has started!!!");
		writer.writeInfo(turn.getOwner().getMySelf().getAsMention() + ", its your turn go on and play!!");
	}

	@Override
	public void executed(boolean success, GuildMessageReceivedEvent event)
	{
		if (success)
		{
			System.out.println("Command start was executed with success");
		}
		else
		{
			System.out.println("Command start could not be executed with success");
		}
		writer = null;
		tale = null;
	}

	@Override
	public String help()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiscordWriter openWriter(GuildMessageReceivedEvent e)
	{
		if (writer == null)
		{
			writer = new DiscordWriter(e);
		}
		return writer;
	}

}

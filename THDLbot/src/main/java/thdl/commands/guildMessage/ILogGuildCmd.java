package thdl.commands.guildMessage;


public interface ILogGuildCmd
{

	// Log
	public static String	NAME	= "GUILD_CMD-Log";
	public static String	NUM		= "LOG_GUILD_CMD";

	// Log-Cause
	public static String	NO_MEMBER				= "Unregistered author";
	public static String	NAME_IN_USE				= "Name is in use";
	public static String	UNAUTHORIZED_USE		= "Usage by Unauthorized";
	public static String	WRONG_FORMAT			= "Wrong format was used";
	public static String	CREATE_TALE_EXCEPTION	= "Exception in createTale";
	public static String	ROLE_CREATE				= "Role created";
	public static String	CHANNEL_CREATE			= "Channel created";
	public static String	NO_ROLE					= "No Role found";
	public static String	NO_CHANNEL				= "No Channel found";
	public static String	CMD_EXE					= "Command executed";
	public static String	NUMBER_TRY				= "Not a number";
	public static String	QUANTITIY_ERROR			= "Quantity is zero or out of range";

	// Log - Messages
	public static String	CMD_CREATE_PNP_SUCCESS		= "Command createPnP was executed with success";
	public static String	CMD_CREATE_PNP_FAILED		= "Command createPnP could not be executed with success";
	public static String	CMD_DICE_FOUR_SUCCESS		= "Command -d4 was executed with success";
	public static String	CMD_DICE_FOUR_FAILED		= "Command -d4 could not be executed with success";
	public static String	CMD_DICE_SIX_SUCCESS		= "Command -d6 was executed with success";
	public static String	CMD_DICE_SIX_FAILED			= "Command -d6 could not be executed with success";
	public static String	CMD_DICE_EIGHT_SUCCESS		= "Command -d8 was executed with success";
	public static String	CMD_DICE_EIGHT_FAILED		= "Command -d8 could not be executed with success";
	public static String	CMD_DICE_TEN_SUCCESS		= "Command -d10 was executed with success";
	public static String	CMD_DICE_TEN_FAILED			= "Command -d10 could not be executed with success";
	public static String	CMD_DICE_TWELVE_SUCCESS		= "Command -d12 was executed with success";
	public static String	CMD_DICE_TWELVE_FAILED		= "Command -d12 could not be executed with success";
	public static String	CMD_DICE_TWENTY_SUCCESS		= "Command -d20 was executed with success";
	public static String	CMD_DICE_TWENTY_FAILED		= "Command -d20 could not be executed with success";
	public static String	CMD_DICE_HUNDRED_SUCCESS	= "Command -d100 was executed with success";
	public static String	CMD_DICE_HUNDRED_FAILED		= "Command -d100 could not be executed with success";
	public static String	DICED						= "Diced: ";
}
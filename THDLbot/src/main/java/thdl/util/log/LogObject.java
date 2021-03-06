package thdl.util.log;


import java.time.LocalDateTime;


public class LogObject
{

	private int				logNumber;
	private String			causeObject;
	private LogMessageType	type;
	private String			cause;
	private String			message;
	private LocalDateTime	timeStamp;

	protected LogObject(int logNumber, String causeObject, LogMessageType type, String cause, String message)
	{
		super();
		this.logNumber = logNumber;
		this.causeObject = causeObject;
		this.type = type;
		this.message = message;
		this.cause = cause;
		timeStamp = LocalDateTime.now();
	}

	protected String getCauseObject()
	{
		return causeObject;
	}

	protected void setCauseObject(String causeObject)
	{
		this.causeObject = causeObject;
	}

	protected LogMessageType getType()
	{
		return type;
	}

	protected void setType(LogMessageType type)
	{
		this.type = type;
	}

	protected String getCause()
	{
		return cause;
	}

	protected void setCause(String cause)
	{
		this.cause = cause;
	}

	protected String getMessage()
	{
		return message;
	}

	protected void setMessage(String message)
	{
		this.message = message;
	}

	protected int getLogNumber()
	{
		return logNumber;
	}

	protected String getTimeStamp()
	{
		return timeStamp.toString();
	}

}

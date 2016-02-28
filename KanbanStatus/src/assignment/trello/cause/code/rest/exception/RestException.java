package assignment.trello.cause.code.rest.exception;

import org.apache.log4j.Logger;

import assignment.trello.cause.code.utils.Codes;

public class RestException extends Throwable
{
	private static final Logger	logger				= Logger.getLogger(RestException.class);

	private static final long	serialVersionUID	= 1L;

	private String				message;
	private Codes				errorCode;

	public RestException()
	{
		super();
	}

	public RestException(Codes code)
	{
		super();
		this.setErrorCode(code);
	}

	public RestException(String message)
	{
		super(message);
		this.message = message;
	}

	public RestException(Throwable cause)
	{
		super(cause);
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return message;
	}

	public Codes getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(Codes code)
	{
		this.errorCode = code;
	}

	public void printMessage(Codes code)
	{
		switch (code)
		{
			case REST_EXCEPTION:
				logger.error("Unknown rest exception occurred.");
				break;
			default:
				logger.error("Unknown exception occurred.");
				break;
		}
	}

}

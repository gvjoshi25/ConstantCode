package assignment.trello.cause.code.utils;

public enum Codes
{
	/**
	 * General Rest Exception
	 */
	REST_EXCEPTION(200),
	/**
	 * HTTP status 200 - OK
	 */
	OK(200),

	/**
	 * HTTP status 201 - Created
	 */
	CREATED(201),

	/**
	 * HTTP Status 202 - Accepted
	 */
	ACCEPTED(202);

	private final int	value;

	Codes(final int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}

}

package assignment.merchant.galaxy.guide.utils;

public class Messages
{
	public void printMessage(Codes error)
	{
		String message = getMessage(error);

		if (message != null)
			System.out.println(message);
	}

	public String getMessage(Codes code)
	{
		String message = null;

		switch (code)
		{
			case NO_INPUT:
				message = "No input was specified ! Program exited";
				break;

			case INVALID_ROMAN_STR:
				message = "wrong Roman number, voilated roman number format";
				break;

			case INCORRECT_LINE_TYPE:
				message = "Exception caused during processing due to incorrect line type supplied";
				break;

			case INVALID_INPUT:
				message = "Input format is wrong ! input discarded";
				break;

			case INVALID_ROMAN_CHAR:
				message = "Illegal character specified in roman number ! input discarded";
				break;

			case NO_IDEA:
				message = "I have no idea what you are talking about.";
				break;

			case INVALID_FILE:
				message = "File is invalid. Please provide text file with absolute path.";
				break;

			case PATH_MISSING:
				message = "Please provide input and output file path.";
				break;

			default:
				message = "Unknown exception occurs.";
				break;
		}
		return message;
	}
}

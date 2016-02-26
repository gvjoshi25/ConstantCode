package assignment.merchant.galaxy.guide;

import java.util.ArrayList;

import assignment.merchant.galaxy.guide.utils.Line;
import assignment.merchant.galaxy.guide.utils.Type;

/**
 * This class validates the line from input file
 * 
 * @author Gautam_Joshi02
 *
 */
public class ValidateLine
{
	private LineIdentifier	lineIdentifier;
	private ProcessLine		processLine;

	/**
	 * Initialize all parameters
	 */
	public ValidateLine()
	{
		lineIdentifier = new LineIdentifier();
		processLine = new ProcessLine();
	}

	/**
	 * Validate a line
	 * 
	 * @param line
	 */
	public void validate(String line)
	{
//		Identify line and get its type
		Line currentLine = this.lineIdentifier.getLineType(line);

		Type lineType = currentLine.getType();
		switch (lineType)
		{
//			Process Roman type of line
			case ASSIGNMENT_ROMAN:
				processLine.processAssignmentLine(currentLine);
				break;

//			Process Credit type of line
			case ASSIGNMENT_CREDITS:
				processLine.processCreditsLine(currentLine);
				break;

//			Process How much question type of line
			case QUESTION_HOW_MUCH:
				processLine.processHowMuchQuestion(currentLine);
				break;

//			Process How many question type of line
			case QUESTION_HOW_MANY:
				processLine.processHowManyCreditsQuestion(currentLine);
				break;

//			Process no match line
			default:
				processLine.processNoMatch();
				break;
		}
	}

	public ArrayList<String> getOutputList()
	{
		return processLine.getOutputList();
	}
}

package assignment.merchant.galaxy.guide;

import assignment.merchant.galaxy.guide.utils.Line;
import assignment.merchant.galaxy.guide.utils.Type;

/**
 * Identify each line from input file.
 * @author Gautam_Joshi02
 *
 */
public class LineIdentifier
{
	private String	p_Assigned	= "^([A-Za-z]+)( [i|I][s|S] )([I|V|X|L|C|D|M])$";
	private String	p_Credits	= "^(?i)([a-z]+)([a-z\\s]*)( is )([0-9]+) (credits)$";
	private String	p_HowMuch	= "^(?i)(how much is )(([A-Za-z\\s])+)\\?$";
	private String	p_HowMany	= "^(?i)(how many )[c|C](redits is )(([A-Za-z\\s])+)\\?$";
	private Line[]	lineArray;

	/**
	 * Initialize array of Line of each type.
	 */
	public LineIdentifier()
	{
		this.lineArray = new Line[5];
		this.lineArray[0] = new Line(Type.ASSIGNMENT_ROMAN, p_Assigned);
		this.lineArray[1] = new Line(Type.ASSIGNMENT_CREDITS, p_Credits);
		this.lineArray[2] = new Line(Type.QUESTION_HOW_MUCH, p_HowMuch);
		this.lineArray[3] = new Line(Type.QUESTION_HOW_MANY, p_HowMany);
		this.lineArray[4] = new Line(Type.NO_MATCH, null);
	}

	/**
	 * Identify line type
	 * 
	 * @param each line from input file
	 * @return Line object with its type
	 */
	public Line getLineType(String line)
	{
		line = line.trim();
		Line resultLine = null;

		for (int i = 0; i < lineArray.length; i++)
		{
			if (lineArray[i].getMatcher() != null)
			{
				lineArray[i].getMatcher().reset(line);
				if (lineArray[i].getMatcher().matches())
				{
					resultLine = lineArray[i];
					break;
				}
			}
		}
		if (resultLine == null)
		{
			resultLine = this.lineArray[4];
		}
		return resultLine;
	}
}

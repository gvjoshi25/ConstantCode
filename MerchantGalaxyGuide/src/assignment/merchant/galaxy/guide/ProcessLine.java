package assignment.merchant.galaxy.guide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import assignment.merchant.galaxy.guide.utils.Codes;
import assignment.merchant.galaxy.guide.utils.Line;
import assignment.merchant.galaxy.guide.utils.Messages;
import assignment.merchant.galaxy.guide.utils.RomanNumbers;

/**
 * Process Line by its type.
 * 
 * @author Gautam_Joshi02
 *
 */
public class ProcessLine
{
	/**
	 * Stores Roman number in map Key = Name Value = Roman
	 */
	private HashMap<String, String>		nameRomanValueMap;
	/**
	 * stores assignment for credit key = Name Value = Credit
	 */
	private HashMap<String, Integer>	creditMap;
	private Messages					message;

	/**
	 * stores output for each question.
	 */
	private ArrayList<String>			outputList;

	/**
	 * Initialize map and other objects
	 */
	public ProcessLine()
	{
		nameRomanValueMap = new HashMap<String, String>();
		creditMap = new HashMap<String, Integer>();
		message = new Messages();
		outputList = new ArrayList<String>();
	}

	/**
	 * Process assignment line for Roman number and store in map.
	 * 
	 * @param currentLine
	 */
	public void processAssignmentLine(Line currentLine)
	{
		try
		{
			nameRomanValueMap.put(currentLine.getMatcher().group(1).trim().toLowerCase(), currentLine.getMatcher().group(3).trim());
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			this.message.printMessage(Codes.INCORRECT_LINE_TYPE);
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Process questions like "How much..?"
	 * 
	 * @param currentLine
	 */
	public void processHowMuchQuestion(Line currentLine)
	{
		try
		{
//			Remove is keyword from line
			String formatted = currentLine.getMatcher().group().split("(?i)\\sis\\s")[1].trim();
			formatted = formatted.replace("?", "").trim();

//			key contains all the key words except is and ?
			String keys[] = formatted.split("\\s+");

			String romanResult = "";
			String completeResult = null;

			for (String key : keys)
			{
//				Get Roman value for each key
				String romanValue = nameRomanValueMap.get(key.toLowerCase());
				if (romanValue == null)
				{
					completeResult = this.message.getMessage(Codes.NO_IDEA);
					break;
				}
//				Concate Roman numbers
				romanResult += romanValue;
			}

			if (!romanResult.isEmpty())
			{
				romanResult = RomanNumbers.romanToArabicNumber(romanResult);
				completeResult = formatted + " is " + romanResult;
			}
			outputList.add(completeResult);
		}
		catch (Exception e)
		{
			this.message.printMessage(Codes.INCORRECT_LINE_TYPE);
			System.out.println(e.getMessage());

		}
	}

	/**
	 * Process line to define credit
	 * 
	 * @param currentLine
	 */
	public void processCreditsLine(Line currentLine)
	{
		try
		{
//			Remove is...credit words from line
			String formatted = currentLine.getMatcher().group().replaceAll("(?i)(is\\s+)|(credits\\s*)", "").trim();

//			All the keys and credit
			String[] keys = formatted.split("\\s");
			String toBeComputed = keys[keys.length - 2];

//			Now Last index is for credit specified in line.
			int value = Integer.parseInt(keys[keys.length - 1]);

			String roman = "";

//			Generate Roman number from keys
			for (int i = 0; i < keys.length - 2; i++)
			{
				roman += nameRomanValueMap.get(keys[i].toLowerCase());
			}

//			Convert Roman number to Arabic number
			int romanNumber = Integer.parseInt(RomanNumbers.romanToArabicNumber(roman));

//			Calculate Credit
			int credit = (int) (value / romanNumber);

//			Store credit in map
			creditMap.put(toBeComputed.toLowerCase(), credit);
		}
		catch (Exception e)
		{
			this.message.printMessage(Codes.INCORRECT_LINE_TYPE);
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Process Question like 'How Many Credits...?'
	 * 
	 * @param currentLine
	 */
	public void processHowManyCreditsQuestion(Line currentLine)
	{
		try
		{
//			Remove "is" keyword from line
			String formatted = currentLine.getMatcher().group().split("(?i)(\\sis\\s)")[1];
			formatted = formatted.replace("?", "").trim();

//			Split all the keywords
			String[] keys = formatted.split("\\s");

			boolean found = false;
			String roman = "";
			String outputResult = null;

//			it contains all the credit values
			Set<Integer> valueSet = new LinkedHashSet<Integer>();

			for (String key : keys)
			{
				found = false;

//				Get Roman Value for key
				String romanValue = nameRomanValueMap.get(key.toLowerCase());
				if (romanValue != null)
				{
					roman += romanValue;
					found = true;
				}
				else
				{
//					Get credit value for key and stored 
					Integer computedValue = creditMap.get(key.toLowerCase());
					if (computedValue != null)
					{
						valueSet.add(computedValue);
						found = true;
					}
				}

				if (!found)
				{
					outputResult = this.message.getMessage(Codes.NO_IDEA);
					break;
				}
			}

			if (found)
			{
				int res = 1;
				Iterator<Integer> itr = valueSet.iterator();
//				Multiple all the credit values.
				while (itr.hasNext())
				{
					res *= itr.next();
				}

				int finalres = (int) res;
//				If Roman number is available then get Arabic value of that Roman number and multiple it with previous credits
				if (roman.length() > 0)
				{
					finalres = (int) (Integer.parseInt(RomanNumbers.romanToArabicNumber(roman)) * res);
				}

				outputResult = formatted + " is " + finalres + " Credits";
			}
			this.outputList.add(outputResult);
		}
		catch (Exception e)
		{
			this.message.printMessage(Codes.INCORRECT_LINE_TYPE);
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<String> getOutputList()
	{
		return outputList;
	}

	/**
	 * no match with line 
	 */
	public void processNoMatch()
	{
		this.outputList.add(message.getMessage(Codes.NO_IDEA));
	}
}

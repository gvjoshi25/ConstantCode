package assignment.merchant.galaxy.guide.utils;

public class RomanNumbers
{

	public static final Messages	message					= new Messages();

//	Pattern to validate Roman Number
	public static String			romanNumberValidator	= "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

	/**
	 * Get Arabic Number of a Roman character
	 * 
	 * @param romanChar
	 * @return Arabic Number
	 */
	private static int getArabicValue(char romanChar)
	{
		int value = -1;

		switch (romanChar)
		{
			case 'I':
				value = Roman.I.getValue();
				break;
			case 'V':
				value = Roman.V.getValue();
				break;
			case 'X':
				value = Roman.X.getValue();
				break;
			case 'L':
				value = Roman.L.getValue();
				break;
			case 'C':
				value = Roman.C.getValue();
				break;
			case 'D':
				value = Roman.D.getValue();
				break;
			case 'M':
				value = Roman.M.getValue();
				break;
		}
		return value;
	}

	/**
	 * Get Arabic Number of a Roman Number
	 * 
	 * @param roman
	 * @return Arabic number or error message
	 */
	public static String romanToArabicNumber(String roman)
	{
		String result = "";
		if (roman.matches(romanNumberValidator))
		{
			result = String.valueOf(convertRomanToArabicNumber(roman));
		}
		else
		{
			result = RomanNumbers.message.getMessage(Codes.INVALID_ROMAN_STR);
		}
		return result;
	}

	/**
	 * Convert Roman number to Arabic number
	 * 
	 * @param Roman Number
	 * @return Arabic Number
	 */
	private static Integer convertRomanToArabicNumber(String roman)
	{
		int arabicNumber = 0;
		int lastNumber = 0;

		for (int i = roman.length() - 1; i >= 0; i--)
		{
			char romanChar = roman.charAt(i);

//			Get Arabic value of Roman character
			int decimal = getArabicValue(romanChar);

//			when smaller values precede large values, the smaller values are subtracted from the larger values and the result is added to the total.
			if (lastNumber > decimal)
				arabicNumber = arabicNumber - decimal;
			else
				arabicNumber = arabicNumber + decimal;

			lastNumber = decimal;
		}
		return arabicNumber;
	}

}
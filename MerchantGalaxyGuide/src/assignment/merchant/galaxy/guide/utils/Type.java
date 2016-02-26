package assignment.merchant.galaxy.guide.utils;

/**
 * Different types of possibilities for line
 * 
 * @author Gautam Joshi
 */
public enum Type
{

	/**
	 * Assignment type. E.g. : glob is V
	 */
	ASSIGNMENT_ROMAN,

	/**
	 * Credits type. E.g. : glob glob Silver is 34 Credits
	 */
	ASSIGNMENT_CREDITS,

	/**
	 * Question asking how much. E.g. : how much is pish tegj glob glob ?
	 */
	QUESTION_HOW_MUCH,

	/**
	 * Question asking how many. E.g. : how many Credits is glob prok Iron ?
	 */
	QUESTION_HOW_MANY,

	/**
	 * Invalid query
	 */
	NO_MATCH

}
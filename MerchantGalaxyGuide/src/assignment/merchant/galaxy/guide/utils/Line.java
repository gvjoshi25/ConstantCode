package assignment.merchant.galaxy.guide.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Line
{
	private Type	type;
	private String	pattern;
	private Matcher	matcher;

	public Line(final Type type, final String pattern)
	{
		this.type = type;
		this.pattern = pattern;
		this.setMatcher();
	}

	public Type getType()
	{
		return this.type;
	}

	public Matcher getMatcher()
	{
		return this.matcher;
	}

	private void setMatcher()
	{
		if (this.pattern != null)
		{
			Pattern linePattern = Pattern.compile(this.pattern);
			this.matcher = linePattern.matcher("");
		}
	}
}

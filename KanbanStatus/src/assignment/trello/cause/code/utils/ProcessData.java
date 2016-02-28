package assignment.trello.cause.code.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class ProcessData
{
	public static JSONObject getJsonObject(String jsonString)
	{
		JSONObject object = null;
		if (isValid(jsonString))
		{
			object = (JSONObject) JSONSerializer.toJSON(jsonString);
		}
		return object;
	}

	/** to check whether this object is valid or not. */
	public static boolean isValid(Object obj)
	{
		if (obj == null)
			return false;
		else
		{
			if (obj instanceof String)
			{
				String s = (String) obj;
				if (s.equals("") || s.equals("null"))
					return false;
				return true;
			}
			if (obj instanceof JSONObject)
			{
				JSONObject json = (JSONObject) obj;
				if (json.isNullObject() || json.isEmpty())
					return false;
			}
			return true;
		}
	}

	public static JSONArray getJsonArray(String jsonString)
	{
		JSONArray jsonArray = null;
		if (isValid(jsonString))
		{
			jsonArray = (JSONArray) JSONSerializer.toJSON(jsonString);
			if (!isValidJSonArray(jsonArray))
			{
				jsonArray = null;
			}
		}
		return jsonArray;
	}

	/**
	 * validate json array. If it is null or empty return false.
	 * 
	 * @param jsonArray
	 * @return true/false
	 */
	public static boolean isValidJSonArray(JSONArray jsonArray)
	{
		Boolean isValidJsonArray = true;
		if (jsonArray == null)
			isValidJsonArray = false;
		else
		{
			if (jsonArray.isEmpty())
			{
				isValidJsonArray = false;
			}
		}
		return isValidJsonArray;
	}
}

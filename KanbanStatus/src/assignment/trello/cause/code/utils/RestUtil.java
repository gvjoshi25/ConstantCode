package assignment.trello.cause.code.utils;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import assignment.trello.cause.code.rest.exception.RestException;

/**
 * @author Gautam Joshi
 * 
 */
public final class RestUtil
{
	private static final Set<Integer>	httpStatusSet;
	private static HttpClient			client;

	private static final Logger			logger	= Logger.getLogger(RestUtil.class);

	private RestUtil()
	{

	}

	static
	{
		httpStatusSet = new HashSet<Integer>();
		httpStatusSet.add(Codes.ACCEPTED.getValue());
		httpStatusSet.add(Codes.CREATED.getValue());
		httpStatusSet.add(Codes.OK.getValue());

		client = HttpClientBuilder.create().build();
	}

	public static JSONArray getHttpJsonArray(final URI uri) throws RestException
	{
		try
		{
			logger.debug(uri.toString());

			HttpGet request = new HttpGet(uri);
			HttpResponse response = client.execute(request);
			int httpResponseCode = response.getStatusLine().getStatusCode();
			if (httpStatusSet.contains(httpResponseCode))
			{
				String jsonString = EntityUtils.toString(response.getEntity());
				JSONArray json = ProcessData.getJsonArray(jsonString);
				return json;
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new RestException(Codes.REST_EXCEPTION);
		}
		throw new RestException(Codes.REST_EXCEPTION);
	}

	public static JSONObject getHttpJsonObject(final URI uri) throws RestException
	{
		try
		{
			logger.debug(uri.toString());

			HttpGet request = new HttpGet(uri);
			HttpResponse response = client.execute(request);
			int httpResponseCode = response.getStatusLine().getStatusCode();
			if (httpStatusSet.contains(httpResponseCode))
			{
				String jsonString = EntityUtils.toString(response.getEntity());
				JSONObject json = ProcessData.getJsonObject(jsonString);
				return json;
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new RestException(Codes.REST_EXCEPTION);
		}
		throw new RestException(Codes.REST_EXCEPTION);
	}
}
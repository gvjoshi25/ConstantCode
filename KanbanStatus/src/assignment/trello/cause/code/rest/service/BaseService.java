package assignment.trello.cause.code.rest.service;

import org.apache.http.client.utils.URIBuilder;

public class BaseService
{
	private URIBuilder		uriBuilder;
	private final String	KEY		= "053c3cf8f54667071b151d3be7871721";
	private final String	TOKEN	= "de0233846cf733fe5b8a5393aa5a956995097d17b895e0a0451ba19ac1f94a2e";

	/**
	 * it contains all the basics information for URI.
	 */
	public BaseService()
	{
		uriBuilder = new URIBuilder();
		uriBuilder.setScheme("https");
		uriBuilder.setHost("trello.com");
		uriBuilder.addParameter("key", KEY);
		uriBuilder.addParameter("token", TOKEN);
	}

	public URIBuilder getUriBuilder()
	{
		return uriBuilder;
	}

}
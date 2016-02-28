package assignment.trello.cause.code.rest.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.utils.URIBuilder;

import assignment.trello.cause.code.rest.exception.RestException;
import assignment.trello.cause.code.utils.Codes;
import assignment.trello.cause.code.utils.RestUtil;

public class TrelloService extends BaseService
{

	/**
	 * get all the boards.
	 * 
	 * @return JSONArray
	 * @throws RestException
	 */
	public JSONArray getAllBoards() throws RestException
	{
		try
		{
			URIBuilder uri = getUriBuilder();
			uri.setPath("/1/member/me/boards");

			return RestUtil.getHttpJsonArray(uri.build());
		}
		catch (Exception e)
		{
			throw new RestException(Codes.REST_EXCEPTION);
		}
	}

	/**
	 * Get all the list of specified board https://api.trello.com/1/boards/CMV0pT47/lists?cards=all&card_fields=name,shortLink,idList,idBoard
	 * 
	 * @param board Id or board short id (566aa55d83a50f627edda222 - kanban)
	 * @return
	 * @throws RestException
	 */
	public JSONArray getAllListByBoard(final String boardId) throws RestException
	{
		try
		{
			URIBuilder uri = getUriBuilder();
			uri.setPath("/1/boards/" + boardId + "/lists");
			uri.setParameter("cards", "all");
			uri.setParameter("card_fields", "name,shortLink,idList,idBoard");

			return RestUtil.getHttpJsonArray(uri.build());
		}
		catch (Exception e)
		{
			throw new RestException(Codes.REST_EXCEPTION);
		}
	}

	/**
	 * Retrieve card history for specified board
	 * 
	 * https://trello.com/1/Boards/CMV0pT47?card_fields=name,shortLink,idList,idBoard&actions=createCard,updateCard:idList,updateCard:Aclosed,updateCard:due
	 * 
	 * @param boardId
	 * @return JsonArray
	 * @throws RestException
	 */
	public JSONObject getCardHistory(final String boardId) throws RestException
	{
		try
		{
			URIBuilder uri = getUriBuilder();
			uri.setPath("/1/Boards/" + boardId);
			uri.addParameter("actions", "createCard,updateCard:idList,updateCard:Aclosed,updateCard:due");

			return RestUtil.getHttpJsonObject(uri.build());
		}
		catch (Exception e)
		{
			throw new RestException(Codes.REST_EXCEPTION);
		}
	}
}

package assignment.trello.cause.code;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import assignment.trello.cause.code.bean.Board;
import assignment.trello.cause.code.bean.Card;
import assignment.trello.cause.code.bean.CheckList;
import assignment.trello.cause.code.rest.exception.RestException;
import assignment.trello.cause.code.rest.service.TrelloService;
import assignment.trello.cause.code.utils.DateUtils;
import assignment.trello.cause.code.utils.ProcessData;

/**
 * @author Gautam_Joshi02
 *
 */
public class ProcessKanban
{
	private final String			BOARD_NAME			= "Kanban";

	private final String			TYPE_UPDATE_CARD	= "updateCard";
	private final String			TYPE_CREATE_CARD	= "createCard";

	private ArrayList<CheckList>	checklistArraylist;
	private ArrayList<Card>			cardArraylist;

	private TrelloService			service;

	public ProcessKanban(TrelloService service)
	{
		this.service = service;
		checklistArraylist = new ArrayList<CheckList>();
		cardArraylist = new ArrayList<Card>();
	}

	/**
	 * get all the boards and process for Kanban board.
	 */
	public void processAllBoards()
	{
		try
		{
			JSONArray jsonArray = service.getAllBoards();
			for (int i = 0; i < jsonArray.size(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String boardName = String.valueOf(jsonObject.get("name"));
				if (BOARD_NAME.equalsIgnoreCase(boardName))
				{
					Board board = new Board();
					board.setBoardId(String.valueOf(jsonObject.get("id")));
					board.setBoardShortId(String.valueOf(jsonObject.get("shortLink")));
					board.setBoardName(BOARD_NAME);
					processAllLists(board);
					processCard(board);
					printCards();
					break;
				}
			}
		}
		catch (RestException exception)
		{
			exception.printMessage(exception.getErrorCode());
		}
	}

	/**
	 * Get all the cards for specified board (here it is kanban).
	 * 
	 * @param board
	 */
	public void processAllLists(Board board)
	{
		try
		{
			JSONArray listJsonArray = service.getAllListByBoard(board.getBoardShortId());

			for (int i = 0; i < listJsonArray.size(); i++)
			{
				JSONObject jsonObject = listJsonArray.getJSONObject(i);

				CheckList checklist = new CheckList();
				checklist.setBoard(board);
				checklist.setListId(String.valueOf(jsonObject.get("id")));
				checklist.setListName(String.valueOf(jsonObject.get("name")));
				checklist.setCardSet(new LinkedHashSet<Card>());
				JSONArray cardArray = jsonObject.getJSONArray("cards");
				if (cardArray != null && !cardArray.isEmpty())
				{
					for (int j = 0; j < cardArray.size(); j++)
					{
						try
						{
							JSONObject cardJson = cardArray.getJSONObject(j);
							Card card = new Card();
							card.setBoard(board);
							card.setCurrentList(checklist);
							card.setCardId(String.valueOf(cardJson.get("id")));
							card.setCardName(String.valueOf(cardJson.get("name")));
							checklist.getCardSet().add(card);
							cardArraylist.add(card);
						}
						catch (Exception exp)
						{
							exp.printStackTrace();
						}
					}
				}
				checklistArraylist.add(checklist);
			}
		}
		catch (RestException exception)
		{
			exception.printMessage(exception.getErrorCode());
		}
	}

	/**
	 * Process card to update created information and latest activity
	 * 
	 * @param board
	 */
	public void processCard(Board board)
	{
		try
		{
			JSONObject historyJsonObject = service.getCardHistory(board.getBoardShortId());

			for (Card card : cardArraylist)
			{
				JSONArray historyArray = (JSONArray) historyJsonObject.get("actions");
				for (int i = 0; i < historyArray.size(); i++)
				{
					JSONObject jsonObject = historyArray.getJSONObject(i);
					String actionType = String.valueOf(jsonObject.get("type"));
					JSONObject dataJsonObject = ProcessData.getJsonObject(String.valueOf(jsonObject.get("data")));
					JSONObject cardJsonObject = ProcessData.getJsonObject(String.valueOf(dataJsonObject.get("card")));

					String cardId = cardJsonObject.getString("id");

					if (card.getCardId().equalsIgnoreCase(cardId))
					{
						if (TYPE_CREATE_CARD.equalsIgnoreCase(actionType))
						{
							String strDate = jsonObject.getString("date");
							if (strDate.endsWith("Z"))
								strDate = strDate.substring(0, strDate.length() - 1);
							Date createdDate = DateUtils.getDate(strDate);
							card.setCreatedDate(createdDate);

							String creator = ProcessData.getJsonObject(jsonObject.getString("memberCreator")).getString("fullName");
							card.setCreatedBy(creator);

						}
						else if (TYPE_UPDATE_CARD.equalsIgnoreCase(actionType))
						{
							String strDate = jsonObject.getString("date");
							if (strDate.endsWith("Z"))
								strDate = strDate.substring(0, strDate.length() - 1);
							Date updateDate = DateUtils.getDate(strDate);
							if (card.getDoneDate() == null || card.getDoneDate().getTime() <= updateDate.getTime())
							{
								card.setDoneDate(updateDate);
								String creator = ProcessData.getJsonObject(jsonObject.getString("memberCreator")).getString("fullName");
								card.setDoneBy(creator);
							}
						}
					}
				}
			}
		}
		catch (RestException exception)
		{
			exception.printMessage(exception.getErrorCode());
		}
	}

	/**
	 * Print all the cards with its current activity.
	 */
	public void printCards()
	{
		for (Card card : cardArraylist)
		{
			System.out.println();
			System.out.println();
			System.out.println("Card Name :- " + card.getCardName() + ":- ");
			System.out.println("Created Date - " + card.getCreatedDate());
			System.out.println("Created By - " + card.getCreatedBy());

			if (card.getCurrentList() != null && card.getCurrentList().getListName() != null)
			{
				System.out.println("Current Stage - " + card.getCurrentList().getListName());
			}

			if (card.getDoneDate() != null)
			{
				System.out.println("Done Date - " + card.getDoneDate());
				System.out.println("Done By - " + card.getCreatedBy());

				System.out.println("Total Time differnce - " + DateUtils.getDifference(card.getCreatedDate(), card.getDoneDate()));
			}
		}
	}

}

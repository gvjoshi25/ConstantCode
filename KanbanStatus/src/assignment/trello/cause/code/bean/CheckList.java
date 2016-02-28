package assignment.trello.cause.code.bean;

import java.util.Set;

public class CheckList
{
	private String		listName;
	private String		listId;
	private String		listShortId;
	private Board		board;
	private Set<Card>	cardSet;

	public String getListName()
	{
		return listName;
	}

	public void setListName(String listName)
	{
		this.listName = listName;
	}

	public String getListId()
	{
		return listId;
	}

	public void setListId(String listId)
	{
		this.listId = listId;
	}

	public String getListShortId()
	{
		return listShortId;
	}

	public void setListShortId(String listShortId)
	{
		this.listShortId = listShortId;
	}

	public Board getBoard()
	{
		return board;
	}

	public void setBoard(Board board)
	{
		this.board = board;
	}

	public Set<Card> getCardSet()
	{
		return cardSet;
	}

	public void setCardSet(Set<Card> cardSet)
	{
		this.cardSet = cardSet;
	}
}

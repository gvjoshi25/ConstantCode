package assignment.trello.cause.code.bean;

import java.util.Date;

public class Card
{
	private String		cardName;
	private String		cardId;
	private String		cardShortId;
	private Board		board;
	private String		createdBy;
	private Date		createdDate;
	private String		doneBy;
	private Date		doneDate;
	private CheckList	currentList;

	public String getCardName()
	{
		return cardName;
	}

	public void setCardName(String cardName)
	{
		this.cardName = cardName;
	}

	public String getCardId()
	{
		return cardId;
	}

	public void setCardId(String cardId)
	{
		this.cardId = cardId;
	}

	public String getCardShortId()
	{
		return cardShortId;
	}

	public void setCardShortId(String cardShortId)
	{
		this.cardShortId = cardShortId;
	}

	public Board getBoard()
	{
		return board;
	}

	public void setBoard(Board board)
	{
		this.board = board;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public CheckList getCurrentList()
	{
		return currentList;
	}

	public void setCurrentList(CheckList currentList)
	{
		this.currentList = currentList;
	}

	public String getDoneBy()
	{
		return doneBy;
	}

	public void setDoneBy(String doneBy)
	{
		this.doneBy = doneBy;
	}

	public Date getDoneDate()
	{
		return doneDate;
	}

	public void setDoneDate(Date doneDate)
	{
		this.doneDate = doneDate;
	}
}

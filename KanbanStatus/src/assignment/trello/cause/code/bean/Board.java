package assignment.trello.cause.code.bean;

import java.util.Set;

public class Board
{
	private String			boardName;
	private String			boardId;
	private String			boardShortId;
	private Set<CheckList>	boardLists;

	public String getBoardName()
	{
		return boardName;
	}

	public void setBoardName(String boardName)
	{
		this.boardName = boardName;
	}

	public String getBoardId()
	{
		return boardId;
	}

	public void setBoardId(String boardId)
	{
		this.boardId = boardId;
	}

	public String getBoardShortId()
	{
		return boardShortId;
	}

	public void setBoardShortId(String boardShortId)
	{
		this.boardShortId = boardShortId;
	}

	public Set<CheckList> getBoardLists()
	{
		return boardLists;
	}

	public void setBoardLists(Set<CheckList> boardLists)
	{
		this.boardLists = boardLists;
	}

}

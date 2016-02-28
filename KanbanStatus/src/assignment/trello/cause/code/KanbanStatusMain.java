package assignment.trello.cause.code;

import assignment.trello.cause.code.rest.service.TrelloService;

public class KanbanStatusMain
{
	/**
	 * This program identify the total time between creation of Card and last activity date.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		ProcessKanban process = new ProcessKanban(new TrelloService());
		process.processAllBoards();
	}
}

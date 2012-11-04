package controllers;

import play.mvc.Controller;
import ch.sauerrahm.hnefatafl.Board;
import ch.sauerrahm.hnefatafl.Field;
import ch.sauerrahm.hnefatafl.Game;
import ch.sauerrahm.hnefatafl.Move;
import ch.sauerrahm.hnefatafl.Side;
import ch.sauerrahm.hnefatafl.exceptions.IllegalMoveException;
import ch.sauerrahm.hnefatafl.exceptions.VictoryException;

public class Application extends Controller {

	static Game game = new Game();
	
    public static void index() {
    	String side = Side.BLACK.toString();
        render(side);
    }

    public static void getBoard(long gameId){
    	Board board = game.getBoard();
    	renderJSON(board);
    }
    
    public static void doMove(long gameId, int fromX, int fromY, int toX, int toY){
    	Field[][] fields = game.getBoard().getBoard();
    	String message = "";
    	try {
			game.doMove(new Move(fields[fromX][fromY], fields[toX][toY]), Side.BLACK);
		} catch (VictoryException e) {
			message = e.getSide() + " won!";
		} catch (IllegalMoveException e) {
			message = "This move is not possible";
		}
    	Board board = game.getBoard();
    	board.setMessage(message);
    	
    	renderJSON(board);
    }
}
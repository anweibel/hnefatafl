package controllers;

import play.mvc.Controller;
import ch.sauerrahm.hnefatafl.Board;
import ch.sauerrahm.hnefatafl.Game;
import ch.sauerrahm.hnefatafl.Side;

public class Application extends Controller {

    public static void index() {
    	String side = Side.BLACK.toString();
        render(side);
    }

    public static void getBoard(long gameId){
    	Board board = new Game().getBoard();
    	renderJSON(board);
    }
}
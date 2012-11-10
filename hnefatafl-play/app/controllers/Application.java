package controllers;

import java.util.HashMap;
import java.util.Map;

import play.mvc.Controller;
import ch.sauerrahm.hnefatafl.Field;
import ch.sauerrahm.hnefatafl.Game;
import ch.sauerrahm.hnefatafl.Move;
import ch.sauerrahm.hnefatafl.Player;
import ch.sauerrahm.hnefatafl.Side;
import ch.sauerrahm.hnefatafl.ai.AiPlayer;
import ch.sauerrahm.hnefatafl.exceptions.IllegalMoveException;
import ch.sauerrahm.hnefatafl.exceptions.VictoryException;
import ch.sauerrahm.hnefatafl.online.OnlinePlayer;

public class Application extends Controller {

	private static Map<Long, Game> games = new HashMap<Long, Game>();
	
    public static void index() {
        render();
    }
    
    public static void board(Long gameId) {
        render(gameId);
    }

    public static void boardJson(Long gameId){
    	renderJSON(games.get(gameId));
    }
    
    public static void doMove(Long gameId, int fromX, int fromY, int toX, int toY){
    	Game game = games.get(gameId);
    	Field[][] fields = game.getBoard().getBoard();
    	String message = "";
    	try {
    		Side side = game.getSide("TODO");
			game.doMove(new Move(fields[fromX][fromY], fields[toX][toY]), side);
		} catch (VictoryException e) {
			message = e.getSide() + " won!";
		} catch (IllegalMoveException e) {
			message = "This move is not possible";
		}
    	game.getBoard().setMessage(message);
    	
    	renderJSON(game);
    }
    
    public static void createAiGame(String side){
    	
    	Player user = new OnlinePlayer(Side.fromString(side), "TODO");
    	Player computer = new AiPlayer(Side.fromString(side).otherSide());
    	Game game = new Game(user, computer);
    	Long gameId = lowestFreeGameId();
    	games.put(gameId, game);
    	redirect("Application.board", gameId);
    }
    
    private static Long lowestFreeGameId(){
    	Long counter = Long.valueOf(1l);
    	while(games.keySet().contains(counter)){
    		counter++;
    	}
    	return counter;
    }
}
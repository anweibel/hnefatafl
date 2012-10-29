package ch.sauerrahm.hnefatafl;

import java.io.IOException;

import ch.sauerrahm.hnefatafl.ai.AiPlayer;
import ch.sauerrahm.hnefatafl.boards.NineBoard;
import ch.sauerrahm.hnefatafl.exceptions.VictoryException;
import ch.sauerrahm.hnefatafl.txtgui.CommandLinePlayer;

/**
 * Hello world!
 *
 */
public class Game 
{
	
	private Board board = new NineBoard();
	private Player whitePlayer = new AiPlayer(Side.WHITE);
	private Player blackPlayer = new CommandLinePlayer(Side.BLACK);
	
    public static void main( String[] args ) throws IOException, NumberFormatException, VictoryException
    {
    	Game game = new Game();
    	
    	game.blackPlayer.handOver(game);
    }
    
    public void doMove(Move move, Side side) throws VictoryException{
    	if(!Rules.isMoveLegal(board, move, side)){
    		Player currentPlayer = getCurrentPlayer(side);
    		currentPlayer.signalIllegalMove();
    		currentPlayer.handOver(this);
    	}
    	
    	board = Rules.doMove(move, board);
    	getNextPlayer(side).handOver(this);    	
    }
    
    private Player getCurrentPlayer(Side side) {
		if(side == Side.WHITE)
			return whitePlayer;
		else 
			return blackPlayer;
	}
    
    private Player getNextPlayer(Side side) {
		if(side == Side.BLACK)
			return whitePlayer;
		else 
			return blackPlayer;
	}

	public Board getBoard(){
    	return board;
    }
}

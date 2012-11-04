package ch.sauerrahm.hnefatafl;

import java.io.IOException;

import ch.sauerrahm.hnefatafl.ai.AiPlayer;
import ch.sauerrahm.hnefatafl.boards.NineBoard;
import ch.sauerrahm.hnefatafl.exceptions.IllegalMoveException;
import ch.sauerrahm.hnefatafl.exceptions.VictoryException;
import ch.sauerrahm.hnefatafl.online.OnlinePlayer;

/**
 * Hello world!
 *
 */
public class Game 
{
	
	private Board board = new NineBoard();
	private Player whitePlayer = new AiPlayer(Side.WHITE);
	private Player blackPlayer = new OnlinePlayer(Side.BLACK);
	
    public static void main( String[] args ) throws IOException, NumberFormatException, VictoryException
    {
    	Game game = new Game();
    	
    	game.blackPlayer.handOver(game);
    }
    
    public void doMove(Move move, Side side) throws VictoryException, IllegalMoveException{
    	if(!Rules.isMoveLegal(board, move, side)){
    		throw new IllegalMoveException();
    	}
    	
    	board = Rules.doMove(move, board);
    	
    	if(board.getWinner() != null)
    		throw new VictoryException(board.getWinner());
    	
    	getNextPlayer(side).handOver(this);    	
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

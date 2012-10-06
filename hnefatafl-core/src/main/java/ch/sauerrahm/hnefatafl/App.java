package ch.sauerrahm.hnefatafl;

import java.io.IOException;

import ch.sauerrahm.hnafatafl.ai.AiPlayer;
import ch.sauerrahm.hnefatafl.boards.NineBoard;
import ch.sauerrahm.hnefatafl.boards.TestBoard;
import ch.sauerrahm.hnefatafl.exceptions.VictoryException;
import ch.sauerrahm.hnefatafl.txtgui.CommandLineDrawer;
import ch.sauerrahm.hnefatafl.txtgui.CommandLinePlayer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, NumberFormatException, VictoryException
    {
    	Board board = new TestBoard();
    	Player whitePlayer = new AiPlayer(Side.WHITE);
    	Player blackPlayer = new CommandLinePlayer(Side.BLACK);
    	
        CommandLineDrawer.drawBoard(board);
        
        while(1==1){
        	
        	Move blackMove = blackPlayer.getNextMove(board);
        	while(blackMove != null && !Rules.isMoveLegal(board, blackMove, Side.BLACK)){
        		blackPlayer.signalIllegalMove();
        		blackMove = blackPlayer.getNextMove(board);
        	}
        	
        	board = Rules.doMove(blackMove,	board);
        	
        	CommandLineDrawer.drawBoard(board);
        	
        	Move whiteMove = whitePlayer.getNextMove(board);
        	while(whiteMove != null && !Rules.isMoveLegal(board, whiteMove, Side.WHITE)){
        		whitePlayer.signalIllegalMove();
        		whiteMove = whitePlayer.getNextMove(board);
        	}
        	System.out.println("Moved " + whiteMove);
        	board = Rules.doMove(whiteMove,	board);
        	
        	CommandLineDrawer.drawBoard(board);
        }
    }
}

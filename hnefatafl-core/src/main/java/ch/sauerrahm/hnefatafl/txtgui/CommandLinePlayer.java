package ch.sauerrahm.hnefatafl.txtgui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ch.sauerrahm.hnefatafl.Board;
import ch.sauerrahm.hnefatafl.Field;
import ch.sauerrahm.hnefatafl.Move;
import ch.sauerrahm.hnefatafl.Player;
import ch.sauerrahm.hnefatafl.Rules;
import ch.sauerrahm.hnefatafl.Side;

public class CommandLinePlayer implements Player {

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private Side side;
	
	public CommandLinePlayer(Side side){
		this.side = side;
	} 
	
	@Override
	public Move getNextMove(Board board) {
		try {
			System.out.print(side + " from ");
			String fromString;
			fromString = reader.readLine();
			String[] from = fromString.split("\\.");
			System.out.print(side + " to ");
			String toString = reader.readLine();
			String[] to = toString.split("\\.");

			Field[][] fields = board.getBoard();
			Field fromField = fields[Integer.parseInt(from[0])][Integer.parseInt(from[1])];
			Field toField = fields[Integer.parseInt(to[0])][Integer.parseInt(to[1])];
			
			return(new Move(fromField, toField));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ArrayIndexOutOfBoundsException e){
			return null;
		}
	}

	@Override
	public void signalIllegalMove() {
		System.out.println("This move is not legal. Try again!");
		
	}

}

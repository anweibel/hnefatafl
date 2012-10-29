package ch.sauerrahm.hnefatafl.txtgui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ch.sauerrahm.hnefatafl.Field;
import ch.sauerrahm.hnefatafl.Game;
import ch.sauerrahm.hnefatafl.Move;
import ch.sauerrahm.hnefatafl.Player;
import ch.sauerrahm.hnefatafl.Side;
import ch.sauerrahm.hnefatafl.exceptions.VictoryException;

public class CommandLinePlayer implements Player {

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private Side side;

	public CommandLinePlayer(Side side) {
		this.side = side;
	}

	@Override
	public void handOver(Game game) {

		CommandLineDrawer.drawBoard(game.getBoard());
		
		boolean validMove = false;
		while (!validMove) {
			try {
				System.out.print(side + " from ");
				String fromString;
				fromString = reader.readLine();
				String[] from = fromString.split("\\.");
				System.out.print(side + " to ");
				String toString = reader.readLine();
				String[] to = toString.split("\\.");

				Field[][] fields = game.getBoard().getBoard();
				Field fromField = fields[Integer.parseInt(from[0])][Integer
						.parseInt(from[1])];
				Field toField = fields[Integer.parseInt(to[0])][Integer
						.parseInt(to[1])];

				validMove = true;
				game.doMove(new Move(fromField, toField), side);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Illegal input!");
				validMove = false;
			} catch (NumberFormatException e) {
				System.out.println("Illegal input!");
				validMove = false;
			} catch (VictoryException e) {
				System.out.println("Congratulations, you won!");
				validMove = true;
			}
		}
	}

	@Override
	public void signalIllegalMove() {
		System.out.println("This move is not legal. Try again!");

	}

	@Override
	public boolean isSynchronous() {
		return true;
	}

}

package ch.sauerrahm.hnefatafl.txtgui;

import ch.sauerrahm.hnefatafl.Board;
import ch.sauerrahm.hnefatafl.Field;

public class CommandLineDrawer {
	public static void drawBoard(Board board){
		
		Field[][] fields = board.getBoard();
		
		String bar = "   +";
		
		for(int i = 0; i < board.getSize() - 1; i++)
			bar += "--";
		
		bar += "-+" + System.lineSeparator();
		
		String playingField = "" + bar;
		
		for(int y = 0; y < board.getSize(); y++){
			String line = "";
			
			if(y < 10){
				line += " ";
			}
			
			line += y;
			line += " |";
			
			for(int x = 0; x < board.getSize(); x++){
				line += drawField(fields[x][y]) + "|";
			}
			playingField += line + System.lineSeparator();
		}
		
		playingField += bar;
		playingField += "    0 1 2 3 4 5 6 7 8" + System.lineSeparator();
		
		System.out.println(playingField);
	}

	private static String drawField(Field field) {
		
		if(field.isOccupiedByKing()){
			return "@";
		}
		
		if(field.isOccupiedByBlack()){
			return "x";
		}
		
		if(field.isOccupiedByWhite()){
			return "o";
		}
		
		if(field.isCorner() || field.isThrone()){
			return "%";
		}

		return " ";
	}
}

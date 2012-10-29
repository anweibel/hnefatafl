package ch.sauerrahm.hnefatafl.boards;

import ch.sauerrahm.hnefatafl.Board;
import ch.sauerrahm.hnefatafl.Field;

public class CopyBoard extends Board{
	public CopyBoard(Board otherBoard) {
		
		int size = otherBoard.getSize();
		this.size = size;
		this.board = new Field[size][size];
		Field[][] originalBoard = otherBoard.getBoard();
		
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				this.board[x][y] = new Field(originalBoard[x][y]);
			}
		}
		
		this.occupiedByWhite.addAll(otherBoard.getOccupiedByWhite());
		this.occupiedByBlack.addAll(otherBoard.getOccupiedByBlack());
		
		this.kingField = otherBoard.getKingField(); 
	}
}

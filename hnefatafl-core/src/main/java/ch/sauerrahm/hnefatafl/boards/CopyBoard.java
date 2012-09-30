package ch.sauerrahm.hnefatafl.boards;

import ch.sauerrahm.hnefatafl.Board;
import ch.sauerrahm.hnefatafl.Field;
import ch.sauerrahm.hnefatafl.Piece;

public class CopyBoard extends Board{
	public CopyBoard(Board otherBoard) {
		
		int size = otherBoard.getSize();
		this.size = size;
		this.board = new Field[size][size];
		Field[][] originalBoard = otherBoard.getBoard();
		
		this.occupiedByWhite.addAll(otherBoard.getOccupiedByWhite());
		this.occupiedByBlack.addAll(otherBoard.getOccupiedByBlack());
		
		this.kingField = otherBoard.getKingField(); 
				
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				this.board[x][y] = originalBoard[x][y];
			}
		}
	}
}

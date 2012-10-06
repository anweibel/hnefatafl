package ch.sauerrahm.hnefatafl.boards;

import ch.sauerrahm.hnefatafl.Board;
import ch.sauerrahm.hnefatafl.Field;
import ch.sauerrahm.hnefatafl.Piece;
import ch.sauerrahm.hnefatafl.Side;

public class TestBoard extends Board{
	
	public TestBoard(){
		size = 4;
		board = new Field[size][size];
		
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				board[x][y] = new Field(x, y, false, false);
			}
		}
		
		this.setPiece(board[3][0], new Piece(Side.BLACK, false));
		this.setPiece(board[3][1], new Piece(Side.BLACK, false));
		this.setPiece(board[3][3], new Piece(Side.BLACK, false));
		this.setPiece(board[0][3], new Piece(Side.BLACK, false));
		
		this.setPiece(board[0][1], new Piece(Side.WHITE, false));
		this.setPiece(board[1][0], new Piece(Side.WHITE, false));
		this.setPiece(board[2][0], new Piece(Side.WHITE, false));
		
		this.setPiece(board[3][2], new Piece(Side.WHITE, true));
		
	}
}

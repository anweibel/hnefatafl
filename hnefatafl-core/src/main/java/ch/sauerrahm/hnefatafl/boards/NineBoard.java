package ch.sauerrahm.hnefatafl.boards;

import ch.sauerrahm.hnefatafl.Board;
import ch.sauerrahm.hnefatafl.Field;
import ch.sauerrahm.hnefatafl.Piece;
import ch.sauerrahm.hnefatafl.Side;

public class NineBoard extends Board {
	public NineBoard(){
		
		size = 9;
		board = new Field[size][size];
		
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				board[x][y] = new Field(x, y, false, false);
			}
		}
		
		board[4][4] = new Field(4, 4, true, false);
		board[4][4].setPiece(new Piece(Side.WHITE, true));
		
		board[0][0] = new Field(0, 0, false, true);
		board[0][8] = new Field(0, 8, false, true);
		board[8][0] = new Field(8, 0, false, true);
		board[8][8] = new Field(8, 8, false, true);
		
		board[0][3].setPiece(new Piece(Side.BLACK, false));
		board[0][4].setPiece(new Piece(Side.BLACK, false));
		board[0][5].setPiece(new Piece(Side.BLACK, false));
		board[1][4].setPiece(new Piece(Side.BLACK, false));
		
		board[3][0].setPiece(new Piece(Side.BLACK, false));
		board[4][0].setPiece(new Piece(Side.BLACK, false));
		board[5][0].setPiece(new Piece(Side.BLACK, false));
		board[4][1].setPiece(new Piece(Side.BLACK, false));
		
		board[4][7].setPiece(new Piece(Side.BLACK, false));
		board[3][8].setPiece(new Piece(Side.BLACK, false));
		board[4][8].setPiece(new Piece(Side.BLACK, false));
		board[5][8].setPiece(new Piece(Side.BLACK, false));
		
		board[7][4].setPiece(new Piece(Side.BLACK, false));
		board[8][3].setPiece(new Piece(Side.BLACK, false));
		board[8][4].setPiece(new Piece(Side.BLACK, false));
		board[8][5].setPiece(new Piece(Side.BLACK, false));
		
		board[2][4].setPiece(new Piece(Side.WHITE, false));
		board[3][4].setPiece(new Piece(Side.WHITE, false));
		board[5][4].setPiece(new Piece(Side.WHITE, false));
		board[6][4].setPiece(new Piece(Side.WHITE, false));
		
		board[4][2].setPiece(new Piece(Side.WHITE, false));
		board[4][3].setPiece(new Piece(Side.WHITE, false));
		board[4][5].setPiece(new Piece(Side.WHITE, false));
		board[4][6].setPiece(new Piece(Side.WHITE, false));
		
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				if(board[x][y].isOccupiedByBlack())
					occupiedByBlack.add(board[x][y]);
				if(board[x][y].isOccupiedByWhite())
					occupiedByWhite.add(board[x][y]);
			}
		}
		
		kingField = board[4][4];
	}
}

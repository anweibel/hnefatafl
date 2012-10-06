package ch.sauerrahm.hnefatafl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.sauerrahm.hnefatafl.exceptions.IncoherentStateException;

public abstract class Board {

	protected int size;
	protected Field[][] board;
	
	protected Set<Field> occupiedByWhite = new HashSet<>();
	protected Set<Field> occupiedByBlack = new HashSet<>();
	
	protected Field kingField;
	
	public List<Move> getPossibleMoves(Side side){
		List<Move> possibleMoves = new ArrayList<>();
		Set<Field> occupiedFields = null;
		
		switch(side){
		case WHITE:
			occupiedFields = occupiedByWhite;
			break;
		case BLACK:
			occupiedFields = occupiedByBlack;
			break;
		}
		
		for(Field field : occupiedFields){
			possibleMoves.addAll(getPossibleMoves(field));
		}
			
		return possibleMoves;
	}
	
	public List<Move> getPossibleMoves(Field fromField){
		
		List<Move> possibleMoves = new ArrayList<Move>();
		
		if(!fromField.isOccupied()){
			return possibleMoves;
		}
		
		boolean isKing = fromField.isOccupiedByKing();
		
		exploreDirection(fromField, isKing, possibleMoves, -1, 0);
		exploreDirection(fromField, isKing, possibleMoves, +1, 0);
		exploreDirection(fromField, isKing, possibleMoves, 0, -1);
		exploreDirection(fromField, isKing, possibleMoves, 0, +1);

		return possibleMoves;
	}

	private void exploreDirection(Field fromField, boolean isKing,
			List<Move> possibleMoves, int xOffset, int yOffset) {
		int x = fromField.getXPosition() + xOffset;
		int y = fromField.getYPosition() + yOffset;
		
		while(isFreeToMove(x, y, isKing)){
			Field targetField = board[x][y];
			
			if(isKing || !targetField.isThrone())
				possibleMoves.add(new Move(fromField, targetField));
			
			x += xOffset;
			y += yOffset;
		}
	}

	private boolean isFreeToMove(int x, int y, boolean isKing) {

		if(x < 0 || y < 0)
			return false;
		
		if(x >= size || y >= size)
			return false;
		
		if(board[x][y].isOccupied())
			return false;
		
		if(board[x][y].isCorner() && !isKing)
			return false;
		
		return true;
	}
	
	public Field[][] getBoard(){
		return board;
	}
	
	public int getSize(){
		return size;
	}
	
	public Set<Field> getOccupiedByWhite() {
		return occupiedByWhite;
	}

	public Set<Field> getOccupiedByBlack() {
		return occupiedByBlack;
	}

	public Field getKingField(){
		return kingField;
	}
	
	public Piece getPieceAt(int x, int y){
		return board[x][y].getPiece();
	}
	
	public void removePiece(Field field){
		if(!field.isOccupied())
			throw new IncoherentStateException();
		
		if(field.isOccupiedByWhite())
			occupiedByWhite.remove(field);
		else
			occupiedByBlack.remove(field);
		
		if(field.isOccupiedByKing()){
			kingField = null;
		}
		
		board[field.getXPosition()][field.getYPosition()].removePiece();
	}
	
	public Set<Field> getOccupiedFields(Side side){
		if(side.equals(Side.BLACK))
			return occupiedByBlack;
		else
			return occupiedByWhite;
	}

	public void setPiece(Field to, Piece movingPiece) {
		if(movingPiece.isKing())
			kingField = to;
		
		if(movingPiece.getSide() == Side.BLACK)
			occupiedByBlack.add(to);
		else
			occupiedByWhite.add(to);
		
		board[to.getXPosition()][to.getYPosition()].setPiece(movingPiece);
	}
	
	public String toString(){
		String bar = "   +";
		
		for(int i = 0; i < size - 1; i++)
			bar += "--";
		
		bar += "-+" + System.lineSeparator();
		
		String playingField = "" + bar;
		
		for(int y = 0; y < size; y++){
			String line = "";
			
			if(y < 10){
				line += " ";
			}
			
			line += y;
			line += " |";
			
			for(int x = 0; x < size; x++){
				line += board[x][y].drawField() + "|";
			}
			playingField += line + System.lineSeparator();
		}
		
		playingField += bar;
		playingField += "    0 1 2 3 4 5 6 7 8" + System.lineSeparator();
		
		return playingField;
	}
}

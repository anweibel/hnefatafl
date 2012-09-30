package ch.sauerrahm.hnefatafl;

public class Piece {

	private Side side;
	private boolean isKing;
	
	public Piece(Side side, boolean isKing){
		this.side = side;
		this.isKing = isKing;
	}
	
	public boolean isKing(){
		return isKing;
	}
	
	public Side getSide(){
		return side;
	}
}

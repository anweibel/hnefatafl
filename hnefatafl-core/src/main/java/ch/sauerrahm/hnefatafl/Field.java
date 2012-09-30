package ch.sauerrahm.hnefatafl;

public class Field {

	private Piece piece;
	
	private int XPosition;
	private int YPosition;
	
	private boolean isThrone;
	private boolean isCorner;
	
	
	public Field(int XPosition, int YPosition, boolean isThrone, boolean isCorner){
		this.XPosition = XPosition;
		this.YPosition = YPosition;
		this.isThrone = isThrone;
		this.isCorner = isCorner;
	}
	
	public boolean isOccupied(){
		return piece != null;
	}
	
	public int getXPosition(){
		return XPosition;
	}
	
	public int getYPosition(){
		return YPosition;
	}

	public boolean isCorner() {
		return isCorner;
	}

	public boolean isThrone() {
		return isThrone;
	}
	
	public boolean isOccupiedByKing(){
		if (piece == null)
			return false;
		else 
			return piece.isKing(); 
	}
	
	public boolean isOccupiedByWhite(){
		if (piece == null)
			return false;
		else 
			return piece.getSide() == Side.WHITE; 
	}
	
	public boolean isOccupiedByBlack(){
		if (piece == null)
			return false;
		else 
			return piece.getSide() == Side.BLACK; 
	}
	
	public boolean isOccupiedBy(Side side){
		if (piece == null)
			return false;
		else 
			return piece.getSide() == side; 
	}
	
	public boolean isOccupiedByOpponent(Side side){
		if (piece == null)
			return false;
		else 
			return piece.getSide() != side; 
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public Piece getPiece(){
		return piece;
	}
	
	public void removePiece(){
		this.piece = null;
	}
	
	@Override
	public boolean equals(Object other){
		if(other == null || !(other instanceof Field))
			return false;
		
		Field otherField = (Field)other;
		
		return this.XPosition == otherField.XPosition && this.YPosition == otherField.YPosition;
	}
	
	public int hashCode(){
		return this.XPosition * 37 + this.YPosition;
	}
}

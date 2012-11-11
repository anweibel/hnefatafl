package ch.sauerrahm.hnefatafl;

import java.util.HashSet;
import java.util.Set;

import ch.sauerrahm.hnefatafl.boards.CopyBoard;

public class Rules {
	public static Result doMove(Move move, Board currentBoard){
		
		CopyBoard copiedBoard = new CopyBoard(currentBoard);
		
		Piece movingPiece = currentBoard.getPieceAt(move.getFrom().getXPosition(), move.getFrom().getYPosition());
				
		copiedBoard.removePiece(move.getFrom());
		copiedBoard.setPiece(move.getTo(), movingPiece);
		
		Set<Field> deletedPieces = checkForCapturedPieces(movingPiece, move.getTo().getXPosition(), move.getTo().getYPosition(), copiedBoard);		
		Side winner = checkForVictory(copiedBoard);
		
		return new Result(copiedBoard, deletedPieces, winner);
	}

	private static Set<Field> checkForCapturedPieces(Piece movingPiece, int toX, int toY, CopyBoard copiedBoard){
		
		Field[][] fields = copiedBoard.getBoard();
		Side side = movingPiece.getSide();
		Set<Field> deletedPieces = new HashSet<Field>();
		
		if(toX >=2 && checkDirectionForCapture(fields[toX-1][toY], fields[toX-2][toY], side)){
			copiedBoard.removePiece(fields[toX-1][toY]);
			deletedPieces.add(fields[toX-1][toY]);
		}
		if(toX <= copiedBoard.size - 3 && checkDirectionForCapture(fields[toX+1][toY], fields[toX+2][toY], side)){
			copiedBoard.removePiece(fields[toX+1][toY]);
			deletedPieces.add(fields[toX+1][toY]);
		}
		if(toY >=2 && checkDirectionForCapture(fields[toX][toY-1], fields[toX][toY-2], side)){
			copiedBoard.removePiece(fields[toX][toY-1]);
			deletedPieces.add(fields[toX][toY-1]);
		}
		if(toY <= copiedBoard.size - 3 && checkDirectionForCapture(fields[toX][toY+1], fields[toX][toY+2], side)){
			copiedBoard.removePiece(fields[toX][toY+1]);
			deletedPieces.add(fields[toX][toY+1]);
		}
		
		return deletedPieces;
	}
	
	private static boolean checkDirectionForCapture(Field directNeighour, Field nextNeighbour, Side side){
		if(!directNeighour.isOccupiedByOpponent(side)){
			return false;
		}
		if(!(nextNeighbour.isOccupiedBy(side) || nextNeighbour.isCorner())){
			return false;
		}
		if(directNeighour.isOccupiedByKing()){
			return false;
		}
		if(nextNeighbour.isThrone()){
			if (side == Side.BLACK)
				return true;
			else 
				if(!nextNeighbour.isOccupied())
					return true;
		}

		return true;
			
	}

	private static Side checkForVictory(CopyBoard copiedBoard){
		
		Field[][] fields = copiedBoard.getBoard();
		
		if(fields[0][0].isOccupiedByKing() || fields[0][fields.length-1].isOccupiedByKing()
				|| fields[fields.length-1][0].isOccupiedByKing() || fields[fields.length-1][fields.length-1].isOccupiedByKing())
			return Side.WHITE;
		
		int kingX = copiedBoard.getKingField().getXPosition();
		int kingY = copiedBoard.getKingField().getYPosition();
		
		if (kingX >= 1 && !(fields[kingX-1][kingY].isOccupiedByBlack() || fields[kingX-1][kingY].isThrone())) return null;
		if (kingX < copiedBoard.size-1 && !(fields[kingX+1][kingY].isOccupiedByBlack() || fields[kingX+1][kingY].isThrone())) return null;
		if (kingY >= 1 && !(fields[kingX][kingY-1].isOccupiedByBlack() || fields[kingX][kingY-1].isThrone())) return null;
		if (kingY < copiedBoard.size-1 && !(fields[kingX][kingY+1].isOccupiedByBlack() || fields[kingX][kingY+1].isThrone())) return null;
		// else
		return Side.BLACK;
	}
	
	public static boolean isMoveLegal(Board board, Move move, Side side){
				
		int fromX = move.getFrom().getXPosition();
		int fromY = move.getFrom().getYPosition();
		int toX = move.getTo().getXPosition();
		int toY = move.getTo().getYPosition();
		
		int size = board.size;
		Field[][] fields = board.getBoard();
		
		if(!(toX == fromX || toY == fromY) )
			return false;
		
		if(toX == fromX && toY == fromY )
			return false;
		
		if(toX < 0 || toX >= size || toY < 0 || toY >= size )
			return false;
		
		if(fromX < 0 || fromX >= size || fromY < 0 || fromY >= size )
			return false;
		
		if(!fields[fromX][fromY].isOccupiedBy(side))
			return false;
		
		if(fields[fromX][fromY].isThrone() && !fields[fromX][fromY].isOccupiedByKing())
			return false;
		
		if(toX < fromX)
			for(int i = fromX-1; i >= toX; i--)
				if(fields[i][toY].isOccupied())
					return false;
		
		if(toX > fromX)
			for(int i = fromX+1; i <= toX; i++)
				if(fields[i][toY].isOccupied())
					return false;
		
		if(toY < fromY)
			for(int i = fromY-1; i >= toY; i--)
				if(fields[toX][i].isOccupied())
					return false;
		
		if(toY > fromY)
			for(int i = fromY+1; i <= toY; i++)
				if(fields[toX][i].isOccupied())
					return false;	
		
		return true;
	}
}

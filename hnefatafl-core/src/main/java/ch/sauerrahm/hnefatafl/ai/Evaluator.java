package ch.sauerrahm.hnefatafl.ai;

import java.util.Set;

import ch.sauerrahm.hnefatafl.Board;
import ch.sauerrahm.hnefatafl.Field;
import ch.sauerrahm.hnefatafl.Side;

public class Evaluator {
	public static float evaluate(Board board, Side side){
		
		Set<Field> whitePieces = board.getOccupiedByWhite();
		Set<Field> blackPieces = board.getOccupiedByBlack();
		
		float pieceDifference = whitePieces.size() - blackPieces.size();
		
		if(side == Side.BLACK)
			pieceDifference *= -1;
		
		return pieceDifference + 100;
	}
}

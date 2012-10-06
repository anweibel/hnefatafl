package ch.sauerrahm.hnefatafl.ai;

import ch.sauerrahm.hnefatafl.Move;

public class ScoredMove {
	private Move move;
	private float score;
	
	public Move getMove() {
		return move;
	}

	public float getScore() {
		return score;
	}

	public ScoredMove(Move move, float score){
		this.move = move;
		this.score = score;
	}
}

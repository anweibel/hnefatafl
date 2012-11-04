package ch.sauerrahm.hnefatafl.ai;

import java.util.List;

import ch.sauerrahm.hnefatafl.Board;
import ch.sauerrahm.hnefatafl.Game;
import ch.sauerrahm.hnefatafl.Move;
import ch.sauerrahm.hnefatafl.Player;
import ch.sauerrahm.hnefatafl.Rules;
import ch.sauerrahm.hnefatafl.Side;
import ch.sauerrahm.hnefatafl.exceptions.IllegalMoveException;
import ch.sauerrahm.hnefatafl.exceptions.VictoryException;

public class AiPlayer implements Player {

	private Side side;
	private long counter = 0;

	public AiPlayer(Side side) {
		this.side = side;
	}

	@Override
	public void handOver(Game game) {
		counter = 0;
		long start = System.currentTimeMillis();
		ScoredMove bestMove = negamax(game.getBoard(), 3,
				Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 1, null);
		System.out.println("counter=" + counter + " in "
				+ (System.currentTimeMillis() - start));
		try {
			game.doMove(bestMove.getMove(), side);
		} catch (VictoryException e) {
			System.out.println("Horray, AI won!");
		} catch (IllegalMoveException e) {
			System.out.println("AI cheated. This should never happen.");
		}
	}

	/**
	 * see http://en.wikipedia.org/wiki/Negamax
	 */
	private ScoredMove negamax(Board board, int depth, float alpha, float beta,
			int color, Move moveToThisSituation) {

		counter++;
		Side currentSide = side;

		if (color == -1)
			currentSide = side.otherSide();

		if (depth == 0)
			return new ScoredMove(moveToThisSituation, color
					* Evaluator.evaluate(board, side));

		List<Move> moves = board.getPossibleMoves(currentSide);
		Move bestMove = null;

		for (Move move : moves) {

			if (move.getFrom().isOccupiedByKing())
				continue;

			Board newBoard = Rules.doMove(move, board);
			ScoredMove negamaxResult = negamax(newBoard, depth - 1, -beta,
					-alpha, -color, move);
			float val = -negamaxResult.getScore();
			if (val >= beta) {
				return new ScoredMove(move, val);
			}
			if (val > alpha) {
				alpha = val;
				bestMove = move;
			}

			if (newBoard.getWinner() != null)
				if (newBoard.getWinner().equals(side))
					return new ScoredMove(move, Float.POSITIVE_INFINITY);
				else
					return new ScoredMove(move, Float.NEGATIVE_INFINITY);
		}

		if (moveToThisSituation != null) {
			return new ScoredMove(moveToThisSituation, alpha);
		} else {
			return new ScoredMove(bestMove, alpha);
		}
	}

	@Override
	public void signalIllegalMove() {
		throw new RuntimeException("This should not happen!");
	}
}

package ch.sauerrahm.hnefatafl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.sauerrahm.hnefatafl.boards.NineBoard;
import ch.sauerrahm.hnefatafl.exceptions.IllegalMoveException;
import ch.sauerrahm.hnefatafl.exceptions.VictoryException;
import ch.sauerrahm.hnefatafl.online.OnlinePlayer;

public class Game 
{
	
	private Player player1;
	private Player player2;
	private List<Move> moveHistory = new ArrayList<Move>(); 
	private Date lastAction = new Date();
	private Result currentState = new Result(new NineBoard(), null, null);
	
	public Game(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
		
		if(player1.getSide().equals(Side.BLACK))
			player1.handOver(this);
		else
			player2.handOver(this);
		
		
	}
    
    public void doMove(Move move, Side side) throws IllegalMoveException, VictoryException{
    	if(!Rules.isMoveLegal(currentState.getBoard(), move, side)){
    		throw new IllegalMoveException();
    	}
    	
    	currentState = Rules.doMove(move, currentState.getBoard());
    	
    	moveHistory.add(move);
    	lastAction = new Date();
    	
    	if(currentState.getWinner() != null){
    		throw new VictoryException(currentState.getWinner());
    	}
    	
    	getNextPlayer(side).handOver(this);    	
    }
    
    private Player getNextPlayer(Side side) {
		if(side == player1.getSide())
			return player2;
		else 
			return player1;
	}

	public Board getBoard(){
    	return currentState.getBoard();
    }
	
	public Result getCurrentState(){
    	return currentState;
    }
	
	public Date getLastAction(){
		return lastAction;
	}
	
	public Side getSide(String username){
		if(player1 instanceof OnlinePlayer){
			if(((OnlinePlayer)player1).getUsername().equalsIgnoreCase(username)){
				return player1.getSide();
			}
		}
		
		if(player2 instanceof OnlinePlayer){
			if(((OnlinePlayer)player2).getUsername().equalsIgnoreCase(username)){
				return player2.getSide();
			}
		}
		
		throw new RuntimeException("User unkonw"); // TODO handle this better
	}
}

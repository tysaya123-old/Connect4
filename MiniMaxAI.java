

import AIModule;

public class MiniMaxAI extends AIModule {

	@Override
	public void getNextMove(GameStateModule game) {
		chosenMove = 0;
		// TODO Loop all possible moves. Return best current move when possible?
		// TODO set d value
		startMinimax(game);
	}
	
	
	startMinimax(GameStateModule game){
		int depth = 5;
		chosenMove = minimax(game, depth);
	}
	
	
	
	
	
	private int minimax(GameStateModule game, int depth) {
		// TODO Auto-generated method stub
		return 0;
	}


	private int Max(GameStateModule game, int depth) {
		int currMax = -1;
		if(depth == 0) {
			return evaluateState(game);
		}
		//TODO Loop for Mins
		for()
		return currMax;
	}
	
	private int Min(GameStateModule game, int depth) {
		int currMin = -1;
		if(depth == 0) {
			return evaluateState(game);
		}
		//TODO Loop for Maxs
		for()
		return currMin;
	}
	
	private int evaluateState(GameStateModule game) {
		//TODO create eval func.
		return 0;
	}

}

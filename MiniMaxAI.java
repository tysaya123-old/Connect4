

import AIModule;

public class MiniMaxAI extends AIModule {

	@Override
	public void getNextMove(GameStateModule game) {
		chosenMove = 0;
		// TODO Loop all possible moves. Return best current move when possible?
		// TODO set d value
		int depth = 5;
			if(player == 1) //MAX Turn
			{
				//TODO Make MAX move.
				chosenMove = Max(game, depth);
			}
			if(player == 2) //MIN Turn
			{
				//TODO Make MIN move.
				chosenMove = Min(game, depth);
			} 
	}
	
	private int Max(GameStateModule game, int depth) {
		int currMax = -1;
		if(depth == 0) {
			return evaluateState(game);
		}
		return currMax;
	}
	
	private int Min(GameStateModule game, int depth) {
		int currMin = -1;
		if(depth == 0) {
			return evaluateState(game);
		}
		return currMin;
	}

}

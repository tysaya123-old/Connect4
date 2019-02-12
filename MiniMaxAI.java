


public class MiniMaxAI extends AIModule {

	private static final int WIDTH = 7;
	private static final int HEIGHT = 6;
	private static final int MAX_INT = Integer.MAX_VALUE;
	private static final int MIN_INT = Integer.MIN_VALUE;
	@Override
	public void getNextMove(GameStateModule game) {
		chosenMove = 0;
		// TODO Loop all possible moves. Return best current move when possible?
		// TODO set d value
		startMinimax(game);
	}
	
	//TODO Unmake move instead of new board
	private void startMinimax(GameStateModule game){
		int currMax = MIN_INT;
		GameStateModule tempBoard;
		int tempMin;
		int depth = 5;
		for(int i = 0; i <= WIDTH; i++) {
			if(game.canMakeMove(i)) {
				tempBoard = game.copy();
				tempBoard.makeMove(i);
				tempMin = min(tempBoard, depth - 1);
				if(tempMin >= currMax) {
					chosenMove = i;
					currMax = tempMin;
				}
			}
		}
	}
	
	
	private int max(GameStateModule game, int depth) {
		int currMax = MIN_INT;
		GameStateModule tempBoard;
		int tempMin;
		
		if(depth == 0) {
			return evaluateState(game);
		}
		
		for(int i = 0; i <= WIDTH; i++) {
			if(game.canMakeMove(i)) {
				tempBoard = game.copy();
				tempBoard.makeMove(i);
				tempMin = min(tempBoard, depth - 1);
				if(tempMin >= currMax) {
					currMax = tempMin;
				}
			}
		}
		return currMax;
	}
	
	private int min(GameStateModule game, int depth) {
		int currMin = MAX_INT;
		GameStateModule tempBoard;
		int tempMax;
		
		if(depth == 0) {
			return evaluateState(game);
		}
		
		for(int i = 0; i <= WIDTH; i++) {
			if(game.canMakeMove(i)) {
				tempBoard = game.copy();
				tempBoard.makeMove(i);
				tempMax = max(tempBoard, depth - 1);
				if(tempMax >= currMin) {
					currMin = tempMax;
				}
			}
		}
		return currMin;
	}
	
	private int evaluateState(GameStateModule game) {
		//TODO create eval func.
		if(game.isGameOver()) {
			if(game.getWinner() == 1) return MAX_INT;
			else return MIN_INT;
		}
		int c = 0;
		for(int i = 0; i <= WIDTH; i++) {
			for(int j = 0; j <= HEIGHT; j++) {
				//TODO add in boost for middle cols
				c+=valueAdjacent(game, i, j);
			}
		}
		return 0;
	}
	
	private int valueAdjacent(GameStateModule game, int i, int j) {
		int player = game.getAt(i, j);
		int c = 0;
		for(int dx = -1; dx <= 1; dx++) {
			for(int dy = -1; dy <= 1; dy++) {
				if(validTile(i + dx, j + dy)) {
					if(game.getAt(i + dx, j + dy) == player) c++; 
				}
			}
		}
		if(player == 1) return c;
		return -c;
	}
	
	private boolean validTile(int x, int y) {
		if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
			return false;
		}
		else return true;
	}

}

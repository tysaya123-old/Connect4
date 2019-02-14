


public class MiniMaxAI extends AIModule {

	private static final int WIDTH = 7;
	private static final int HEIGHT = 6;
	private static final int MAX_INT = Integer.MAX_VALUE;
	private static final int MIN_INT = Integer.MIN_VALUE;
	@Override
	public void getNextMove(GameStateModule game) {
		// TODO Loop all possible moves. Return best current move when possible?
		// TODO set d value
		startMinimax(game);
	}
	
	//TODO Unmake move instead of new board
	private void startMinimax(GameStateModule game){
		//If == then choose closer to middle.
		int depth = 6;
		GameStateModule tempBoard;
		System.out.println("Starting -- ");
		if(game.getActivePlayer() == 1) {
			int currMax = MIN_INT;
			int tempMin;
			for(int i = 0; i <= WIDTH; i++) {
				if(game.canMakeMove(i)) {
					tempBoard = game.copy();
					tempBoard.makeMove(i);
					if(tempBoard.isGameOver()) {
						chosenMove = i;
						break;
					}
					tempMin = min(tempBoard, depth - 1);
					if(tempMin >= currMax) {
						System.out.println("New Move: " + i);
						chosenMove = i;
						currMax = tempMin;
					}
				}
			}
		}
		else {
			int currMin = MAX_INT;
			int tempMax;
			for(int i = 0; i <= WIDTH; i++) {
				if(game.canMakeMove(i)) {
					tempBoard = game.copy();
					tempBoard.makeMove(i);
					if(tempBoard.isGameOver()) {
						chosenMove = i;
						break;
					}
					tempMax = max(tempBoard, depth - 1);
					if(tempMax <= currMin) {
						System.out.println("New Move: " + i);
						chosenMove = i;
						currMin = tempMax;
					}
				}
			}
		}
	}
	
	
	private int max(GameStateModule game, int depth) {
		int currMax = MIN_INT;
		GameStateModule tempBoard;
		int tempMin = 0;
		
		if(depth == 0) {
			return evaluateState(game);
		}
		else if(game.isGameOver()) {
			if(game.getWinner() == 1) {
				return MAX_INT;
			}
			else return MIN_INT;
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
		int tempMax = 0;
		
		if(depth == 0) {
			return evaluateState(game);
		}
		else if(game.isGameOver()) {
			if(game.getWinner() == 1) {
				return MAX_INT;
			}
			else return MIN_INT;
		}
		
		for(int i = 0; i <= WIDTH; i++) {
			if(game.canMakeMove(i)) {
				tempBoard = game.copy();
				tempBoard.makeMove(i);
				tempMax = max(tempBoard, depth - 1);
				if(tempMax <= currMin) {
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
		return c;
	}
	 
	private int valueAdjacent(GameStateModule game, int x, int y) {
		int player = game.getAt(x, y);
		int c = 0;
		int behind;
		int next;
		int openSides;
		int l;
		for(int dx = -1; dx <= 1; dx++) {
			for(int dy = -1; dy <= 1; dy++) {
				openSides = 0;
				l = 1;
				if(validTile(x - dx, y - dy)) {
					behind = game.getAt(x - dx, y - dy);
					if(behind == 0) {
						openSides++;
					}
				}
				else behind = 3;
				for(int m = 0; m < 4; m++) {
					if(validTile(x + m*dx, y + m*dy)) {
						next = game.getAt(x + m*dx, y + m*dy);
						if(next == player) l = m;
						else if(next == 0) {
							openSides++;
							break;
						}
						else break;
					}
				}
				//TODO mess with this function
				c+=Math.pow(10, l*openSides);
			}
		}
		if(player == 1) return c;
		return -c;
	}

		
		
		
		
//		int player = game.getAt(i, j);
//		
//		int c = 0;
//		for(int dx = -1; dx <= 1; dx++) {
//			for(int dy = -1; dy <= 1; dy++) {
//				if(validTile(i + dx, j + dy)) {
//					int nextTile = game.getAt(i + dx, j + dy);
//					if( nextTile == player) {
//						int nextNext = game.getAt(i + 2*dx, j + 2*dy);
//						if(nextNext == player) {
//							int nextNN = game.getAt(i + 3*dx, j + 3*dy);
//							int backNext = game.getAt(i - dx, j - dy);
//							if(nextNN == 0 && backNext == 0) {
//								c+=100;
//							}
//							else if(nextNN == 0) {
//								c+=20;
//							}
//							else if(backNext == 0) {
//								c+=20;
//							}
//							else c+=10;
//						}
//						if(nextNext == 0) {
//							c+=2;
//						}
//						else c++;
//					}
//					else if(nextTile == 0) c++;
//				}
//			}
//		}
//		if(player == 1) return c;
//		return -c;
//	}
//				
	
	private boolean validTile(int x, int y) {
		if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
			return false;
		}
		else return true;
	}

}

package ai;

public class MiniMaxAI {

	private static int MAX_DEPTH = 6;
	
	private char playerAI;
	private char player;
	
	private int row;
	private int col;
	
	public MiniMaxAI(char player, char playerAI) {
		this.player = player;
		this.playerAI = playerAI;
	}
	
	public int minimax(char board[][],int depth, boolean max) {
		 int score = evaluate(board);

		    if (score == 10 || score == -10)
		        return score;

		    if (!movesLeft(board))
		        return 0;

		    if (max) {
		        int best = Integer.MIN_VALUE;
		        for (int i = 0; i < 3; i++) {
		            for (int j = 0; j < 3; j++) {
		                if (board[i][j] == 0) {
		                    board[i][j] = playerAI;
		                    best = Math.max(best, minimax(board, depth + 1, !max));
		                    board[i][j] = 0;
		                }
		            }
		        }
		        return best;
		    } else {
		        int best = Integer.MAX_VALUE;
		        for (int i = 0; i < 3; i++) {
		            for (int j = 0; j < 3; j++) {
		                if (board[i][j] == 0) {
		                    board[i][j] = player;
		                    best = Math.min(best, minimax(board, depth + 1, !max));
		                    board[i][j] = 0;
		                }
		            }
		        }
		        return best;
		    }
		}
	
	public int evaluate(char board[][]) {
		
		for(int i = 0; i < 3; i++) {
			if(board[i][0] == board[i][1] &&
				board[i][1] == board[i][2]) {
				if(board[i][0] == playerAI) {
					return +10;
				} else if(board[i][0] == player) {
					return -10;
				}
			}
		}
		
		for(int j = 0; j < 3; j++) {
			if(board[0][j] == board[1][j] &&
					board[1][j] == board[2][j]) {
					if(board[0][j] == playerAI) {
						return +10;
					} else if(board[0][j] == player) {
						return -10;
					}
				}
		}
		
		if(board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			if(board[0][0] == playerAI) {
				return +10;
			}else if(board[0][0] == player) {
				return -10;
			}
		}
		
		if(board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			if(board[0][2] == playerAI) {
				return +10;
			}else if(board[0][2] == player) {
				return -10;
			}
		}
		
		return 0;
	}
	
	public boolean movesLeft(char board[][]) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int[] findBestMove(char board[][]) {
		int bestVal = -1000;
		row = -1;
		col = -1;
		int[] bestMove = new int[2];
		bestMove[0] = row;
		bestMove[1] = col;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				if(board[i][j] == 0) {
					board[i][j] = playerAI;
					
					int moveVal = minimax(board, 0, false);
					
					board[i][j] = 0;
					
					if(moveVal > bestVal) {
						bestMove[0] = i;
						bestMove[1] = j;
						bestVal = moveVal;
					}
				}
			}
		}
		System.out.println("Melhor movimento: " + bestMove[0] +", " + bestMove[1]);
		return bestMove;
	}
	
}

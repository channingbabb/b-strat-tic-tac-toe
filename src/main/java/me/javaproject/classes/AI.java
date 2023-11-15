package me.javaproject.classes;

public class AI {
    private Board board;

    public AI(Board board) {
        this.board = board;
    }

    public int pickNextMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        // Loop through all possible moves
        for (int i = 0; i < 9; i++) {
            if (board.isValidMove(i)) {
                // Make the move for AI (assuming AI is 'O')
                board.makeMove(i, "O");

                // Evaluate the move using minimax and alpha-beta pruning
                int score = minimax(0, Integer.MIN_VALUE, Integer.MAX_VALUE, false);

                // Undo the move for backtracking
                board.undoMove(i);

                // If the current move is better than the best found so far, update the best score and move
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }

        return bestMove; // Return the best move found
    }

    private int minimax(int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        // Base case: check for endgame or depth limit
        if (board.isGameOver() || depth == 3) {
            return board.getScore(); // Use a scoring function to evaluate board state
        }

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;

            // Explore all valid moves for 'O'
            for (int i = 0; i < 9; i++) {
                if (board.isValidMove(i)) {
                    board.makeMove(i, "O");
                    int eval = minimax(depth + 1, alpha, beta, false);
                    board.undoMove(i);

                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(alpha, eval);

                    // Alpha-beta pruning
                    if (beta <= alpha) {
                        break;
                    }
                }
            }

            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;

            // Explore all valid moves for 'X'
            for (int i = 0; i < 9; i++) {
                if (board.isValidMove(i)) {
                    board.makeMove(i, "X");
                    int eval = minimax(depth + 1, alpha, beta, true);
                    board.undoMove(i);

                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, eval);

                    // Alpha-beta pruning
                    if (beta <= alpha) {
                        break;
                    }
                }
            }

            return minEval;
        }
    }
}

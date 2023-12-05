package me.javaproject.interfaces;

import me.javaproject.classes.Board;
import me.javaproject.classes.Cell;
import me.javaproject.classes.Game;

public interface AIInterface {

    /**
     * Choose the next move on the specified board.
     *
     * @param nextMoveBoard The index of the board where the move should be made.
     * @return The index of the cell where the AI chooses to make its move.
     */
    int chooseNextMove(int nextMoveBoard);

    /**
     * Generate a list of possible moves for the AI on the specified board.
     *
     * @param board
     * @return An array of indices representing possible moves on the specified board.
     */
    Cell[] generatePossibleMoves(Board board);

    /**
     * Choose the best move from a list of possible moves based on a heuristic.
     *
     * @param game
     * @param board
     * @param possibleMoves An array of indices representing possible moves on the specified board.
     * @return The index of the cell representing the best move among the possible moves.
     */
    int chooseBestMove(Game game, Board board, int[] possibleMoves);

}

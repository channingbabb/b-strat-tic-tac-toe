package me.javaproject.interfaces;

import me.javaproject.classes.Cell;

public interface BoardInterface {

    /**
     * Makes a move on a specific cell of the board.
     *
     * @param cellIndex     The index of the cell where the move should be made.
     * @param playerSymbol  The symbol of the player making the move (e.g., "X" or "O").
     * @return true if the move is valid and made successfully, false otherwise.
     */
    boolean makeMove(int cellIndex, String playerSymbol);

    /**
     * Checks the winner of the board.
     *
     * @return The symbol of the winning player ("X", "O") or null if there is no winner yet.
     */
    String checkWinner();

    /**
     * Gets the current state of the board as an array of cell states.
     *
     * @return An array of strings representing the current state of each cell on the board.
     */
    String[] getBoardState();

    /**
     * Determines if a move is valid on this board.
     *
     * @param cellIndex The index of the cell to check for a valid move.
     * @return true if the move is valid, false otherwise.
     */
    boolean isValidMove(int cellIndex);

    /**
     * Checks if the board is full (no more moves possible).
     *
     * @return true if the board is full, false otherwise.
     */
    boolean isBoardFull();

    /**
     * Gets the cells that are open (not taken by a player).
     * 
     * @return
     */
    Cell[] getOpenCells();

    /**
     * Gets all the cells in the board.
     *
     * @return An array of cells.
     */
    Cell[] getCells();

    /**
     * Gets all cells from a certain player.
     * 
     * @param playerSymbol
     * @return An array of cells.
     */
    Cell[] getCellsFromPlayer(String playerSymbol);
}

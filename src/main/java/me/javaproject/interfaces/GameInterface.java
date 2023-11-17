package me.javaproject.interfaces;

import com.fasterxml.jackson.databind.node.ArrayNode;

import me.javaproject.classes.Board;

public interface GameInterface {


    int getNextMoveBoard();

    int getNextMoveCell();

    // Method to make a move on a specific board and cell
    boolean makeMove(int boardIndex, int cellIndex, String playerSymbol);

    // Method to check the winner of a specific board
    String checkBoardWinner(int boardIndex);

    // Method to check the overall winner of the game
    String checkOverallWinner();

    // Method to get the current state of a specific board
    String[][] getBoardState(int boardIndex);

    // Method to determine if a move is valid
    boolean isValidMove(int boardIndex, int cellIndex);

    // Method to get the current player
    String getCurrentPlayer();

    // Method to generate return JSON
    ArrayNode generateJSON(int aiMovedTo);

    // Method to switch the current player
    void switchPlayer();

    // Method to get the board from the index
    Board getBoard(int boardIndex);

}

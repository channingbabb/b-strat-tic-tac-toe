package me.javaproject.classes;

import me.javaproject.interfaces.GameInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.Arrays;

public class Game implements GameInterface {

    private Board[] boards; // Array of boards
    private String currentPlayer;
    private int nextMoveBoard; // Store the next move board index
    private int nextMoveCell; // Store the next move cell index
    private String gameWonBy;

    // Constructor
    public Game(Board[] boards, int nextMoveBoard, int nextMoveCell) {
        if (boards.length != 9 || boards[0].getCells().length != 9) {
            throw new IllegalArgumentException(
                    "Game must be initialized with 9 boards, each containing 9 cells.  Length of boards: "
                            + boards.length + ", length of cells: " + boards[0].getCells().length + ".");
        }

        currentPlayer = "X";

        // Set the board, next move board and cell
        this.boards = boards;
        this.nextMoveBoard = nextMoveBoard;
        this.nextMoveCell = nextMoveCell;
        this.gameWonBy = this.checkOverallWinner();
    }

    @Override
    public int getNextMoveBoard() {
        return nextMoveBoard;
    }

    @Override
    public int getNextMoveCell() {
        return nextMoveCell;
    }

    @Override
    public boolean makeMove(int boardIndex, int cellIndex, String playerSymbol) {
        if (isValidMove(boardIndex, cellIndex)) {
            boards[boardIndex].makeMove(cellIndex, playerSymbol);
            switchPlayer();
            return true;
        }
        return false;
    }

    @Override
    public String checkBoardWinner(int boardIndex) {
        return boards[boardIndex].checkWinner();
    }

    @Override
    public String checkOverallWinner() {

        for (int row = 0; row < 9; row += 3) {
            if (!boards[row].isEmpty() &&
                    boards[row].getWinner().equals(boards[row + 1].getWinner()) &&
                    boards[row].getWinner().equals(boards[row + 2].getWinner())) {
                return boards[row].getWinner();
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (!boards[col].isEmpty() &&
                    boards[col].getWinner().equals(boards[col + 3].getWinner()) &&
                    boards[col].getWinner().equals(boards[col + 6].getWinner())) {
                return boards[col].getWinner();
            }
        }

        // Check diagonals
        if (!boards[4].isEmpty()) {
            if (boards[0].getWinner().equals(boards[4].getWinner()) &&
                    boards[0].getWinner().equals(boards[8].getWinner())) {
                return boards[0].getWinner();
            }
            if (boards[2].getWinner().equals(boards[4].getWinner()) &&
                    boards[2].getWinner().equals(boards[6].getWinner())) {
                return boards[2].getWinner();
            }
        }

        return "null"; // if there is no winner found
    }

    @Override
    public String[][] getBoardState(int boardIndex) {
        return new String[][]{boards[boardIndex].getBoardState()};
    }

    @Override
    public boolean isValidMove(int boardIndex, int cellIndex) {
        return boardIndex >= 0 && boardIndex < 9
                && !boards[boardIndex].isBoardFull()
                && boards[boardIndex].isValidMove(cellIndex);
    }

    @Override
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public Board getBoard(int boardIndex) {
        return boards[boardIndex];
    }

    @Override
    public ArrayNode generateJSON(int aiMovedTo) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode cellsNode = objectMapper.createArrayNode();
        String[] boardsWonArr = new String[9];
        int j = 0;
        for (Board board : this.boards) {
            if (board.isWon()) {
                boardsWonArr[j] = '"'+board.getWinner()+'"';
            } else {
                boardsWonArr[j] = "null";
            }
            ObjectNode boardObjectNode = objectMapper.createObjectNode(); // Create an object node for the board
            for (int i = 0; i < board.getCells().length; i++) {
                boardObjectNode.put(String.valueOf(i), board.getCells()[i].getSymbol()); // Add the cell to the board
                // object node
            }
            cellsNode.add(boardObjectNode); // Add the board object node to the cells node
            j++;
        }

        ArrayNode rootNode = objectMapper.createArrayNode();
        ObjectNode aiMovedToNode = objectMapper.createObjectNode();
        ObjectNode boardsWon = objectMapper.createObjectNode();
        ObjectNode gameWon = objectMapper.createObjectNode();
        aiMovedToNode.put("aiMovedTo", aiMovedTo);
        boardsWon.put("boardsWon", Arrays.toString(boardsWonArr));
        gameWon.put("gameWon", this.gameWonBy);
        rootNode.add(cellsNode);
        rootNode.add(aiMovedToNode);
        rootNode.add(boardsWon);
        rootNode.add(gameWon);

        return rootNode;
    }

    @Override
    public void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    public Board[] getBoards() {
        return boards;
    }
}
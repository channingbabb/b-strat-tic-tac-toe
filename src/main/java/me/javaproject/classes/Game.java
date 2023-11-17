package me.javaproject.classes;

import me.javaproject.interfaces.GameInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Game implements GameInterface {

    private Board[] boards; // Array of boards
    private String currentPlayer;
    private int nextMoveBoard; // Store the next move board index
    private int nextMoveCell; // Store the next move cell index

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
        // Implement the logic to check the overall winner of the game
        return null; // Placeholder
    }

    @Override
    public String[][] getBoardState(int boardIndex) {
        return new String[][] { boards[boardIndex].getBoardState() };
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
    public ArrayNode generateJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode cellsNode = objectMapper.createArrayNode();

        for (Board board : this.boards) {
            ObjectNode boardObjectNode = objectMapper.createObjectNode(); // Create an object node for the board
            for (int i = 0; i < board.getCells().length; i++) {
                boardObjectNode.put(String.valueOf(i), board.getCells()[i].getSymbol()); // Add the cell to the board
                                                                                         // object node
            }
            cellsNode.add(boardObjectNode); // Add the board object node to the cells node
        }

        ArrayNode rootNode = objectMapper.createArrayNode();
        rootNode.add(cellsNode);

        return rootNode;
    }

    @Override
    public void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }
}

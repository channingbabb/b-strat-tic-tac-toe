package me.javaproject.classes;

import com.fasterxml.jackson.databind.JsonNode;
import me.javaproject.interfaces.BoardInterface;
import me.javaproject.interfaces.CellInterface;

public class Board implements BoardInterface {

    private Cell[] cells; // Array of cells

    // Constructor
    public Board(Cell[] cells) {
        if (cells.length != 9) {
            throw new IllegalArgumentException("A board must be initialized with 9 cells");
        }
        this.cells = cells;
    }

    @Override
    public boolean makeMove(int cellIndex, String playerSymbol) {
        if (isValidMove(cellIndex)) {
            cells[cellIndex].setSymbol(playerSymbol);
            return true;
        }
        return false;
    }

    // naya - adding logic to check the winner of the board
    @Override
    public String checkWinner() {

        // array of array of winning moves
        int[][] winningMoves = new int[][] {
                {0, 1, 2}, // top row
                {3, 4, 5}, // middle row
                {6, 7, 8}, // bottom row
                {0, 3, 6}, // left column
                {1, 4, 7}, // middle column
                {2, 5, 8}, // right column
                {0, 4, 8}, // top left to bottom right diagonal
                {2, 4, 6} // top right to bottom left diagonal
        };
        // check if there is a winner
        for (int[] winningMove : winningMoves) {
            String firstCell = cells[winningMove[0]].getSymbol();
            String secondCell = cells[winningMove[1]].getSymbol();
            String thirdCell = cells[winningMove[2]].getSymbol();
            if (!firstCell.equals("null") && firstCell.equals(secondCell) && firstCell.equals(thirdCell)) {
                return firstCell;
            }
        }
        return "null"; // if there is no winner found
    }

    // naya just added this
    public boolean isWon() {
        // Implement the logic to check if a winner exists
        String winner = checkWinner();
        return winner != "null" && !winner.isEmpty();
    }

    // get winner
    public String getWinner() {
        return checkWinner();
    }


    @Override
    public String[] getBoardState() {
        String[] state = new String[9];
        for (int i = 0; i < cells.length; i++) {
            state[i] = cells[i].getSymbol();
        }
        return state;
    }

    @Override
    public boolean isValidMove(int cellIndex) {
        return cellIndex >= 0 && cellIndex < 9 && cells[cellIndex].isEmpty();
    }

    @Override
    public Cell[] getCells() {
        return cells;
    }

    @Override
    public Cell[] getOpenCells() {
        int openCellCount = 0;
        for (Cell cell : cells) {
            if (cell.isEmpty()) {
                openCellCount++;
            }
        }
        Cell[] openCells = new Cell[openCellCount];
        int openCellIndex = 0;
        for (Cell cell : cells) {
            if (cell.isEmpty()) {
                openCells[openCellIndex] = cell;
                openCellIndex++;
            }
        }
        return openCells;
    }

    @Override
    public Cell[] getCellsFromPlayer(String playerSymbol) {
        int playerCellCount = 0;
        for (Cell cell : cells) {
            if (cell.getSymbol().equals(playerSymbol)) {
                playerCellCount++;
            }
        }
        Cell[] playerCells = new Cell[playerCellCount];
        int playerCellIndex = 0;
        for (Cell cell : cells) {
            if (cell.getSymbol().equals(playerSymbol)) {
                playerCells[playerCellIndex] = cell;
                playerCellIndex++;
            }
        }
        return playerCells;
    }

    @Override
    public boolean isBoardFull() {
        for (CellInterface cell : cells) {
            if (cell.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        for (CellInterface cell : cells) {
            if (!cell.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

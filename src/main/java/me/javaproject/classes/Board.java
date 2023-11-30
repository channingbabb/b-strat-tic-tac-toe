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
        // Check rows
        for (int row = 0; row < 9; row += 3) {
            if (!cells[row].isEmpty() &&
                    cells[row].getSymbol().equals(cells[row + 1].getSymbol()) &&
                    cells[row].getSymbol().equals(cells[row + 2].getSymbol())) {
                return cells[row].getSymbol();
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (!cells[col].isEmpty() &&
                    cells[col].getSymbol().equals(cells[col + 3].getSymbol()) &&
                    cells[col].getSymbol().equals(cells[col + 6].getSymbol())) {
                return cells[col].getSymbol();
            }
        }

        // Check diagonals
        if (!cells[4].isEmpty()) {
            if (cells[0].getSymbol().equals(cells[4].getSymbol()) &&
                    cells[0].getSymbol().equals(cells[8].getSymbol())) {
                return cells[0].getSymbol();
            }
            if (cells[2].getSymbol().equals(cells[4].getSymbol()) &&
                    cells[2].getSymbol().equals(cells[6].getSymbol())) {
                return cells[2].getSymbol();
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

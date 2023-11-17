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

    @Override
    public String checkWinner() {
        // Implement logic to check for winner in the board
        return null; // Placeholder
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
}

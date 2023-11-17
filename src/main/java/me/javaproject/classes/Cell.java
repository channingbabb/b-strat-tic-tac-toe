package me.javaproject.classes;

import me.javaproject.interfaces.CellInterface;

public class Cell implements CellInterface {

    private String symbol = "null"; // Represents the symbol ('X', 'O', or null for empty)
    private int position; // Represents the position of the cell in the board

    // Constructor
    public Cell(String cellState, int position) {
        symbol = cellState;
        this.position = position;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean isEmpty() {
        return symbol == null || symbol.isEmpty() || symbol.equals("null");
    }
}

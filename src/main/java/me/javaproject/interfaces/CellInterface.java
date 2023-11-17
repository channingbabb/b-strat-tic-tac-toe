package me.javaproject.interfaces;

public interface CellInterface {

    /**
     * Sets the symbol for the cell (e.g., 'X' or 'O').
     *
     * @param symbol The symbol to set for the cell.
     */
    void setSymbol(String symbol);

    /**
     * Gets the current symbol of the cell.
     *
     * @return The symbol of the cell ('X', 'O', or empty).
     */
    String getSymbol();

    /**
     * Checks if the cell is empty.
     *
     * @return true if the cell is empty, false otherwise.
     */
    boolean isEmpty();


    int getPosition();

    void setPosition(int position);

}

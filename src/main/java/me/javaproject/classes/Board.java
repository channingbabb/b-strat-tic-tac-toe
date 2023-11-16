package me.javaproject.classes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Board looks like:
 * {
 *     "0": "X",
 *     "1": "null",
 *     "2": "null",
 *     "3": "null",
 *     "4": "null",
 *     "5": "null",
 *     "6": "null",
 *     "7": "null",
 *     "8": "O"
 * }
 */
public class Board {
    JsonNode board;
    public Board(JsonNode board) {
        this.board = board;
        System.out.println(board);
    }

    public boolean isValidMove(int cell) {
        JsonNode cellValue = this.board.get(String.valueOf(cell));
        return cellValue != null && "null".equals(cellValue.asText());
    }

    public void makeMove(int cell, String player) {
        ((ObjectNode) this.board).put(String.valueOf(cell), player);
    }

    public void undoMove(int cell) {
        ((ObjectNode) this.board).put(String.valueOf(cell), "null");
    }

    public int getScore() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (this.board.get(i) != null && this.board.get(i) == this.board.get(i + 1) && this.board.get(i) == this.board.get(i + 2)) {
                if (this.board.get(i).asText().equals("X")) {
                    return 1;
                } else if (this.board.get(i).asText().equals("O")) {
                    return -1;
                }
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (this.board.get(i) != null && this.board.get(i).asText().equals("X") && this.board.get(i) == this.board.get(i + 3) && this.board.get(i) == this.board.get(i + 6)) {
                return 1;
            } else if (this.board.get(i) != null && this.board.get(i).asText().equals("O") && this.board.get(i) == this.board.get(i + 3) && this.board.get(i) == this.board.get(i + 6)) {
                return -1;
            }
        }

        // Check diagonals
        if (this.board.get(0) != null && this.board.get(0).asText().equals("X") && this.board.get(0) == this.board.get(4) && this.board.get(0) == this.board.get(8)) {
            return 1;
        } else if (this.board.get(0) != null && this.board.get(0).asText().equals("O") && this.board.get(0) == this.board.get(4) && this.board.get(0) == this.board.get(8)) {
            return -1;
        }

        if (this.board.get(2) != null && this.board.get(2).asText().equals("X") && this.board.get(2) == this.board.get(4) && this.board.get(2) == this.board.get(6)) {
            return 1;
        } else if (this.board.get(2) != null && this.board.get(2).asText().equals("O") && this.board.get(2) == this.board.get(4) && this.board.get(2) == this.board.get(6)) {
            return -1;
        }

        // No winner
        return 0;
    }

    public boolean isGameOver() {
        return getScore() != 0;
    }


}

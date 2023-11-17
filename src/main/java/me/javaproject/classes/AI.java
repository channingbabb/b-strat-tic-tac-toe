package me.javaproject.classes;

import me.javaproject.interfaces.AIInterface;
import java.util.Random;

public class AI implements AIInterface {
    final private String AI_SYMBOL = "O";
    Game game;

    public AI(Game game) {
        this.game = game;
    }

    @Override
    public int chooseNextMove(int nextMoveBoard) {
        Board board = game.getBoard(nextMoveBoard);
        Cell[] cells = generatePossibleMoves(board);
        int[] possibleMoves = new int[cells.length];
        for (int i = 0; i < cells.length; i++) {
            possibleMoves[i] = cells[i].getPosition();
        }
        int bestMove = chooseBestMove(game, board, possibleMoves);
        return bestMove;
    }

    @Override
    public int evaluateGame() {
        // This is STRATEGIC tic tac toe.  This means that there are 9 boards, and 9 cells in each board.
        //
        // The board is represented as a 1D array of 9 boards, each containing 9 cells.  The board is indexed
        throw new UnsupportedOperationException("Unimplemented method 'evaluateGame'");
    }

    @Override
    public int applyStrategy(int nextMoveBoard) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyStrategy'");
    }

    @Override
    public Cell[] generatePossibleMoves(Board board) {
        Cell[] cells = board.getOpenCells();
        return cells;

    }

    @Override
    public int chooseBestMove(Game game, Board board, int[] possibleMoves) {
        Cell[] playersCells = board.getCellsFromPlayer(AI_SYMBOL);
        if (playersCells.length == 0) {
            // If the AI has no moves, choose a random move
            Random random = new Random();
            return possibleMoves[random.nextInt(possibleMoves.length)];
        }
        // Check for horizontal wins
        for (int i = 0; i < 3; i++) {
            int rowStart = i * 3;
            if (playersCells[rowStart] != null && playersCells[rowStart].getSymbol().equals(AI_SYMBOL) &&
                    playersCells[rowStart + 1] != null && playersCells[rowStart + 1].getSymbol().equals(AI_SYMBOL) &&
                    playersCells[rowStart + 2] != null && playersCells[rowStart + 2].getSymbol().equals(AI_SYMBOL)) {
                return rowStart + 1; // Return the middle cell of the winning row
            }
        }

        // Check for vertical wins
        for (int i = 0; i < 3; i++) {
            int colStart = i;
            if (playersCells[colStart] != null && playersCells[colStart].getSymbol().equals(AI_SYMBOL) &&
                    playersCells[colStart + 3] != null && playersCells[colStart + 3].getSymbol().equals(AI_SYMBOL) &&
                    playersCells[colStart + 6] != null && playersCells[colStart + 6].getSymbol().equals(AI_SYMBOL)) {
                return colStart + 3; // Return the middle cell of the winning column
            }
        }

        // Check for diagonal wins
        if (playersCells[0] != null && playersCells[0].getSymbol().equals(AI_SYMBOL) &&
                playersCells[4] != null && playersCells[4].getSymbol().equals(AI_SYMBOL) &&
                playersCells[8] != null && playersCells[8].getSymbol().equals(AI_SYMBOL)) {
            return 4; // Return the center cell of the diagonal win
        }

        if (playersCells[2] != null && playersCells[2].getSymbol().equals(AI_SYMBOL) &&
                playersCells[4] != null && playersCells[4].getSymbol().equals(AI_SYMBOL) &&
                playersCells[6] != null && playersCells[6].getSymbol().equals(AI_SYMBOL)) {
            return 4; // Return the center cell of the diagonal win
        }

        // If no winning moves are found, choose a random available move
        Random random = new Random();
        return possibleMoves[random.nextInt(possibleMoves.length)];
    }


    @Override
    public void cleanup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cleanup'");
    }
}
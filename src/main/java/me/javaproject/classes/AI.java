package me.javaproject.classes;

import me.javaproject.interfaces.AIInterface;
import java.util.Random;

public class AI implements AIInterface {
    final private String AI_SYMBOL = "O";
    Game game;

    public AI(Game game) {
        this.game = game;
    }

    // modify this to check if board is already won or not
    @Override
    public int chooseNextMove(int nextMoveBoard) {

        Board board = game.getBoard(nextMoveBoard);

        // Check if the selected board is already won or full. If so, find a new board.
        do {
            nextMoveBoard = findPlayableBoard();
            board = game.getBoard(nextMoveBoard); // Update the board reference
        } while (board.isWon() || board.isBoardFull());

//        if (board.isWon() || board.isBoardFull()) {
//            nextMoveBoard = findPlayableBoard();
//            board = game.getBoard(nextMoveBoard); // Update the board reference
//        }

        // Now that we have a valid board, generate possible moves
        Cell[] cells = generatePossibleMoves(board);
        int[] possibleMoves = new int[cells.length];
        for (int i = 0; i < cells.length; i++) {
            possibleMoves[i] = cells[i].getPosition();
        }

        // Choose the best move from the possible moves
        int bestMove = chooseBestMove(game, board, possibleMoves);
        return bestMove;

       // Board board = game.getBoard(nextMoveBoard);
       // Cell[] cells = generatePossibleMoves(board);
       // int[] possibleMoves = new int[cells.length];
       // for (int i = 0; i < cells.length; i++) {
        //    possibleMoves[i] = cells[i].getPosition();
        //}
       // int bestMove = chooseBestMove(game, board, possibleMoves);
        //return bestMove;
    }

    // naya added this
    private int findPlayableBoard() {
        for (int i = 0; i < game.getBoards().length; i++) {
            Board board = game.getBoard(i);
            if (!board.isWon() && !board.isBoardFull()) {
                return i; // Return index of a playable board
            }
        }
        throw new IllegalStateException("No playable boards available");
    }
    @Override
    public int evaluateGame() {
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

    // modify this to check if a board is already won
    @Override
    public int chooseBestMove(Game game, Board board, int[] possibleMoves) {
        while (board.isBoardFull()) {
            // generate random char between 0 and 8
            Random random = new Random();
            int randomInt = random.nextInt(9);
            board = game.getBoard(randomInt);
        }
        Cell[] playersCells = board.getCells();
        if (playersCells.length == 0) {
            // If the AI has no moves, choose a random move
            Random random = new Random();
            return possibleMoves[random.nextInt(possibleMoves.length)];
        }

        // check for a horizontal win
        for(int i = 0; i < 3; i++){
            int rowStart = i * 3;

            // check for a board winning move to the left
            if (playersCells[rowStart] == null &&
                    playersCells[rowStart + 1] == null && playersCells[rowStart].getSymbol().equals(AI_SYMBOL) &&
                    playersCells[rowStart + 2] != null && playersCells[rowStart].getSymbol().equals(AI_SYMBOL)) {
                return possibleMoves[rowStart];
            }

            // check for a board winning move in the middle
            if (playersCells[rowStart] != null && playersCells[rowStart].getSymbol().equals(AI_SYMBOL) &&
                    playersCells[rowStart + 1] == null &&
                    playersCells[rowStart + 2] != null && playersCells[rowStart].getSymbol().equals(AI_SYMBOL)) {
                return possibleMoves[rowStart + 1];
            }


            // check for a board winning move to the right
            if (playersCells[rowStart] != null && playersCells[rowStart].getSymbol().equals(AI_SYMBOL) &&
                    playersCells[rowStart + 1] != null && playersCells[rowStart].getSymbol().equals(AI_SYMBOL) &&
                    playersCells[rowStart + 2] == null) {
                return possibleMoves[rowStart + 2];
            }
        }

//        // Check for horizontal wins
//        for (int i = 0; i < 3; i++) {
//            int rowStart = i * 3;
//            if (playersCells[rowStart] != null && playersCells[rowStart].getSymbol().equals(AI_SYMBOL) &&
//                    playersCells[rowStart + 1] != null && playersCells[rowStart + 1].getSymbol().equals(AI_SYMBOL) &&
//                    playersCells[rowStart + 2] != null && playersCells[rowStart + 2].getSymbol().equals(AI_SYMBOL)) {
//                return rowStart + 1; // Return the middle cell of the winning row
//            }
//        }

        // Check for vertical wins
//        for (int i = 0; i < 3; i++) {
//            int colStart = i;
//            if (playersCells[colStart] != null && playersCells[colStart].getSymbol().equals(AI_SYMBOL) &&
//                    playersCells[colStart + 3] != null && playersCells[colStart + 3].getSymbol().equals(AI_SYMBOL) &&
//                    playersCells[colStart + 6] != null && playersCells[colStart + 6].getSymbol().equals(AI_SYMBOL)) {
//                return colStart + 3; // Return the middle cell of the winning column
//            }
//        }

        // Check for diagonal wins
//        if (playersCells[0] != null && playersCells[0].getSymbol().equals(AI_SYMBOL) &&
//                playersCells[4] != null && playersCells[4].getSymbol().equals(AI_SYMBOL) &&
//                playersCells[8] != null && playersCells[8].getSymbol().equals(AI_SYMBOL)) {
//            return 4; // Return the center cell of the diagonal win
//        }
//
//        if (playersCells[2] != null && playersCells[2].getSymbol().equals(AI_SYMBOL) &&
//                playersCells[4] != null && playersCells[4].getSymbol().equals(AI_SYMBOL) &&
//                playersCells[6] != null && playersCells[6].getSymbol().equals(AI_SYMBOL)) {
//            return 4; // Return the center cell of the diagonal win
//        }


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
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
        while (board.isWon() || board.isBoardFull()) {
            nextMoveBoard = this.findPlayableBoard();
            board = game.getBoard(nextMoveBoard); // Update the board reference
        }
        Cell[] cells = generatePossibleMoves(board);
        int[] possibleMoves = new int[cells.length];
        for (int i = 0; i < cells.length; i++) {
            possibleMoves[i] = cells[i].getPosition();
        }
        int bestMove = chooseBestMove(game, board, possibleMoves);
        return bestMove;
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
        throw new UnsupportedOperationException("Unimplemented method 'applyStrategy'");
    }

    @Override
    public Cell[] generatePossibleMoves(Board board) {
        Cell[] cells = board.getOpenCells();
        return cells;

    }
    
    @Override
    public int chooseBestMove(Game game, Board board, int[] possibleMoves) {
        while (board.isBoardFull() || board.isWon()) {
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

        // get cells from AI player
        Cell[] aiCells = board.getCellsFromPlayer(AI_SYMBOL);

        // loop through AI cells and check if there is a winning move
        for (int i = 0; i < aiCells.length; i++) {
            // get the position of the cell
            int cellPosition = aiCells[i].getPosition();

            // loop through winning moves
            for (int j = 0; j < winningMoves.length; j++) {
                // get the winning move
                int[] winningMove = winningMoves[j];

                System.out.println(cellPosition);

                // check if the cell position is in the winning move
                if (cellPosition == winningMove[0] || cellPosition == winningMove[1] || cellPosition == winningMove[2]) {
                    System.out.println("Found a winning move");
                    System.out.println(playersCells[winningMove[0]].getSymbol().getClass().getName());
                    // check if the other two cells in the winning move are empty
                    if (playersCells[winningMove[0]].getSymbol().equals("null")) {
                        return winningMove[0];
                    } else if (playersCells[winningMove[1]].getSymbol().equals("null")) {
                        return winningMove[1];
                    } else if (playersCells[winningMove[2]].getSymbol().equals("null")) {
                        return winningMove[2];
                    }
                }
            }
        }

        System.out.println("No moves found");

        // If no winning moves are found, choose a random available move
        Random random = new Random();
        return possibleMoves[random.nextInt(possibleMoves.length)];

    }

    @Override
    public void cleanup() {
        throw new UnsupportedOperationException("Unimplemented method 'cleanup'");
    }
}
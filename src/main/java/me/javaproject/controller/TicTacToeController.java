package me.javaproject.controller;

import me.javaproject.classes.AI;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import me.javaproject.classes.Board;
import me.javaproject.classes.Cell;
import me.javaproject.classes.Game;

@RestController
@RequestMapping("/tictactoe")
public class TicTacToeController {

    public Game parseGame(String requestBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(requestBody);

// Create Cell instances
        JsonNode cellsNode = rootNode.get(0);
        Cell[] cells = new Cell[81]; // 9 boards * 9 cells per board
        for (int i = 0; i < 9; i++) {
            JsonNode boardNode = cellsNode.get(i);
            for (int j = 0; j < 9; j++) {
                String cellState = boardNode.get(String.valueOf(j)).asText();
                cells[i * 9 + j] = new Cell(cellState, j);
            }
        }


        JsonNode gameInfo = rootNode.get(1);
        int nextMoveBoard = gameInfo.get("nextMoveBoard").asInt();
        int nextMoveCell = gameInfo.get("nextMoveCell").asInt();

        // Create Board instances
        Board[] boards = new Board[9];
        for (int i = 0; i < 9; i++) {
            int startIndex = i * 9;
            Cell[] boardCells = new Cell[9];
            System.arraycopy(cells, startIndex, boardCells, 0, 9);
            boards[i] = new Board(boardCells);
        }

        // Create Game instance with boards and next move information
        return new Game(boards, nextMoveBoard, nextMoveCell);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @CrossOrigin(origins = "http://localhost:3000")
    public String processMove(@RequestBody String requestBody) throws IOException {
        // Create Game instance with boards and next move information
        Game game = this.parseGame(requestBody);
        game.makeMove(game.getNextMoveBoard(), game.getNextMoveCell(), "X");
        AI ai = new AI(game);
        int nextMove = ai.chooseNextMove(game.getNextMoveCell());
        game.makeMove(game.getNextMoveCell(), nextMove, "O");
        String result = game.generateJSON(nextMove).toString();
        // String result = "Hello World";

        // Return the result
        return result;
    }
}

package me.javaproject.controller;

import me.javaproject.classes.AI;
import me.javaproject.classes.Board;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

@RestController
@RequestMapping("/tictactoe")
public class TicTacToeController {

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public int logic(@RequestBody String requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode;
        JsonNode jsonNode;
        try {
            // get first array in requestBody
            arrayNode = (ArrayNode) objectMapper.readTree(requestBody).get(0);  
            jsonNode = objectMapper.readTree(requestBody);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
        Board[] boards = new Board[9];
        // foreach arrayNode, create boards
        for (int i = 0; i < arrayNode.size(); i++) {
            boards[i] = new Board((JsonNode)arrayNode.get(i));
        }

        JsonNode nextMoveBoard = jsonNode.get(1).get("nextMoveBoard");
        int nextMoveCell = jsonNode.get(1).get("nextMoveCell").asInt();
        Board nextBoard = boards[0];
        nextBoard.isValidMove(5);
        AI ai = new AI(nextBoard);
        int nextMove = ai.pickNextMove();
        return nextMove;
    }
}

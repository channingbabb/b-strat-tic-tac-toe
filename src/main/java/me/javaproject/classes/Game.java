package me.javaproject.classes;

public class Game {
    Board[] boards;
    int nextMoveBoard;
    int nextMoveCell;
    public Game(Board[] boards, int nextMoveBoard, int nextMoveCell) {
        this.boards = boards;
        this.nextMoveBoard = nextMoveBoard;
        this.nextMoveCell = nextMoveCell;
    }
}

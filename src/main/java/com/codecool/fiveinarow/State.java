package com.codecool.fiveinarow;

import java.util.ArrayList;
import java.util.List;

import static com.codecool.fiveinarow.Game.boardSize;


public class State {
    private Game board;
    private int player;
    private int visitCount;
    protected double winScore;

    public State() {
        board = new Game(boardSize);
    }

    public State(State state) {
        this.board = new Game(state.getBoard());
        this.player = state.getPlayer();
        this.visitCount = state.getVisitCount();
        this.winScore = state.getWinScore();
    }

    public State(Game board) {
        this.board = new Game(board);
    }

    Game getBoard() {
        return board;
    }

    void setBoard(Game board) {
        this.board = board;
    }

    int getPlayer() {
        return player;
    }

    void setPlayer(int player) {
        this.player = player;
    }

    int getOpponent() {
        return 3 - player;
    }

    public int getVisitCount() {
        return visitCount;
    }

    double getWinScore() {
        return winScore;
    }

    void setWinScore(double winScore) {
        this.winScore = winScore;
    }

    public List<State> getAllPossibleStates() {
        List<State> possibleStates = new ArrayList<>();
        List<Position> availablePositions = this.board.getEmptyPositions();
        availablePositions.forEach(p -> {
            State newState = new State(this.board);
            newState.setPlayer(3 - this.player);
            newState.getBoard().getMove(newState.getPlayer(), p);
            possibleStates.add(newState);
        });
        return possibleStates;
    }

    void incrementVisit() {
        this.visitCount++;
    }

    void addScore(double score) {
        if (this.winScore != Integer.MIN_VALUE)
            this.winScore += score;
    }

    void randomPlay() {
        List<Position> availablePositions = this.board.getEmptyPositions();
        int totalPossibilities = availablePositions.size();
        int selectRandom = (int) (Math.random() * totalPossibilities);
        this.board.getMove(this.player, availablePositions.get(selectRandom));
    }

    void togglePlayer() {
        this.player = 3 - this.player;
    }
}
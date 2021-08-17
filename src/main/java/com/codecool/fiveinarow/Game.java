package com.codecool.fiveinarow;

import java.util.ArrayList;
import java.util.List;

public class Game {

    int[][] board;
    int totalMoves;


    public static int boardSize;

    public static final int IN_PROGRESS = -1;
    public static final int DRAW = 0;
    public static final int P1 = 1;
    public static final int P2 = 2;

    public Game() {
        board = new int[Game.boardSize][Game.boardSize];
    }

    public Game(int boardSize) {
        board = new int[boardSize][boardSize];
    }

    public Game(int[][] board) {
        this.board = board;
    }

    public Game(int[][] board, int totalMoves) {
        this.board = board;
        this.totalMoves = totalMoves;
    }

    public Game(Game board) {
        int boardLength = board.getBoard().length;
        this.board = new int[boardLength][boardLength];
        int[][] boarD = board.getBoard();
        int n = boarD.length;
        for (int i = 0; i < n; i++) {
            int m = boarD[i].length;
            System.arraycopy(boarD[i], 0, this.board[i], 0, m);
        }
    }

    public void getMove(int player, Position p) {
        this.totalMoves++;
        board[p.getX()][p.getY()] = player;
    }

    public int[][] getBoard() {
        return board;
    }

    public int hasWon() {
        int boardSize = board.length;
        int maxIndex = boardSize - 1;
        int[] diag1 = new int[boardSize];
        int[] diag2 = new int[boardSize];

        for (int i = 0; i < boardSize; i++) {
            int[] row = board[i];
            int[] col = new int[boardSize];
            for (int j = 0; j < boardSize; j++) {
                col[j] = board[j][i];
            }

            int checkRowForWin = checkForWin(row);
            if(checkRowForWin!=0)
                return checkRowForWin;

            int checkColForWin = checkForWin(col);
            if(checkColForWin!=0)
                return checkColForWin;

            diag1[i] = board[i][i];
            diag2[i] = board[maxIndex - i][i];
        }

        int checkDia1gForWin = checkForWin(diag1);
        if(checkDia1gForWin!=0)
            return checkDia1gForWin;

        int checkDiag2ForWin = checkForWin(diag2);
        if(checkDiag2ForWin!=0)
            return checkDiag2ForWin;

        if (getEmptyPositions().size() > 0)
            return IN_PROGRESS;
        else
            return DRAW;
    }

    private int checkForWin(int[] row) {
        boolean isEqual = true;
        int previous = row[0];
        for (int j : row) {
            if (previous != j) {
                isEqual = false;
                break;
            }
            previous = j;
        }
        if(isEqual)
            return previous;
        else
            return 0;
    }

    public void printBoard() {
        int size = this.board.length;
        for (int[] ints : this.board) {
            for (int j = 0; j < size; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    public List<Position> getEmptyPositions() {
        int size = this.board.length;
        List<Position> emptyPositions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0)
                    emptyPositions.add(new Position(i, j));
            }
        }
        return emptyPositions;
    }

    public void printResult() {
        switch (this.hasWon()) {
            case P1:
                System.out.println("Player 1 wins");
                break;
            case P2:
                System.out.println("Player 2 wins");
                break;
            case DRAW:
                System.out.println("Game Draw");
                break;
            case IN_PROGRESS:
                System.out.println("Game In Progress");
                break;
        }
    }


}

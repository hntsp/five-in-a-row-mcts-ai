package com.codecool.fiveinarow;

import java.util.Scanner;



public class FiveInARow {

    public static void main(String[] args) {

        play(10);
    }

    public static void play(double howMany) {
        Scanner input = new Scanner(System.in);
        System.out.println("Press ENTER to initiate math or type 'quit' THEN press ENTER to exit");
        while (!(input.nextLine()).toLowerCase().equals("quit")){


                    Game board = new Game((int) howMany);
                    System.out.println("Difficulty (For AI1): 1 - 3 ");
                    int howDiff1 = input.nextInt();
                    MonteCarloTreeSearch mcts1 = new MonteCarloTreeSearch();
                    mcts1.setLevel(howDiff1);
                    System.out.println("Difficulty (For AI2): 1 - 3 ");
                    int howDiff2 = input.nextInt();
                    MonteCarloTreeSearch mcts3 = new MonteCarloTreeSearch();
                    mcts3.setLevel(howDiff2);

                    int player = Game.P1;
                    int totalMoves = 10 * 10;
                    for (int i = 0; i < totalMoves; i++) {
                        if (player == Game.P1)
                            board = mcts3.findNextMove(board, player);

                        else
                            board = mcts1.findNextMove(board, player);
                        System.out.println("\n");
                        board.printBoard();

                        if (board.hasWon() != -1) {
                            break;
                        }
                        player = 3 - player;
                    }
                    System.out.println("\n");
                    board.printBoard();
                    board.printResult();
                    new Game((int) howMany);


            }
        }
    }

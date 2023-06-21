package com.game.xo;

import java.util.Scanner;

public class Runner {
    private int gameModeChoice;
    private int boardChoice;

    public void Application() {

        Scanner scanner = new Scanner(System.in);

        //choosing single-player or multi-player
        System.out.println("Welcome in Tic Tac Toe Game, \n" +
                "Press '1' if you want to play against computer. \n" +
                "Press '2' if you want to play with other player.");

        while (true) {
            try {
                gameModeChoice = Integer.parseInt(scanner.nextLine());
                if (gameModeChoice == 1 || gameModeChoice == 2) {
                    break;
                } else {
                    System.out.println("Enter correct number 1 or 2");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter correct number 1 or 2");
            }
        }

        //choosing board size
        System.out.println("\nNow choose on witch board you want to play: \n" +
                "Press '1' if you want standard 3x3 board. \n" +
                "Press '2' if you want 10x10 board with 5 chars in a row to win.");

        while (true) {
            try {
                boardChoice = Integer.parseInt(scanner.nextLine());
                if (boardChoice == 1 || boardChoice == 2) {
                    break;
                } else {
                    System.out.println("Enter correct number 1 or 2");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter correct number 1 or 2");
            }
        }
        System.out.println();

        switch (gameModeChoice) {
            case 1 -> {
                if (boardChoice == 1) {
                    Board board = new Board(3);
                    board.playAgainstComputer();
                } else {
                    Board board = new Board(10);
                    board.playAgainstComputer();
                }
            }
            case 2 -> {
                if (boardChoice == 1) {
                    Board board = new Board(3);
                    board.playAgainstOtherPlayer();
                } else {
                    Board board = new Board(10);
                    board.playAgainstOtherPlayer();
                }
            }
        }
    }
}

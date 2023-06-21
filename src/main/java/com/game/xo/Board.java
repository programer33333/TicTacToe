package com.game.xo;

import java.util.Random;
import java.util.Scanner;

public class Board {
    private char[][] board;
    private char currentPlayer;
    private int boardSize;
    private Random random;

    public Board(int size) {
        boardSize = size;
        board = new char[boardSize][boardSize];
        initializeBoard();
        random = new Random();
    }

    private void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void playAgainstOtherPlayer() {
        boolean gameFinished = false;
        currentPlayer = 'X';

        while (!gameFinished) {
            displayBoard();
            int[] move = getPlayerMove(2);
            int row = move[0];
            int col = move[1];

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;

                if (checkWin()) {
                    gameFinished = true;
                    System.out.println("Player " + currentPlayer + " wins!");
                } else if (isBoardFull()) {
                    gameFinished = true;
                    System.out.println("It's a tie!");
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Wrong answer");
            }
        }
        displayBoard();
    }

    public void playAgainstComputer() {
        boolean gameOver = false;
        currentPlayer = 'X';

        while (!gameOver) {
            displayBoard();
            int[] move = (currentPlayer == 'X') ? getPlayerMove(1) : getComputerMove();
            int row = move[0];
            int col = move[1];

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;

                if (checkWin()) {
                    if (currentPlayer == 'X') {
                        System.out.println("You won!");
                    } else {
                        System.out.println("Computer won!");
                    }
                    gameOver = true;
                } else if (isBoardFull()) {
                    System.out.println("It's a tie!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Wrong answer, try again.");
            }
        }
        displayBoard();
    }

    private void displayBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int[] getComputerMove() {
        Random random = new Random();
        int[] move = new int[2];

        System.out.println("Computer move:");
        move[0] = random.nextInt(boardSize);
        move[1] = random.nextInt(boardSize);

        return move;
    }

    private int[] getPlayerMove(int playersQuantity) {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2];
        while (true) {
            try {
                if (playersQuantity == 2) {
                    System.out.println("Player " + currentPlayer + ", enter row (1-" + (boardSize) + "): ");
                    move[0] = Integer.parseInt(scanner.nextLine()) - 1;

                    System.out.println("Player " + currentPlayer + ", enter column (1-" + (boardSize) + "): ");
                    move[1] = Integer.parseInt(scanner.nextLine()) - 1;
                    break;
                } else {
                    System.out.println("Enter row (1-" + (boardSize) + "):");
                    move[0] = Integer.parseInt(scanner.nextLine()) - 1;

                    System.out.println("Enter column (1-" + (boardSize) + "): ");
                    move[1] = Integer.parseInt(scanner.nextLine()) - 1;
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Wrong answer");
            }
        }
        return move;
    }

    private boolean isValidMove(int row, int col) {
        return (row >= 0 && row < boardSize && col >= 0 && col < boardSize && board[row][col] == '-');
    }

    private boolean checkWin() {
        // Checking rows
        for (int i = 0; i < boardSize; i++) {
            if (board[i][0] != '-' && checkRow(i)) {
                return true;
            }
        }

        // Checking columns
        for (int i = 0; i < boardSize; i++) {
            if (board[0][i] != '-' && checkColumn(i)) {
                return true;
            }
        }

        // Checking diagonals
        if (board[0][0] != '-' && checkDiagonal(0, 0, 1, 1)) {
            return true;
        }

        if (board[0][boardSize - 1] != '-' && checkDiagonal(0, boardSize - 1, 1, -1)) {
            return true;
        }

        return false;
    }

    private boolean checkRow(int row) {
        for (int i = 1; i < boardSize; i++) {
            if (board[row][i] != board[row][0]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int col) {
        for (int i = 1; i < boardSize; i++) {
            if (board[i][col] != board[0][col]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(int startRow, int startCol, int rowStep, int colStep) {
        char firstCell = board[startRow][startCol];
        int row = startRow + rowStep;
        int col = startCol + colStep;

        while (row < boardSize && row >= 0 && col < boardSize && col >= 0) {
            if (board[row][col] != firstCell) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }

        return true;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}



package com.xo;

import java.util.Random;
import java.util.Scanner;

public class Board {
    public char[][] board;
    private char currentPlayer;
    private int size;

    public Board(int size) {
        this.size = size;
        board = new char[this.size][this.size];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
                System.out.println("Wrong answer, try again.");
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

    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[] getComputerMove() {
        Random random = new Random();
        int[] move = new int[2];

        System.out.println("Computer move:");
        move[0] = random.nextInt(size);
        move[1] = random.nextInt(size);

        return move;
    }

    public int[] getPlayerMove(int playersQuantity) {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2];
        while (true) {
            try {
                if (playersQuantity == 2) {
                    System.out.println("Player " + currentPlayer + ", enter row (1-" + (size) + "): ");
                    move[0] = Integer.parseInt(scanner.nextLine()) - 1;

                    System.out.println("Player " + currentPlayer + ", enter column (1-" + (size) + "): ");
                    move[1] = Integer.parseInt(scanner.nextLine()) - 1;
                    break;
                } else {
                    System.out.println("Enter row (1-" + (size) + "):");
                    move[0] = Integer.parseInt(scanner.nextLine()) - 1;

                    System.out.println("Enter column (1-" + (size) + "): ");
                    move[1] = Integer.parseInt(scanner.nextLine()) - 1;
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Wrong answer");
            }
        }
        return move;
    }

    public boolean isValidMove(int row, int col) {
        return (row >= 0 && row < size && col >= 0 && col < size && board[row][col] == '-');

    }

    public boolean checkWin() {
        // Checking rows and columns for both 3x3 and 10x10 boards
        for (int i = 0; i < size; i++) {
            if (board[i][0] != '-' && checkRow(i) ||
                    board[0][i] != '-' && checkColumn(i)) {
                return true;
            }
        }

        // Checking diagonals for 3x3 board
        if (board[0][0] != '-' && checkDiagonal(0, 0, 1, 1) ||
                board[0][size - 1] != '-' && checkDiagonal(0, size - 1, 1, -1)) {
            return true;
        }

        // Checking for 5 points on a 10x10 board
        if (size == 10) {
            int maxPoints = 5;
            if (checkPoints(maxPoints)) {
                return true;
            }
        }

        return false;
    }

    public boolean checkRow(int row) {
        for (int i = 1; i < size; i++) {
            if (board[row][i] != board[row][0]) {
                return false;
            }
        }
        return true;
    }

    public boolean checkColumn(int col) {
        for (int i = 1; i < size; i++) {
            if (board[i][col] != board[0][col]) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDiagonal(int startRow, int startCol, int rowStep, int colStep) {
        char firstCell = board[startRow][startCol];
        int row = startRow + rowStep;
        int col = startCol + colStep;

        while (row < size && row >= 0 && col < size && col >= 0) {
            if (board[row][col] != firstCell) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }

        return true;
    }

    public boolean checkPoints(int maxPoints) {
        // Checking rows for 5 points
        for (int i = 0; i < size; i++) {
            int count = 1;
            for (int j = 1; j < size; j++) {
                if (board[i][j] == board[i][j - 1] && board[i][j] != '-') {
                    count++;
                    if (count == maxPoints) {
                        return true;
                    }
                } else {
                    count = 1;
                }
            }
        }

        // Checking columns for 5 points
        for (int i = 0; i < size; i++) {
            int count = 1;
            for (int j = 1; j < size; j++) {
                if (board[j][i] == board[j - 1][i] && board[j][i] != '-') {
                    count++;
                    if (count == maxPoints) {
                        return true;
                    }
                } else {
                    count = 1;
                }
            }
        }

        // Checking diagonals for 5 points
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (checkDiagonal(i, j, 1, 1, maxPoints) ||
                        checkDiagonal(i, j, 1, -1, maxPoints) ||
                        checkDiagonal(i, j, 1, 0, maxPoints) ||
                        checkDiagonal(i, j, 0, 1, maxPoints)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkDiagonal(int startRow, int startCol, int rowStep, int colStep, int maxPoints) {
        char firstCell = board[startRow][startCol];
        int row = startRow + rowStep;
        int col = startCol + colStep;
        int count = 1;

        while (row < size && row >= 0 && col < size && col >= 0) {
            if (board[row][col] == firstCell && board[row][col] != '-') {
                count++;
                if (count == maxPoints) {
                    return true;
                }
            } else {
                count = 1;
            }
            row += rowStep;
            col += colStep;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}



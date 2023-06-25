package com.xo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTestSuite {

    @Test
    public void testCheckWinXRow() {

        //Given
        Board board = new Board(3);
        board.initializeBoard();

        //When
        board.board[0][0] = 'X';
        board.board[0][1] = 'X';
        board.board[0][2] = 'X';

        //Then
        Assertions.assertTrue(board.checkWin());
    }

    @Test
    public void testCheckWinXColumn() {

        //Given
        Board board = new Board(3);
        board.initializeBoard();

        //When
        board.board[0][1] = 'X';
        board.board[1][1] = 'X';
        board.board[2][1] = 'X';

        //Then
        Assertions.assertTrue(board.checkWin());
    }

    @Test
    public void testCheckWinXDiagonal() {

        //Given
        Board board = new Board(3);
        board.initializeBoard();

        //When
        board.board[0][0] = 'X';
        board.board[1][1] = 'X';
        board.board[2][2] = 'X';

        //Then
        Assertions.assertTrue(board.checkWin());
    }

    @Test
    public void testCheckWinORow() {

        //Give
        Board board = new Board(3);
        board.initializeBoard();

        //When
        board.board[0][0] = 'O';
        board.board[0][1] = 'O';
        board.board[0][2] = 'O';

        //Then
        Assertions.assertTrue(board.checkWin());
    }

    @Test
    public void testCheckWinOColumn() {

        //Given
        Board board = new Board(3);
        board.initializeBoard();

        //When
        board.board[0][1] = 'O';
        board.board[1][1] = 'O';
        board.board[2][1] = 'O';

        //Then
        Assertions.assertTrue(board.checkWin());
    }

    @Test
    public void testCheckWinODiagonal() {

        //Given
        Board board = new Board(3);
        board.initializeBoard();

        //When
        board.board[0][0] = 'O';
        board.board[1][1] = 'O';
        board.board[2][2] = 'O';

        //Then
        Assertions.assertTrue(board.checkWin());
    }

    @Test
    public void testIsBoardFull() {

        //Given
        Board board = new Board(3);
        board.initializeBoard();

        //When
        board.board[0][0] = 'X';
        board.board[0][1] = 'O';
        board.board[0][2] = 'X';
        board.board[1][0] = 'X';
        board.board[1][1] = 'O';
        board.board[1][2] = 'X';
        board.board[2][0] = 'O';
        board.board[2][1] = 'X';
        board.board[2][2] = 'O';

        //Then
        Assertions.assertTrue(board.isBoardFull());
    }
}




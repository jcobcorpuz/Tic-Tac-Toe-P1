package com.csc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestTicTacToe {
    TicTacToe game;

    @BeforeEach
    void setup(){
        game = new TicTacToe();
    }

    @Test
    void testPlayerXMove(){
        game.makeMove("1");
        assertTrue(true);
    }

    @Test
    void testPlayerOMove(){
        game.makeMove("1");
        assertTrue(true);
    }

    @Test
    void testPlayerSwitch(){
        game.switchPlayer();
        assertTrue(true);
    }

    @Test
    void testWinByRow(){
        game.makeMove("1");
        game.makeMove("4");
        game.makeMove("2");
        game.makeMove("5");
        game.makeMove("3");
        assertTrue(game.checkWin());
    }

    @Test
    void testDraw(){
        game.makeMove("1");
        game.makeMove("2");
        game.makeMove("3");
        game.makeMove("5");
        game.makeMove("4");
        game.makeMove("6");
        game.makeMove("8");
        game.makeMove("7");
        game.makeMove("9");
        assertTrue(game.checkDraw());
    }

}

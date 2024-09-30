package com.csc;
import java.util.Scanner;

public class TicTacToe {
    private static final char[][] board = { {' ', ' ',' '}, {' ', ' ',' '}, {' ', ' ',' '} };
    private static char currentPlayer = 'X';

    private static void printBoard(){
        System.out.println();
        for(int i = 0; i < 3; i++){
            System.out.println(board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if(i < 2){
                System.out.println("---------");
            }
        }
        System.out.println();
    }

    public boolean isNumber(String input){
        for(int i = 0; i < input.length(); i++){
            if(!Character.isDigit(input.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public boolean isValidMove(String input){
        if(input.length() != 1){
            return false;
        }
        int move;
        if(!isNumber(input)){
            return false;
        }
        move = Integer.parseInt(input);
        if (move < 1 || move > 9){
            return false;
        }
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;

        return board[row][col] == ' ';
    }

    public void makeMove(String input){
        int move = Integer.parseInt(input);
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        board[row][col] = currentPlayer;
    }

    public void switchPlayer(){
        if(currentPlayer == 'X'){
            currentPlayer = 'O';
        }
        else{
            currentPlayer = 'X';
        }
    }

    public boolean checkWin(){
        for(int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return true;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return true;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' '){
                return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board [2][0] && board[0][2] != ' '){
                return true;
        }
            return false;
    }

    public boolean checkDraw(){
        return board[0][0] != ' ' && board[0][1] != ' ' && board[0][2] != ' ' &&
                board[1][0] != ' ' && board[1][1] != ' ' && board[1][2] != ' ' &&
                board[2][0] != ' ' && board[2][1] != ' ' && board[2][2] != ' ';
    }

    public void runGame(){
        Scanner scanner = new Scanner(System.in);
        boolean gameEnd = false;
        while (!gameEnd){
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (1-9)");
            String input = scanner.nextLine();

            if(!isValidMove(input)){
                System.out.println("Invalid move, please re-enter your move (1-9)");
                continue;
            }

            makeMove(input);
            gameEnd = checkWin() || checkDraw();
            if (!gameEnd) {
                switchPlayer();
            }
        }

        printBoard();
        if(checkWin()){
            System.out.println("Player " + currentPlayer + " wins!");
        }
        else{
            System.out.println("Draw!");
        }
        scanner.close();
    }
    public static void main(String[] args){
        TicTacToe game = new TicTacToe();
        game.runGame();
    }
}

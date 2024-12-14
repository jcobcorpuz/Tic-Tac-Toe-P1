package com.csc;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final char[][] board = { {' ', ' ',' '}, {' ', ' ',' '}, {' ', ' ',' '} };
    private static char currentPlayer;
    private static boolean isComputerPlayer = false;
    private static char playerOneMark;
    private static char playerTwoMark;

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

    public void makeRandomMove(){
        Random random = new Random();
        int move;
        do{
           move = random.nextInt(9) + 1;
        }
        while (!isValidMove(String.valueOf(move)));
        makeMove(String.valueOf(move));
        System.out.println("The computer player moved in space " + move + ".");
    }

    public void switchPlayer(){
        if(currentPlayer == playerOneMark){
            currentPlayer = playerTwoMark;
        }
        else{
            currentPlayer = playerOneMark;
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

    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Welcome to Tic Tac Toe!\n");
            System.out.print("Please choose a game mode:\n");
            System.out.print("(1) Human vs. human\n");
            System.out.println("(2) Human vs. computer\n");

            String choice = scanner.nextLine();
            if(choice.equals("1")){
                isComputerPlayer = false;
                break;
            }
            else if(choice.equals("2")){
                isComputerPlayer = true;
                break;
            }
            else{
                System.out.print("That is an invalid choice. Try again.");
            }
        }
    }

    public void exitMenu(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Would you like to play again?\n");
            System.out.print("(1) Yes\n");
            System.out.print("(2) No\n");

            String choice = scanner.nextLine();
            if(choice.equals("1")){
                resetBoard();
                runGame();
                break;
            }
            else if(choice.equals("2")){
                System.out.println("Goodbye!");
                scanner.close();
                System.exit(0);
            }
            else{
                System.out.print("That is an invalid choice. Try again.");
            }
        }
    }

    public void resetBoard(){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
    }

    public void getPlayerMarks(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Player 1, choose your mark: ");
            String input = scanner.nextLine();
            if (input.length() == 1 && !Character.isWhitespace(input.charAt(0))){
                playerOneMark = input.charAt(0);
                break;
            }
            else{
                System.out.println("Invalid mark, please try again.");
            }
        }
        while(true){
            System.out.println("Player 2, choose your mark: ");
            String input = scanner.nextLine();
            if (input.length() == 1 && !Character.isWhitespace(input.charAt(0)) && input.charAt(0) != playerOneMark){
                playerTwoMark = input.charAt(0);
                break;
            }
            else{
                System.out.println("Invalid mark, please try again.");
            }
        }
        currentPlayer = playerOneMark;
    }

    public void runGame(){
        mainMenu();
        getPlayerMarks();

        Scanner scanner = new Scanner(System.in);
        boolean gameEnd = false;
        while (!gameEnd){
            printBoard();
            if (isComputerPlayer && currentPlayer == 'O'){
                makeRandomMove();
            }
            else{
                System.out.println("Player " + currentPlayer + ", enter your move (1-9)");
                String input = scanner.nextLine();

                if(!isValidMove(input)){
                    System.out.println("Invalid move, please re-enter your move (1-9)");
                    continue;
                }
                makeMove(input);
            }

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
        exitMenu();
    }
    public static void main(String[] args){
        TicTacToe game = new TicTacToe();
        game.runGame();
    }
}

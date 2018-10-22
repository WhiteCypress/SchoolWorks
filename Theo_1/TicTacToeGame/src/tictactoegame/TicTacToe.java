/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoegame;

import java.util.Scanner;

/**
 *
 * @author cstuser
 */

public class TicTacToe {
    String Player1Name;
    String Player2Name;
    char[][] board = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
    Scanner kb = new Scanner(System.in);
    boolean isNextMovePlayer1 = true;
    
    Scanner scanner = new Scanner(System.in);
    
    public TicTacToe() {
        initializeGame();
    }
    
    public void displayBoard()
    {
        System.out.println();
        
        for (int i=0; i<3; ++i)
        {
            for (int j=0; j<3; ++j)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("\n");
    }
    
    public void initializeGame()
    {
        System.out.print("Enter name for player 1: ");
        Player1Name = kb.next();
        System.out.print("Enter name for player 2: ");
        Player2Name = kb.next();              
    }
    
    public boolean isGameTied()
    {
        for (int i=0; i<3; ++i)
        {
            for (int j=0; j<3; ++j)
            {
                if(board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isGameFinished()
    {
        // Test diagonals
        if (board[1][1] != '-')
        {
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
                return true;

            if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
                return true;
        }
            
        // Test rows
        for (int i=0; i<3; ++i)
        {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return true;
        }
        
        // Test columns
        for (int j=0; j<3; ++j)
        {
            if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
                return true;
        }
        
        return false;
    }
    
    public void playTurn()
    {
        // Initialize data for this turn
        // - Get player name
        // - Get player symbol
        // - Set next turn to other player
        String playerName;
        char playerSymbol;
        
        if (isNextMovePlayer1)
        {
            playerName = Player1Name;
            playerSymbol = 'X';
        }
        else
        {
            playerName = Player2Name;
            playerSymbol = 'O';
        }
        
        isNextMovePlayer1 = !isNextMovePlayer1;
         
        
        // Interactive prompt to ask player for coordinate to play
        System.out.println(playerName + ", enter the coordinates of your next move (eg: 1 2 is middle-right): ");

        int i = kb.nextInt();
        int j = kb.nextInt();

        // Validate that the requested coordinate is valid
        while(i < 0 || i > 2 || j < 0 || j > 2 || board[i][j] != '-')
        {
            System.out.println("Invalid coordinate, enter the coordinates of your next move (eg: 1 2 is middle-right): ");

            i = kb.nextInt();
            j = kb.nextInt();                
        }
        
        setBoard(playerSymbol, i, j);
    }
    
    public void setBoard(char playerSymbol, int row, int column)
    {
        // Assumptions on the coordinate
        assert(row >= 0 && row < 3 && column >= 0 && column < 3);
        
        // Assumptions on the player symbol
        assert(playerSymbol == 'X' || playerSymbol == 'O');
        
        board[row][column] = playerSymbol;        
    }
}

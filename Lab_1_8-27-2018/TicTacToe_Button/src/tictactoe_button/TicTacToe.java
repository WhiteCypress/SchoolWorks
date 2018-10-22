/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_button;

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
        Player1Name = "Player_1";
        Player2Name = "Player_2";
        board[0][0] = board[0][1] = board[0][2] = board[1][0] = board[1][1] = board[1][2] = board[2][0] = board[2][1] = board[2][2];
    }
    
    public TicTacToe(String n2) {
        System.out.print("Enter name for player 1: ");
        Player1Name = kb.next();
        
        Player2Name = n2;
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
    
    public void setBoard(char playerSymbol, int row, int column)
    {
        // Assumptions on the coordinate
        assert(row >= 0 && row < 3 && column >= 0 && column < 3);
        
        // Assumptions on the player symbol
        assert(playerSymbol == 'X' || playerSymbol == 'O');
        
        board[row][column] = playerSymbol;        
    }
}

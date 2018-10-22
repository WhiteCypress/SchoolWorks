/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author cstuser
 */
public class gameWindow extends JFrame implements KeyListener{        //-1 for bomb, -2 for first click, -3 for flag

    private JButton[][] buttons;
    private int[][] board;
    public static boolean running;
    private mainMenu instance;
    private int clickCount = 0;
    private boolean ctrHold = false;
    public static boolean debug = false;

    public gameWindow(mainMenu main) {
        super("MINE SWEEPER!");
        instance = main;

        setSize(900, 900);

        Container container = getContentPane();
        container.setLayout(new GridLayout(8, 8));

        buttons = new JButton[8][8];

        board = new int[8][8];
        check_ctrl();

        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
                final int A = a;
                final int B = b;
                buttons[a][b] = new JButton();
                buttons[a][b].addKeyListener(this);
                buttons[a][b].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (clickCount == 0 && !ctrHold) {
                            board[A][B] = -2;
                            plantMine();
                            debugMode(debug);

                            for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                    checkMine(i, j);
                                }
                            }     
                            /*if(board[A][B] == 0) {
                                clickAround(A, B);
                            }*/
                        }
                        if(ctrHold){
                            if(buttons[A][B].getText().equals("FLAG") && board[A][B] == -1 && debug == true){
                                buttons[A][B].setText("mine");
                            }else if(buttons[A][B].getText().equals("FLAG")){
                                buttons[A][B].setText("");
                            }else {
                                buttons[A][B].setText("FLAG");
                            }
                        }else if(board[A][B] != 0 && board[A][B] != -2 && board[A][B] != -1){
                            buttons[A][B].setText(Integer.toString(board[A][B]));
                        }else if(board[A][B] == -1){
                            for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                    buttons[i][j].setEnabled(false);
                                    if(board[i][j] == -1){
                                        buttons[i][j].setText("MINE");
                                    } 
                                }
                            }
                            mainMenu.lose();
                        }
                            
                        if(!ctrHold){
                            buttons[A][B].setEnabled(false);
                            clickCount++;
                        }
                        
                        if(clickCount == 54){
                             for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                    buttons[i][j].setEnabled(false);
                                    if(board[i][j] == -1){
                                        buttons[i][j].setText("MINE");
                                    } 
                                }
                            }
                            mainMenu.win();
                        }
                        
                        if(board[A][B] == 0 && !ctrHold){
                            clickAround(A, B);
                        }
                    }
                });
                container.add(buttons[a][b]);
            }
        }



        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainMenu.startButton.setEnabled(true);
                mainMenu.progressLabel.setText("Press NEW GAME to start");
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void plantMine() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] != -2)
                    board[i][j] = 0;
            }
        }

        for (int i = 0; i < 10; i++) {
            int x,y;
            do {
                x = (int) (Math.random() * 8);
                y = (int) (Math.random() * 8);
            } while (board[x][y] == -1 || board[x][y] == -2);

            board[x][y] = -1;
        }
    }

    public void debugMode(boolean Debug) {
        //shows where is the bomb
        if (Debug == true) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == -1) {
                        buttons[i][j].setText("mine");
                    }
                }
            }
        }
    }

    public void checkMine(int x, int y) {
        int counter = 0;

        if (board[x][y] != -1 && y - 1 >= 0 && board[x][y - 1] == -1) {
            counter++;
        }

        if (board[x][y] != -1 && x - 1 >= 0 && y - 1 >= 0 && board[x - 1][y - 1] == -1) {
            counter++;
        }

        if (board[x][y] != -1 && y - 1 >= 0 && x + 1 <= 7 && board[x + 1][y - 1] == -1) {
            counter++;
        }

        if (board[x][y] != -1 && x + 1 <= 7 && board[x + 1][y] == -1) {
            counter++;
        }

        if (board[x][y] != -1 && x - 1 >= 0 && board[x - 1][y] == -1) {
            counter++;
        }

        if (board[x][y] != -1 && y + 1 <= 7 && board[x][y + 1] == -1) {
            counter++;
        }

        if (board[x][y] != -1 && y + 1 <= 7 && x - 1 >= 0 && board[x - 1][y + 1] == -1) {
            counter++;
        }

        if (board[x][y] != -1 && x + 1 <= 7 && y + 1 <= 7 && board[x + 1][y + 1] == -1) {
            counter++;
        }

        if (counter != 0) {
            board[x][y] = counter;
        }
    }
    
    public void clickAround(int x, int y){
        if(y-1 >= 0 && buttons[x][y-1].isEnabled() == true && !buttons[x][y-1].getText().equals("FLAG"))
            buttons[x][y-1].doClick();
        if(y-1 >= 0 && x-1 >= 0 && buttons[x-1][y-1].isEnabled() == true && !buttons[x-1][y-1].getText().equals("FLAG"))
            buttons[x-1][y-1].doClick();
        if(y-1 >= 0 && x+1 <= 7 && buttons[x+1][y-1].isEnabled() == true && !buttons[x+1][y-1].getText().equals("FLAG"))
            buttons[x+1][y-1].doClick();
        if(x-1 >= 0 && buttons[x-1][y].isEnabled() == true && !buttons[x-1][y].getText().equals("FLAG"))
            buttons[x-1][y].doClick();
        if(x+1 <= 7 && buttons[x+1][y].isEnabled() == true && !buttons[x+1][y].getText().equals("FLAG"))
            buttons[x+1][y].doClick();
        if(y+1 <= 7 && buttons[x][y+1].isEnabled() == true && !buttons[x][y+1].getText().equals("FLAG"))
            buttons[x][y+1].doClick();
        if(y+1 <= 7 && x-1 >= 0 && buttons[x-1][y+1].isEnabled() == true && !buttons[x-1][y+1].getText().equals("FLAG"))
            buttons[x-1][y+1].doClick();
        if(y+1 <= 7 && x+1 <= 7 && buttons[x+1][y+1].isEnabled() == true && !buttons[x+1][y+1].getText().equals("FLAG"))
            buttons[x+1][y+1].doClick();
    }
    
    public void check_ctrl() {
        setFocusable(true);
        
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        ctrHold = ke.isControlDown();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        ctrHold = ke.isControlDown();
    }
}

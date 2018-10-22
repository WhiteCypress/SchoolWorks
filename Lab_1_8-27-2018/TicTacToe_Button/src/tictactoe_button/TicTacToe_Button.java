/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_button;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author cstuser
 */
public class TicTacToe_Button extends JFrame {

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    static TicTacToe game;
    static TicTacToe_Button tic;

    static boolean finish;
    static boolean tied;

    public static void main(String[] args) {
        tic = new TicTacToe_Button("Tic", 500, 500);
        tic.setVisible(true);

        game = new TicTacToe();

    }

    public TicTacToe_Button(String title, int width, int height) {
        super(title);
        setLocationRelativeTo(null);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();
        b5 = new JButton();
        b6 = new JButton();
        b7 = new JButton();
        b8 = new JButton();
        b9 = new JButton();

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (game.isNextMovePlayer1) {

                    drawX(0, 0, b1);

                } else {

                    drawO(0, 0, b1);

                }

            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (game.isNextMovePlayer1) {

                    drawX(0, 1, b2);

                } else {

                    drawO(0, 1, b2);

                }

            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (game.isNextMovePlayer1) {

                    drawX(0, 2, b3);

                } else {

                    drawO(0, 2, b3);

                }

            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (game.isNextMovePlayer1) {

                    drawX(1, 0, b4);

                } else {

                    drawO(1, 0, b4);

                }

            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (game.isNextMovePlayer1) {

                    drawX(1, 1, b5);

                } else {

                    drawO(1, 1, b5);

                }

            }
        });

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (game.isNextMovePlayer1) {

                    drawX(1, 2, b6);

                } else {

                    drawO(1, 2, b6);

                }

            }
        });

        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (game.isNextMovePlayer1) {

                    drawX(2, 0, b7);

                } else {

                    drawO(2, 0, b7);

                }

            }
        });

        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (game.isNextMovePlayer1) {

                    drawX(2, 1, b8);

                } else {

                    drawO(2, 1, b8);

                }

            }
        });

        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (game.isNextMovePlayer1) {

                    drawX(2, 2, b9);

                } else {

                    drawO(2, 2, b9);

                }

            }
        });

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(3, 3));

        pane.add(b1);
        pane.add(b2);
        pane.add(b3);
        pane.add(b4);
        pane.add(b5);
        pane.add(b6);
        pane.add(b7);
        pane.add(b8);
        pane.add(b9);
    }

    public void drawO(int x, int y, JButton b) {
        game.setBoard('O', x, y);
        b.setEnabled(false);
        b.setText("O");

        if (game.isGameFinished()) {
            if (!game.isNextMovePlayer1) {
                JOptionPane.showMessageDialog(null, game.Player2Name + " has won the game!");
                restartGame();
            }
        }
        if (game.isGameTied()) {
            JOptionPane.showMessageDialog(null, "TIED!!!");
        }

        game.isNextMovePlayer1 = !game.isNextMovePlayer1;
    }

    public void drawX(int x, int y, JButton b) {
        game.setBoard('X', x, y);
        b.setEnabled(false);
        b.setText("X");

        if (game.isGameFinished()) {
            if (game.isNextMovePlayer1) {
                JOptionPane.showMessageDialog(null, game.Player1Name + " has won the game!");
                restartGame();
            }
            if (game.isGameTied()) {
                JOptionPane.showMessageDialog(null, "TIED!!!");
            }
        }

        game.isNextMovePlayer1 = !game.isNextMovePlayer1;
    }

    public void restartGame() {
        tic.dispose();
        String[] args = new String[0];
        main(args);
    }
}

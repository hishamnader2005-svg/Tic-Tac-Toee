
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int borderWidth = 800;
    int borderHeight = 850;//0 for the text

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel borderPanel = new JPanel();
    JButton [][] board = new JButton[3][3];
    String playerX = "X";
    String playerO= "O";
    String currentPlayer= playerX;

    boolean gameover= false;
    int turns = 0;



    TicTacToe (){
        frame.setVisible(true);
        frame.setSize(borderWidth, borderHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial" , Font.BOLD , 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel);
        frame.add(textPanel, BorderLayout.NORTH);

        borderPanel.setLayout(new GridLayout (3,3));
        borderPanel.setBackground(Color.DARK_GRAY);
        frame.add(borderPanel);

        for (int r = 0 ; r<3 ; r++) {
            for( int c = 0 ; c <3 ; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                borderPanel.add(tile);
                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.black);
                tile.setFont(new Font ("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);


                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameover) {
                            return;
                        }//end if
                        JButton tile =(JButton) e.getSource();

                        if (tile.getText()== "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameover) {

                                currentPlayer= currentPlayer== playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn");

                            }//end if


                        }//end if


                    }//end method
                });
            }
        }
        frame.validate(); // Ensure the layout is recalculated
    }

    void checkWinner() {
        //horizontal
        for (int r = 0 ; r<3 ; r++) {
            if (board[r][0].getText()== "") continue;

            if (board[r][0].getText()== board [r][1].getText() && board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0 ; i<3 ; i++) {
                    setWinner(board[r][i]);
                }
                gameover = true;
                return;
            }
        }

        //vertical
        for (int c = 0 ; c < 3 ; c ++) {
            if (board[0][c].getText() == "") continue;

            if (board[0][c].getText() == board [1][c].getText() && board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i<3 ; i++ ) {


                    setWinner(board[i][c]);
                }
                gameover = true;
                return;
            }
        }

        //diagonal
        if (board[0][0].getText()==board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText()!="") {

            for (int i =0; i<3;i++) {
                setWinner(board[i][i]);
            }
            gameover=true;
            return;
        }

        if (board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][2].getText()!= "") {

            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameover=true;
            return;
        }
        if (turns ==9) {
            for (int r = 0 ; r<3 ; r++) {
                for (int c = 0 ; c<3;c++) {
                    settie(board[r][c]);
                }
            }
            gameover = true;

        }
    }//function for checkwinner

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner");
    }

    void settie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("tie");
    }

}





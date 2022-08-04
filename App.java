import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.ActionListener;

public class App{

    // creating Java Swing UI elements

    private static JTextField[][] fields;
    private static JTextArea area;
    private static JTabbedPane tab;
    private static JFrame frame;
    
    public static void main(String[] args) {

        // defining main Sudoku board Array ----------

        int[][] board = new int[9][9];

        // JFRAME ------------------------------

        frame = new JFrame("Sudoku Solver");
        JPanel root = new JPanel();
        frame.add(root);

        // JTab -> Grid tab -------------------------

        tab = new JTabbedPane();
        root.add(tab);
        tab.setFont(tab.getFont().deriveFont(40f));

        //  Panel for Text Fields --------------------

        JPanel panel = new JPanel();
        tab.addTab("Sudoku Solver", panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.lightGray);
        JPanel grid = new JPanel();

        panel.add(grid);
        grid.setLayout(new GridLayout(9, 9));

        // Initialising textFields ---------------------

        fields = new JTextField[9][9];

        for(int row =0; row<9; row++){
            for(int column = 0; column<9; column++){
                fields[row][column] = new JTextField(2);
                fields[row][column].setFont(fields[row][column].getFont().deriveFont(50f));
                fields[row][column].setHorizontalAlignment(JTextField.CENTER);
                fields[row][column].setBackground(Color.ORANGE);
                // fields[row][column].setDocument(new Document(1));
                // fields[row][column].setText("0");
                grid.add(fields[row][column]);
            }
        }

        // creating Sudoku class object to access this class finctions -----------------

        Sudoku Sudoku = new Sudoku();

        // defining solve button to perfomr action to solve Sudoku ------------------

        JButton solveButton = new JButton("Solve");
        solveButton.setFont(solveButton.getFont().deriveFont(60f));
        root.add(solveButton);

        // adding action listner to solve button ----------------

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if (fields[i][j].getText().length() > 1)
                                throw new Exception();
                            if (fields[i][j].getText().equals(".") || fields[i][j].getText().equals(" ")
                                    || fields[i][j].getText().equals(""))
                                board[i][j] = 0;
                            else
                                board[i][j] = Integer.parseInt(fields[i][j].getText());
                        }
                    }
                    Sudoku.solve(board);
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            String s = Integer.toString(board[i][j]);
                            fields[i][j].setText(s);
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Input Sudoku not valid!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Clear button ------------------

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(clearButton.getFont().deriveFont(60f));
        root.add(clearButton);

        // adding action listner to Clear button to clear board --------------------

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int row = 0; row < 9; row++) {
                    for (int column = 0; column < 9; column++) {
                        fields[row][column].setText("");
                    }
                }
                area.setText("");
            }
        });


        

        // Setting up JFrame

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
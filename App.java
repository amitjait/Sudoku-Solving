import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import javax.swing.JFrame;
// import javax.swing.JTabbedPane;
// import javax.swing.JTextArea;
// import javax.swing.JTextField;
import java.awt.event.ActionListener;

public class App{

    private static JTextField[][] fields;
    private static JTextArea area;
    private static JTabbedPane tab;
    private static JFrame frame;
    
    public static void main(String[] args) {

        int[][] board = new int[9][9];

        frame = new JFrame("Sudoku Solver");
        JPanel root = new JPanel();
        frame.add(root);

        tab = new JTabbedPane();
        root.add(tab);
        tab.setFont(tab.getFont().deriveFont(40f));

        JPanel panel = new JPanel();
        tab.addTab("Grid", panel);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.lightGray);
        JPanel grid = new JPanel();

        panel.add(grid);
        grid.setLayout(new GridLayout(9, 9));

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

        
        // for(int row=0; row<9; row++){
        //     for(int column =0; column<9; column++){
        //         String fieldText = fields[row][column].getText();
        //         if(fieldText == ""){
        //             board[row][column] = 0;
        //         }
        //         else{
        //             board[row][column] = Integer.parseInt(fieldText);
        //         }
        //     }
        // }

        Sudoku Sudoku = new Sudoku();

        // Sudoku.solve(board);

        // Sudoku.printSudoku(board);

        JButton solveButton = new JButton("Solve");
        solveButton.setFont(solveButton.getFont().deriveFont(60f));
        root.add(solveButton);
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // int[][] board = new int[9][9];
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
                    StringBuilder b = new StringBuilder();
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

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(clearButton.getFont().deriveFont(60f));
        root.add(clearButton);
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


        

        

        // app.printSudoku(board);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
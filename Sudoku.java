

public class Sudoku{
    public static int GRID_SIZE = 9;

    public boolean solve(int[][] board){

        for(int row=0; row<GRID_SIZE; row++){
            for(int column=0; column<GRID_SIZE; column++){
                if(board[row][column] == 0){
                    for(int numToTry = 1; numToTry <= GRID_SIZE; numToTry++){
                        if(isValid(board, numToTry, row, column)){
                            board[row][column] = numToTry;

                            if(solve(board)){
                                return true;
                            }
                            else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true; 
    }

    public void printSudoku(int[][] board){
        for(int row=0; row<GRID_SIZE; row++){
            for(int column=0; column<GRID_SIZE; column++){
                System.out.print(board[row][column] +" ");
            }
            System.out.println();
        }
    }

    public static boolean isNumInRow(int[][] board, int number, int row){
        for(int i=0; i<GRID_SIZE; i++){
            if(board[row][i] ==  number){
                return true;
            }
        }
        return false;
    }

    public static boolean isNumInColumn(int[][] board, int number, int column){
        for(int i=0; i<GRID_SIZE; i++){
            if(board[i][column] ==  number){
                return true;
            }
        }
        return false;
    }

    public static boolean isNumInBox(int[][] board, int number, int row, int column){

        int boxFisrtRow = row - row%3;
        int boxFirstColumn = column - column%3;

        for(int i=boxFisrtRow; i<boxFisrtRow+3; i++){
            for(int j=boxFirstColumn; j<boxFirstColumn+3; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isValid(int[][] board, int number, int row, int column){
        return !isNumInBox(board, number, row, column) &&
         !isNumInColumn(board, number, column) &&
         !isNumInRow(board, number, row);
    }
}

class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
        System.out.print("[");
        for (int i = 0; i < 9; i++) {
            System.out.print("[");
            for (int j = 0; j < 9; j++) {
                System.out.print("\"" + board[i][j] + "\"");
                if (j != 8) System.out.print(",");
            }
            System.out.print("]");
            if (i != 8) System.out.print(",");
        }
        System.out.println("]");
    
    }
    public static boolean solve(char[][] board)
    {
        for(int row=0;row<9;row++)
        {
            for(int col= 0;col<9;col++)
            {
                if(board[row][col] == '.')
                {
                    for(char digit ='1';digit<= '9';digit++)
                    {
                        if(isValid(board, row,col,digit))
                        {
                            board[row][col] = digit;
                            if(solve(board))
                                return true;
                            board[row][col] = '.';    
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isValid(char[][] board, int row, int col,char c)
    {
        for(int i =0;i<9;i++)
        {
            if(board[row][i]==c || board[i][col]==c)
            {
                return false;
            }
            int boxRow = 3* (row/3) + i/3;
            int boxCol = 3*(col/3) + i%3;
            if(board[boxRow][boxCol]==c)
            {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
    char[][] board = generateSudokuPuzzle();
    
    System.out.println("Generated Puzzle:");
    printBoard(board);
    
    Solution solver = new Solution();
    solver.solveSudoku(board);
}

private static char[][] generateSudokuPuzzle() {
    char[][] fullBoard = {
        {'5','3','4','6','7','8','9','1','2'},
        {'6','7','2','1','9','5','3','4','8'},
        {'1','9','8','3','4','2','5','6','7'},
        {'8','5','9','7','6','1','4','2','3'},
        {'4','2','6','8','5','3','7','9','1'},
        {'7','1','3','9','2','4','8','5','6'},
        {'9','6','1','5','3','7','2','8','4'},
        {'2','8','7','4','1','9','6','3','5'},
        {'3','4','5','2','8','6','1','7','9'}
    };

    // Remove random cells to form a puzzle
    java.util.Random rand = new java.util.Random();
    int cellsToRemove = 40;  // Increase for harder puzzles

    while (cellsToRemove > 0) {
        int i = rand.nextInt(9);
        int j = rand.nextInt(9);
        if (fullBoard[i][j] != '.') {
            fullBoard[i][j] = '.';
            cellsToRemove--;
        }
    }
    return fullBoard;
}

private static void printBoard(char[][] board) {
    for (char[] row : board) {
        System.out.print("[");
        for (int i = 0; i < row.length; i++) {
            System.out.print("\"" + row[i] + "\"");
            if (i < row.length - 1) System.out.print(",");
        }
        System.out.println("],");
    }
}
}

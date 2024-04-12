package org.example;
import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {

        char [][] tictactoe =
                {{'O', 'O', 'X'},
                 {'O', 'X', 'X'},
                 {'X', 'O', 'O'}};

        // expect true if either X or O have 3 across, diagonal or down in a row
        System.out.println("Has a winner: " + tttOutcome(tictactoe));

/*        int[] array1 = {1, 3, 200, 11, 2};
        int[] array2 = {9, 33, 217, 18, 66};
        System.out.println(smallestDiff(array1, array2));    // expected output: 2 (11, 9)

        intersection(0, 0, 7, -7, 0, -5, 7, -2);

        numberSwapper(234, 232);
 */   }

    public static boolean tttOutcome (char [][] board) {
        // Problem: write a method to check if someone has one in a game of tic-tac-toe

        // My solution was first solved for 'X' then added 'O' as valid tokens on the T-T-T board


        int colCountX = 0; int rtDiagX = 0;  // variables to count tokens for each valid win: diag/row/column
        int rowCountX = 0; int lfDiagX = 0;
        int colCountO = 0; int rtDiagO = 0;
        int rowCountO = 0; int lfDiagO = 0;

        for (int i = 0; i < 3; i++) {           // two-dimensional array for loops to check every board token
            for (int j =0; j < 3; j++) {
                //System.out.println("col:"+board[j][i]+" row:"+board[i][j]);

                if (board[j][i] == 'X') {                       // checks for three tokens in each column
                    colCountX++;
                } else if (board[j][i] == 'O') {colCountO++;}

                if (board[i][j] == 'X') {                       // checks for three tokens in each row
                    rowCountX++;
                } else if (board[i][j] == 'O') {rowCountO++;}

                if ((i == j) && (board[i][j] == 'X')) {         // right diagonal board spots have equal index values
                    rtDiagX++;
                } else if ((i == j) && (board[i][j] == 'O')) { rtDiagO++;}

                if (((i+j) == 2) && (board[i][j] == 'X')) {     // left diagonal board spots have index sum == 2
                    lfDiagX++;
                } else if (((i+j) == 2) && (board[i][j] == 'O')) { lfDiagO++;}

            }
            if ((rowCountX == 3) || (colCountX == 3) || (rtDiagX == 3) || (lfDiagX == 3) ||
                    (rowCountO == 3) || (colCountO == 3) || (rtDiagO == 3) || (lfDiagO == 3)) {
                return true;  // returns true upon first 3 successive tokens found that win the game
            } else {
                rowCountX = 0; colCountX = 0; colCountO = 0; rowCountO = 0;
            }
        }
        return false;  // exhaustive search not finding 3 successive tokens on the board returns false
    }

    public static int smallestDiff (int[] arr1, int[] arr2) {
        // Problem: given two arrays, compute the pair values (one value in each array) with the smallest
        //  non-negative difference.  Return the difference.

        int curDiff; int val1=0; int val2=0;
        int minDiff = abs(arr1[1] - arr2[1]);   // initialize the minDiff with absolute value
                                                    // of diff from first two values in arrays
        for (int i = 0; i < arr1.length; i++ ) {

            for (int j = 0; j < arr2.length; j++) {

                curDiff = abs(arr1[i]-arr2[j]);
                if (curDiff < minDiff) {            // if the current diff is smaller than min diff
                    minDiff = curDiff;          //  current diff is new min diff
                    val1 = arr1[i];         // save min val1 from arr1
                    val2 = arr2[j];         // save min val2 from arr2
                }
            }
        }
        //System.out.println("min vals: " + val1 + ", " + val2 );
        return minDiff;
    }

    public static void intersection (int x1, int y1, int x2, int y2,
                                        int x3, int y3, int x4, int y4) {

        // Problem: given 2 straight line segments (represented as a start and end point),
        //  compute the point of intersection, if any.

        // Note: easy to derive using math formula search

        double t;
        double tNum;
        double tDen;
        double u;
        double uNum;
        double uDen;

        tNum = ((x1 - x3)*(y3 - y4)) - ((y1 - y3)*(x3 - x4));
        tDen = ((x1 - x2)*(y3 - y4)) - ((y1 - y2)*(x3 - x4));
        t = tNum/tDen;

        uNum = ((x1 - x2)*(y1 - y3)) - ((y1 - y2)*(x1 - x3));
        uDen = tDen;
        u =  -uNum/uDen;

        //System.out.println(t + "  " + u);

        // if 0<=t<=1 AND 0<=u<=1 then the lines intersect at a point (Px, Py)

        if (((t >= 0) && (t<=1)) && ((u>=0) && (u<=1))) {

           double px = x1 + (t * (x2 - x1));
           double py = y1 + (t * (y2 - y1));

           System.out.println("Line intersects at P("+ px + "," + py + ")");

        }  else {
            System.out.println("Lines don't intersect at a point.");
        }

    }

    public static void numberSwapper (int a, int b) {
        // Problem: write a function to swap two numbers in place (no temp variables)

        // simple solution is to use subtraction with the two values a and b

        System.out.println("Before Switch: a:" + a + "  b:" + b);
        if (a > b) {
            a = a - b;
            b = a + b;
            a = b - a;
        } else {
            b = b - a;
            a = b + a;
            b = a - b;
        }
        System.out.println("After Switch: a:" + a + "  b:" + b);
    }

} // end class
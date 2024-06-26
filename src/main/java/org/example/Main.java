package org.example;

import java.util.ArrayList;
import static java.lang.Math.abs;


public class Main {
    public static void main(String[] args) {

        //ex1: swap 2 variable values in place WITHOUT using temp variable
        numberSwapper(28, 232);

        //ex2: find intersection point of 2 lines
        intersection(0, 0, 7, -7, 0, -5, 7, -2); // expect: intersection p(3.5, -3.5)

        //ex3: given two arrays, compute the pair values (one value in each array)
        //     with the smallest non-negative difference.  Return the difference.
        int[] array1 = {1, 3, 200, 11, 2};      // input array 1
        int[] array2 = {9, 33, 217, 18, 66};    // input array 2
        System.out.println("Smallest Diff: " + smallestDiff(array1, array2));    // expected output: 2 (11, 9)


        //ex4: check if someone has won in a game of tic-tac-toe
        char [][] tictactoe =
                {{'O', 'O', 'X'},
                 {'O', 'X', 'X'},
                 {'X', 'O', 'O'}};  // input tic-tac-toe board
        // expected output: true if either X or O have 3 across, diagonal or down in a row in above 2D array
        System.out.println("Has a winner: " + tttOutcome(tictactoe));

        //ex5: convert number to English text
        System.out.println(intToEnglish(2145937268));  // expect: output english version of this integer

        //ex6: print out primes up to n
        System.out.println("Primes:" + primes(50));  // expect: primes <= 50

        //ex7: find the contiguous sequence with the largest sum in a given array
        int[] chkSeq = {-3, 7, 3, -2, 7, 5};
        System.out.println("Max Sequence Sum: " + largestSequenceSum(chkSeq));  //expect: 20 (7, 3, -2, 7, 5)

    }

    public static int largestSequenceSum (int[] seq) {
        //  Exercise 7: write a method to find the contiguous sequence with the largest sum in a given array .
        //            e.g. input: 2, -8, 3, -2, 4, -10
        //                 output: 5   (i.e.  {3, -2, 4} )
        //          Method will handle various length arrays.
        //

        int max = Integer.MIN_VALUE;    // set to MIN_VALUE integer to capture negative value sums
        int sum = 0;

        // These for loops act to capture all sequence permutations of the given array using its indices
        for (int m = 1; m < seq.length; m++) {
            int i = 0;
            for (int k = m; k < seq.length; k++) {
                sum = getSum(seq, i, k);        // get the sum of the current bound sequence from i to k
                max = sum > max ? sum : max;        // check sum of current sequence; make max if applicable
                //System.out.println(i + ":" + k + ":" + max);
                i++;
            }
        }
        return max;
    }
    public static int getSum (int[] arr, int s, int e) {
        // This method adds the values of the array sequence between sTART and eND indices
        int total = 0;
        for (int i = s; i <= e; i++) {
            total += arr[i];
        }
        return total;
    }

    public static ArrayList<Integer> primes(int n){
        // Exercise 6: write a method to return an array of prime numbers up to input int n
        //               I used an ArrayList as return type

        ArrayList<Integer> outArr = new ArrayList<>() ;
        byte[] arrP = new byte[n + 1];              // byte array initialized with '0' values

        arrP[1] = 1; arrP[2] = 1;  // by definition 1 & 2 are both Prime Numbers

        for (int k = n; k > 2; k--) {   // check all values from n -> 3
            if (((k%2) != 0) && (checkPrime(k))) {  // if n is not even AND n is Prime , set bit
                arrP[k] = 1;
            }
        }
        for (int indx = 0; indx < arrP.length; indx++) {
            if (arrP[indx] == 1) {      // if the array element is 1, then add to ArrayList
                outArr.add(indx);
            }
        }
        return outArr;  // return array of prime numbers <= n
    }

    public static boolean checkPrime(int n) {
        for (int j=(n-1); j > 2; j--) {
            if ((n%j) == 0) {       // check divisibility for n of all values n-1 to 3
                return false;
            }
        }
        return true;
    }


    public static String intToEnglish(int n) {
        // Exercise 5: given any integer, print an English text
        // e.g. input: 1234
        //     output: "One-Thousand, Two-Hundred Thirty Four"

        String englishNumberStr = "";
        String masterOutStr = "";
        String onesStr = "";
        String tensStr = "";
        String hundsStr = "";

        int blkCount = 1; int pwr = 1000;   // input number is broken up into blocks of 3; this counts those blocks
        while ((n / pwr) >= 1 ) {
            pwr = pwr * 1000;
            blkCount++;
        }

        int[] digits = new int[4];      // this array will hold the 3-digit number integers

        digits = blockDigits(n, blkCount);  // populate the array with the 3-digit number integers

        for (int j = blkCount - 1; j >= 0; j--) {   // loop to convert each 3-digit block into text

            onesStr = getOnes(digits[j]);
            tensStr = getTens(digits[j]);
            hundsStr = getHundreds(digits[j]);

            englishNumberStr = hundsStr + tensStr + onesStr;  // concatenate
            if (!englishNumberStr.isEmpty()) {          // this checks if the 3-digit is "000" and skips concatenation
                switch (j) {
                    case 1: {
                        masterOutStr = masterOutStr + englishNumberStr + "Thousand ";
                        break;
                    }
                    case 2: {
                        masterOutStr = masterOutStr + englishNumberStr + "Million ";
                        break;
                    }
                    case 3: {
                        masterOutStr = masterOutStr + englishNumberStr + "Billion ";
                        break;
                    }
                    default: {
                        masterOutStr = masterOutStr + englishNumberStr;
                    }
                }
            }
        }
        return masterOutStr;  // master output concatenated text version of the input integer
    }
    public static int[] blockDigits(int n, int blockCount) {
        // need to account for up to 4 blocks of 3-digit array values
        //  The cases below parse the given integer into 3-digit integer values and add them to the return array

        int[] digs = new int[4]; int temp;
        switch (blockCount) {
            case 1 : { digs[0] = n; break; }

            case 2 : { digs[0] = n - ((n/1000) * 1000);
                       digs[1] = n/1000; break;}

            case 3 : { digs[0] = n - ((n/1000) * 1000);
                       temp = n/1000;
                       digs[1] = temp - ((temp/1000)*1000);
                       digs[2] = n/1000000; break;}
            case 4: { digs[0] = n - ((n/1000) * 1000);
                      temp = n/1000;
                      digs[1] = temp - ((temp/1000)*1000);
                      temp = n/1000000;
                      digs[2] = temp - ((temp/1000)*1000);
                      digs[3] = n/1000000000; break;}
        }
        return digs;
    }
    public static String getOnes(int n) {
        int ones = (n - ((n / 10)*10));
        if (((n - ((n / 100)*100 )) / 10) == 1) { ones = 0;}
        switch (ones) {
            case 9:
                return "Nine ";
            case 8:
                return "Eight ";
            case 7:
                return "Seven ";
            case 6:
                return "Six ";
            case 5:
                return "Five ";
            case 4:
                return "Four ";
            case 3:
                return "Three ";
            case 2:
                return "Two ";
            case 1:
                return "One ";
            default:
                return "";
        }
    }
    public static String getTens(int n) {
        int highTens = ((n - ((n / 100)*100 )) / 10);
        switch (highTens){
            case 9:
                return "Ninety ";
            case 8:
                return "Eighty ";
            case 7:
                return "Seventy ";
            case 6:
                return "Sixty ";
            case 5:
                return "Fifty ";
            case 4:
                return "Forty ";
            case 3:
                return "Thirty ";
            case 2:
                return "Twenty ";
            case 1:
                switch (n % 10) {

                    case 9:
                        return "Nineteen ";
                    case 8:
                        return "Eighteen ";
                    case 7:
                        return "Seventeen ";
                    case 6:
                        return "Sixteen ";
                    case 5:
                        return "Fifteen ";
                    case 4:
                        return "Fourteen ";
                    case 3:
                        return "Thirteen ";
                    case 2:
                        return "Twelve ";
                    case 1:
                        return "Eleven ";
                    default:
                        return "Ten ";

                }
            default:
                return "";
        }
    }

    public static String getHundreds(int n) {
        switch (n / 100) {
            case 9:
                return "Nine-Hundred ";
            case 8:
                return "Eight-Hundred ";
            case 7:
                return "Seven-Hundred ";
            case 6:
                return "Six-Hundred ";
            case 5:
                return "Five-Hundred ";
            case 4:
                return "Four-Hundred ";
            case 3:
                return "Three-Hundred ";
            case 2:
                return "Two-Hundred ";
            case 1:
                return "One-Hundred ";
            default:
                return "";
        }
    }

    public static boolean tttOutcome (char [][] board) {
        // Exercise 4: write a method to check if someone has won in a game of tic-tac-toe

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
        // Exercise 3: given two arrays, compute the pair values (one value in each array) with the smallest
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

        // Exercise 2: given 2 straight line segments (represented as a start and end point),
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
        // Exercise 1: write a function to swap two variable values in place (without using temp variables)

        // simple solution is to use subtraction/addition/subtraction with the two values a and b

        System.out.println("Before Swap: a:" + a + "  b:" + b);
        if (a > b) {
            a = a - b;
            b = a + b;
            a = b - a;
        } else {
            b = b - a;
            a = b + a;
            b = a - b;
        }
        System.out.println("After Swap: a:" + a + "  b:" + b);
    }

} // end class
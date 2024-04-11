package org.example;
import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {

        int[] array1 = {1, 3, 200, 11, 2};
        int[] array2 = {9, 33, 217, 18, 66};
        System.out.println(smallestDiff(array1, array2));    // expected output: 2 (11, 9)

        intersection(0, 0, 7, -7, 0, -5, 7, -2);

        numberSwapper(234, 232);
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
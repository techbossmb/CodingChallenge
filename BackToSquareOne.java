import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
* IEEEXtreme 8.0
* https://www.hackerrank.com/contests/ieeextreme-challenges/challenges/back-to-square-1
* <problem_description>
* Write a program that determines the expected number of turns needed for a player to reach the final square.
* For example, consider the board below with n = 3 and p(1) = 0.5 and p(2) = 0.25. 
* A player moves to square 1 on their first turn. 
* With probability p(1) , they move to square 2 on their second turn, but with probability 1- p(1) , 
* they remain on square 1. If they were lucky and made it to square 2 on their second turn, 
* they advance to square 3 on their third turn with probability p(2) , 
* but they would go back to square 1 with probability 1- p(2) . 
* Thus, a really lucky player could finish is 3 turns. However, on average, 
* it would take 13 turns for a player to make it to square 3.
* </problem_description>
*/
public class BackToSquareOne{

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while(s.hasNext()){
            int n = s.nextInt();
            if(n < 2)continue;
            double[] nextPrbs = new double[n-1];
            double[] eigens = new double[n-1];
            double currentEigen = 1;
            double sum = 1;
            for(int i = 0; i < n-1; i++){
                double prb = s.nextDouble(); 
                nextPrbs[i] = prb;
                eigens[i] = currentEigen * nextPrbs[i];
                currentEigen = eigens[i];
                sum += eigens[i];
            }
            double result = sum/eigens[eigens.length-1];
            System.out.println(Math.round(result));
        }
    }
}

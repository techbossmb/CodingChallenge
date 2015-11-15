import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class FibonacciModified{
    private static int firstTerm, secondTerm;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        firstTerm = s.nextInt();
        secondTerm = s.nextInt();
        System.out.println(computeTerm(s.nextInt())+"");
    }
    
    private static BigInteger computeTerm(int n){
        if(n == 1)
            return BigInteger.valueOf(firstTerm);
        if(n == 2)
            return BigInteger.valueOf(secondTerm);
        
        BigInteger nminus1 = computeTerm(n-1);
        BigInteger nminus2 = computeTerm(n-2);
        return nminus2.add(nminus1.pow(2));
    }
}
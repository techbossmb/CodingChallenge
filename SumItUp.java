import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SumItUp{

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        BigInteger[] values = new BigInteger[n];
        StringTokenizer token = new StringTokenizer(s.nextLine());
        for(int i = 0; i < n; i++){
            values[i] = new BigInteger(token.nextToken());
        }
        BigInteger sum = new BigInteger("0");
        for(int i = 0; i < n; i++){
            sum = sum.add(values[i]);
        }
        
        int q = s.nextInt();
        BigInteger sums = sum.multiply(new BigInteger("2").pow(q));
        BigInteger result = sums.mod((new BigInteger("10").pow(9)).add(new BigInteger("7")));
        System.out.println(result);
        
    }
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AlternatingCharacters{

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        for(int testcases = 0; testcases < T; testcases++){
            String line = s.next();
            int del = 0;
            for(int stringIndex = 1; stringIndex < line.length(); stringIndex++){
                if(line.charAt(stringIndex) == line.charAt(stringIndex - 1)){
                    del++;
                }
            }
            System.out.println(del);
        }
    }
}
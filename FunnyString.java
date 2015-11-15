import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class FunnyString{

    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int iT = 0; iT < T; iT++){
            char[] jokes = br.readLine().toCharArray();
            if(isFunny(jokes)){
                System.out.println("Funny");
            }else{
                System.out.println("Not Funny");
            }
        }
    }
    
    private static boolean isFunny(char[] jokes){
        int jokeLength = jokes.length;
        for(int i = 1; i<jokeLength; i++){
           if((Math.abs((int)Character.toLowerCase(jokes[i]) - (int)Character.toLowerCase(jokes[i-1]))) != (Math.abs((int)Character.toLowerCase(jokes[jokeLength-1-i]) - (int)Character.toLowerCase(jokes[jokeLength-i])))){
               return false;
           }
        }
        return true;
    }
}
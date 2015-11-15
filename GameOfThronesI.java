import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class GameOfThrones{

    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        String inputString = myScan.nextLine();
       
        String ans = isPalindrome(inputString)?"YES":"NO";
        // Assign ans a value of YES or NO, depending on whether or not inputString satisfies the required condition
        System.out.println(ans);
        myScan.close();
    }
    
    private static boolean isPalindrome(String line){
        int[] charCount = new int[26];
        int count = 0;
        for(int i = 97; i <= 122; i++){
            charCount[i-97] = line.length() - line.replaceAll(""+(char)i, "").length();
            if(charCount[i-97] % 2 != 0){
                count++;
                if(count >= 2){
                    return false;
                }
            }
        }
        return true;
    }
}
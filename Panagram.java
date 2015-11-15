import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Panagram{

    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().toLowerCase();
        if(isPanagram(line)){
            System.out.println("pangram");
        }else{
            System.out.println("not pangram");
        }
    }
    
    private static boolean isPanagram(String sentence){
        for(int character = 97; character <= 122; character++){
            if(sentence.indexOf(character) == -1){
                return false;
            }
        }
        return true;
    }
}
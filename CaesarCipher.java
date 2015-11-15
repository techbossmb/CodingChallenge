import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CaesarCipher{
     private static final char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int stringLength = Integer.parseInt(br.readLine());
        String messages = br.readLine();
        int rotateBy = Integer.parseInt(br.readLine());
        char[] content = messages.toCharArray();
        StringBuilder ciphered = new StringBuilder();
        for(int charIndex = 0; charIndex < stringLength; charIndex++){
            if(Character.isLetter(content[charIndex])){
                //System.out.println(characterValue(content[charIndex])+"");
                ciphered.append(cipher(content[charIndex], rotateBy));
            }else{
                ciphered.append(content[charIndex]);
            }
        }
        System.out.println(ciphered.toString());
    }
    
    private static char cipher(char ch, int rotation){
        char newChar = rotateByK(ch, rotation);
        if(Character.isUpperCase(ch)){
            return Character.toUpperCase(newChar);
        }
        return newChar;
    }
    private static char rotateByK(char value, int K){
        int newPosition = characterValue(value)+K;
        if(newPosition >= abc.length){
            int newK = overFlow(K);
            newPosition = characterValue(value) + newK;
            if(newPosition >= abc.length){
               newPosition = newK - (abc.length - characterValue(value));
            }
        }
        
        char decipheredChar = indexValue(newPosition);
        return decipheredChar;
    }
    
    private static int overFlow(int position){
        int newK = position % (abc.length);
        return newK;
    }
    
    private static int characterValue(char character){
        char ch = Character.toLowerCase(character);
        int index = Arrays.binarySearch(abc, ch);
        return index;
    }
    
    private static char indexValue(int index){
        return abc[index];
    }
}
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class UtopianTree{

    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int iT = 0; iT < T; iT++){
            int N = Integer.parseInt(br.readLine());
            int h = 1;
            for(int i = 0; i < N; i++){
                if(i % 2 == 0){
                    h *= 2;
                }else{
                    h += 1;
                }
            }
            System.out.println(h+"");
        }
    }
}
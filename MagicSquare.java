import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MagicSquare{

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        int[][] matrix = new int[n][n];
        int sum_diag = 0;
        int sum_adiag = 0;
        int[] sum_rows = new int[n];
        int[] sum_columns = new int[n];
        //create dummy arrays
        /*for(int k = 0; k < n; k++){
            k_hold[k] = k;
        }*/
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int cur_value = s.nextInt();
                matrix[i][j] = cur_value;
                if(i == j)sum_diag += cur_value;
                if(i == (n-1)-j)sum_adiag += cur_value;
                sum_rows[i] += cur_value;
                sum_columns[j] += cur_value;
            }
        }
        
        ArrayList<Integer> non_magic = new ArrayList<>();
        if(sum_diag != sum_adiag)non_magic.add(0);
        for(int i = 0; i < n; i++){
            if(sum_diag != sum_rows[i])non_magic.add(i+1);
            if(sum_diag != sum_columns[i])non_magic.add(-1*(i+1));
        }
        Collections.sort(non_magic);
        System.out.println(non_magic.size());
        for(int i = 0; i < non_magic.size(); i++){
                System.out.println(non_magic.get(i));
        }
        //System.out.println("daig: "+sum_diag+" adiag: "+sum_adiag);
        
    }
}
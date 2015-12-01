import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LibraryFine {
    private static int yearFine = 10000;
    private static int monthFineRate = 500;
    private static int dayFineRate = 15;
    private static int noFine = 0;
    
    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] returnDate = dateStringToArray(br.readLine());
        DateObject returnedOn = new DateObject(returnDate[0], returnDate[1], returnDate[2]);
        int[] expectDate = dateStringToArray(br.readLine());
        DateObject expectedOn = new DateObject(expectDate[0], expectDate[1], expectDate[2]);
        computeFine(returnedOn, expectedOn);
    }
    
    private static void computeFine(DateObject returned, DateObject expected){
        int yearDifference = returned.year - expected.year;
        int monthDifference = returned.month - expected.month;
        int dayDifference = returned.day - expected.day;
        if(yearDifference != 0){
            if(yearDifference > 0){
                System.out.println(yearFine+"");
            }
            else{
                System.out.println(noFine+"");
            }
        }else if(monthDifference != 0){
            if(monthDifference > 0){
                System.out.println(monthFineRate*(monthDifference)+"");
            }else{
                System.out.println(noFine+"");
            }
        }else if(dayDifference > 0){
            System.out.println(dayFineRate*(dayDifference)+"");
        }else{
            System.out.println(noFine+"");
        }
    }
    private static int[] dateStringToArray(String date){
        StringTokenizer dateString = new StringTokenizer(date);
        int[] dateArray = new int[3];
        for(int index = 0; index < 3; index++){
            dateArray[index] = Integer.parseInt(dateString.nextToken());
        }
        return dateArray;
    }
}

class DateObject{
    public int day;
    public int month;
    public int year;
    
    public DateObject(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    public void setDay(int day){
        this.day = day;
    }
    
    public void setMonth(int month){
        this.month = month;
    }
    
    public void setYear(int year){
        this.year = year;
    }
    
    public int getDay(){
        return day;
    }
    
    public int getMonth(){
        return month;
    }
    
    public int getYear(){
        return year;
    }
}
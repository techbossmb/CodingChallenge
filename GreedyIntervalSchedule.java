import java.util.*;
import java.io.*;

public class GreedyIntervalSchedule{
	
	private static int scheduleSize;
	
	public static void main(String[] args){
		try{
			Schedule[] schedule = readSchedule();
			Arrays.sort(schedule);
			printFavouredSchedule(getFavourableSchedule(schedule));
		}catch(IOException ioE){
			System.err.println(ioE);
		}
	}
	
	private static Schedule[] readSchedule() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("greedyscheduleinput.txt")));
		int N = Integer.parseInt(br.readLine());
		scheduleSize = N;
		Schedule[] schedule = new Schedule[N];
		for(int i = 0; i < N; i++){
			StringTokenizer token = new StringTokenizer(br.readLine());
			schedule[i] = new Schedule(token.nextToken(), Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
		}
		return schedule;
	}
	
	private static ArrayList<Schedule> getFavourableSchedule(Schedule[] schedule){
		ArrayList<Schedule> favourableSchedule = new ArrayList<>();
		favourableSchedule.add(schedule[0]);
		for(int j = 0; j < scheduleSize; j++){
			if(checkCompatibility(schedule[j], favourableSchedule))
				favourableSchedule.add(schedule[j]);	
		}
		return favourableSchedule; 
	}
	
	private static boolean checkCompatibility(Schedule r, ArrayList<Schedule> s){
		if(s == null) 
			return true;
		return s.get(s.size() - 1).finish <= r.start;
	}
	
	private static void printFavouredSchedule(ArrayList<Schedule> s){
		for(Schedule i:s){
			System.out.print(i.id+" ");
		}
	}
	
}

class Schedule implements Comparable<Schedule>{
	public String id;
	public int start;
	public int finish;
	
	public Schedule(String id, int start, int finish){
		this.id = id;
		this.start = start;
		this.finish = finish;
	}
	
	public int compareTo(Schedule s){
		return new Integer(this.finish).compareTo(s.finish);
	}	
}

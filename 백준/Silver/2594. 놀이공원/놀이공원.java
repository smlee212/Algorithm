import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	static int N, maxTime, endMax;
	static int[][] map;
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    N = Integer.parseInt(br.readLine());
	    
	    map = new int[N][2];
	    
	    for(int i=0;i<N;i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int start = Integer.parseInt(st.nextToken());
	    	int end = Integer.parseInt(st.nextToken());
	    	
	    	map[i][0] = (start / 100) * 60 + (start % 100) - 10; // 0시부터 시작 시간까지의 시간
	    	map[i][1] = (end / 100) * 60 + (end % 100) + 10; // 0시부터 종료시간까지의 시간
	    }
	   
	    Arrays.sort(map, Comparator.comparingInt(arr -> arr[0])); // 시작 시간 기준 정렬
	    maxTime = Math.max(0, map[0][0] - 600); // 10시부터 놀이 기구 시작 시간까지의 시간
	    
	    endMax = 0;
	    for(int i=1;i<N;i++) {
	    	endMax = Math.max(endMax, map[i-1][1]);
	    	if(map[i][0] > endMax) { // 시작 시간과 이전 종료 시간 사이에 빈 시간이 있다면
	    		maxTime = Math.max(maxTime, map[i][0] - endMax); // 그 빈 시간과 최대 시간을 비교
	    	}
	    }
	    
	    Arrays.sort(map, Comparator.comparingInt(arr -> arr[1])); // 종료 시간 기준 정렬
	    
	    maxTime = Math.max(maxTime, 1320 - map[N-1][1]); // 제일 마지막 종료 시간과 오후 10시까지의 시간
	    
	    System.out.println(maxTime);
	}
	
}
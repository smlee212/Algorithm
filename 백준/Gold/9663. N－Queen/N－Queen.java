import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, cnt=0;
	static int[] map;
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    N = Integer.parseInt(br.readLine());
	    
	    map = new int[N+1];
	    
	    int d = 1;
	    for(int i=1;i<=N;i++) {
	    	map[d] = i;
	    	dfs(d+1);
	    }
	  
	    System.out.println(cnt);
	}
	
	static void dfs(int d) {
		if(d==N+1) {
			cnt++;
			return;
		}
		
		for(int i=1;i<=N;i++) {
			boolean check = true;
			for(int j=1;j<d;j++) {
				// 세로 중복 확인 || 대각선 중복 확인
				if(map[j]==i || Math.abs(d - j) == Math.abs(i - map[j])) {
					check = false;
					break;
				}
			}
			if(!check) continue;
						
			map[d] = i;
			dfs(d+1);
		}
	}
}
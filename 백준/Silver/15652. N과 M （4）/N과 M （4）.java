import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] selected;
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    st = new StringTokenizer(br.readLine());
	    
	    N = Integer.parseInt(st.nextToken()); 
	    M = Integer.parseInt(st.nextToken());
	    
	    selected = new int[M];
	    
	    combination(1,0);
	    
	    System.out.println(sb);
	}
	
	static void combination(int now, int cnt) {
		if(cnt == M) {
			for(int i=0;i<M;i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=now;i<=N;i++) {
			selected[cnt] = i;
			combination(i,cnt+1);
		}
	}
}
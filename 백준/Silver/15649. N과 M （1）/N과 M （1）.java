import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] selected;
	static boolean[] visited;
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    st = new StringTokenizer(br.readLine());
	    
	    N = Integer.parseInt(st.nextToken()); 
	    M = Integer.parseInt(st.nextToken());
	    
	    selected = new int[M];
	    visited = new boolean[N];
	    
	    permutation(0);
	    
	    System.out.println(sb);
	}
	
	static void permutation(int cnt) {
		if(cnt == M) {
			for(int i=0;i<M;i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[cnt] = i+1;
				permutation(cnt+1);
				visited[i] = false;
			}
		}
	}
}
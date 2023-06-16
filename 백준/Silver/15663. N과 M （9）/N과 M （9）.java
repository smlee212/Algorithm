import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] num,
		   		 selected;
	static boolean[] visited;
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    st = new StringTokenizer(br.readLine());
	    
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    
	    num = new int[N];
	    selected = new int[M];
	    visited = new boolean[N];
	    
	    st = new StringTokenizer(br.readLine());
	    for(int i=0;i<N;i++) {
	    	num[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    Arrays.sort(num);
	    
	    permutation(0);
	    
	    System.out.println(sb);
	    
	}
	
	static void permutation(int cnt) {
		if(cnt == M) {
			for(int x : selected) {
				sb.append(x).append(" ");
			}
			sb.append("\n");
				
			return;
		}
		
		int temp = 0;
		for(int i=0;i<N;i++) {
			if(!visited[i] && temp != num[i]) {
				visited[i] = true;
				temp = num[i];
				selected[cnt] = num[i];
				permutation(cnt+1);
				visited[i] = false;
			}
		}
	}
}
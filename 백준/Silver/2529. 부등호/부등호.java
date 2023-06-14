import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static char[] map;
	
	static long minRes = Long.MAX_VALUE,
			   maxRes = Long.MIN_VALUE;
	
	static int[] selected;
	static boolean[] visited;
	
	static StringBuilder min = new StringBuilder();
	static StringBuilder max = new StringBuilder();

	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    N = Integer.parseInt(br.readLine());
	    
	    map = new char[N];
	    selected = new int[N+1];
	    visited = new boolean[10];
	    
	    st = new StringTokenizer(br.readLine());
	    for(int i=0;i<N;i++) {
	    	map[i] = st.nextToken().charAt(0);
	    }
	    
	    for(int i=0;i<10;i++) {
	    	visited[i] = true;
	    	selected[0] = i;
	    	permutation(1);
	    	visited[i] = false;
	    }

	    System.out.println(max);
	    System.out.println(min);
	}
	
	static void permutation(int d) {
		if(d == N + 1) {
			long res = 0;
			for(int i=0;i<=N;i++) {
				res *= 10;
				res += selected[i];
			}
			
			if(res < minRes) {
				min.setLength(0);
				for(int i=0;i<=N;i++) {
					min.append(selected[i]);
				}
				minRes = Math.min(minRes, res);
			}
			
			if(res > maxRes) {
				max.setLength(0);
				for(int i=0;i<=N;i++) {
					max.append(selected[i]);
				}
				maxRes = Math.max(maxRes, res);
			}
			
			return;
		}
		
		for(int i=0;i<10;i++) {
			if(visited[i]) continue;
			
			if((map[d-1] == '<' && selected[d-1] < i) ||
			   (map[d-1] == '>' && selected[d-1] > i)) {
				visited[i] = true;
				selected[d] = i;
				permutation(d+1);				
				visited[i] = false;
			}
		}
	}
}
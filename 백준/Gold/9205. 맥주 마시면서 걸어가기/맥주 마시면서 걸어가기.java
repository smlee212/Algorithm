import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] pos;
	static boolean[] visited;
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder();
	    StringTokenizer st;
	    
	    int T = Integer.parseInt(br.readLine());
	    
	    for(int t=1;t<=T;t++) {
	    	
	    	n = Integer.parseInt(br.readLine());
	    	
	    	pos = new int[n+2][2];
	    	
	    	for(int i=0;i<n+2;i++) {
	    		st = new StringTokenizer(br.readLine());
	    		pos[i][1] = Integer.parseInt(st.nextToken()); // x 좌표	    		
	    		pos[i][0] = Integer.parseInt(st.nextToken()); // y 좌표
	    	}

	    	sb.append(bfs());	    	
	    }
	    System.out.println(sb);
	}
	
	static String bfs() {
		Deque<Integer> dq = new ArrayDeque<>();
		
		dq.add(0);
		visited = new boolean[100+2];
		visited[0] = true;
		
		while(!dq.isEmpty()) {
			int idx = dq.poll();
			
			if(idx == n+1) {
				return "happy\n";
			}
			
			int y = pos[idx][0];
			int x = pos[idx][1];
			
			for(int i=1;i<n+2;i++) {
				int dist = Math.abs(pos[i][0]-y) + Math.abs(pos[i][1]-x);
				if(dist <= 1000 && !visited[i]) {
					visited[i] = true;
					dq.add(i);
				}
			}
			
		}	
		return "sad\n";
	}
}
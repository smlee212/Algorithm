import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] map;
	static int y1,x1, y2,x2;
	static boolean[][] visited;
	static int[] dy = {-2,-2,0,0,2,2},
				 dx = {-1,1,-2,2,-1,1};
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder();
	    StringTokenizer st;
	    
	    n = Integer.parseInt(br.readLine());
	    
	    st = new StringTokenizer(br.readLine());
	    y1 = Integer.parseInt(st.nextToken());
	    x1 = Integer.parseInt(st.nextToken());
	    y2 = Integer.parseInt(st.nextToken());
	    x2 = Integer.parseInt(st.nextToken());

	    map = new int[n][n];
	    visited = new boolean[n][n];
	    
	    Deque<int[]> dq = new ArrayDeque<>();
	    dq.add(new int[] {y1,x1});
	    visited[y1][x1] = true;
	    
	    int cnt = 0;
	    
	    while(!dq.isEmpty()) {
	    	
	    	int size = dq.size();
	    	for(int s=0;s<size;s++) {

		    	int y = dq.peek()[0];
		    	int x = dq.peek()[1];
		    	dq.poll();
		    	
	    		if(y==y2 && x==x2) {
	    			System.out.println(cnt);
	    			System.exit(0);
	    		}
	    		
	    		for(int i=0;i<6;i++) {
	    			int ny = y + dy[i];
	    			int nx = x + dx[i];
	    			
	    			if(ny<0||nx<0||ny>=n||nx>=n) continue;
	    			
	    			if(!visited[ny][nx]) {
	    				visited[ny][nx] = true;
	    				dq.add(new int[] {ny,nx});
	    			}
	    		}
	    	}
    	
	    	cnt++;
	    }
	    
	    System.out.println(-1);
	}
}
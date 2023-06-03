import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static final int N = 5;
	static final int K = 7;
	static final int L = 4;
	
	static char[][] map;
	
	static int[] dy = {-1,0,1,0},
				 dx = {0,1,0,-1};
	static Point[] point;
	
	static int res = 0;
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder();
	    StringTokenizer st;
	    
	    map = new char[N][N];
	    
	    for(int i=0;i<N;i++) {
	    	map[i] = br.readLine().toCharArray();
	    }
	    
	    point = new Point[N*N];
	    
	    for(int i=0;i<N*N;i++) {
	    	point[i] = new Point(i%5,i/5);
	    }
	    
	    combination(new int[K], 0, 0, 0);
	    
	    System.out.println(res);
	}
	
	static void combination(int[] selected ,int now, int cnt, int depth) {
		if(cnt == K) {
			bfs(selected);
			return;
		}
		
		if(depth == N*N)
			return;
		
		selected[now] = depth;
		combination(selected, now+1, cnt+1, depth+1); // 선택 O
		combination(selected, now, cnt, depth+1); // 선택 X
	}
	
	static void bfs(int[] selected) {
		Deque<Integer> dq = new ArrayDeque<>();
		boolean[] visited = new boolean[N*N];
		
		visited[selected[0]] = true;
		dq.add(selected[0]);
		
		int cnt = 1, sCnt = 0;
		
		while(!dq.isEmpty()) {
			int nowIdx = dq.poll();
			Point now = point[nowIdx];
			
			if(map[now.y][now.x] == 'S')
				sCnt++;
			
			for(int i=0;i<4;i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				
				if(ny<0||nx<0||ny>=N||nx>=N) continue;
				
				for(int nextIdx : selected) {
					Point next = point[nextIdx];
					if(!visited[nextIdx] && next.y == ny && next.x == nx) {
						visited[nextIdx] = true;
						dq.add(nextIdx);
						cnt++;
					}
				}
			}
		}
		
		if(cnt == K && sCnt >= L) {
			res++;
		}
	}
	
	static class Point{
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
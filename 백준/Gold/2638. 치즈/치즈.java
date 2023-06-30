import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 입력 변수
	static int n,m;
	static int[][] map;
	
	// 남은 치즈 개수
	static int cnt;
	// 치즈가 녹은 후 배열
	static int[][] temp;
	// 방문 처리 배열
	static boolean[][] visited;
	// 4방 탐색 벡터
	static int[] dy = {-1,0,1,0},
			 	 dx = {0,1,0,-1};
        
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 입력 및 초기화
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cnt = 0;
		
		map = new int[n][m];
		temp = new int[n][m];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 치즈의 개수 카운팅
				if(map[i][j]==1) {
					temp[i][j] = 1;
					cnt++;
				}
			}
		}
		
		// 동작 수행
		int time = 0; // 걸린 시간
		while(cnt>0) {
			visited = new boolean[n][m];
			temp = new int[n][m];
			time++;

			dfs(0,0);
			copyMap();
		}
		
		System.out.println(time);
	}
	
	static void dfs(int y, int x) {
		visited[y][x] = true;
		
		for(int i=0;i<4;i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny<0||nx<0||ny>=n||nx>=m) continue;
			
			if(map[ny][nx]==0) {
				if(visited[ny][nx]) continue;
				
				dfs(ny,nx);
			}
			else if(map[ny][nx]==1) {
				visited[ny][nx] = true;
				temp[ny][nx]++;
			}
		}
	}
	
	static void copyMap() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(temp[i][j] >= 2) {
					map[i][j] = 0;
					cnt--;
				}
			}
		}
	}
}
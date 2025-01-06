import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 공간 크기 N
	static int N;
	// 공간 배열 map
	static int[][] map;
	
	// 물고기들의 정보 배열
	static ArrayList<Fish> fishs;
	// 아기 상어의 정보
	static Fish baby;
	// 아기 상어가 먹은 물고기의 개수
	static int cnt = 0;
	
	// 북, 서, 동, 남
	static int[] dy = {-1,0,0,1};
	static int[] dx = {0,-1,1,0};
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder();
	    StringTokenizer st;
	    
	    // 입력
	    N = Integer.parseInt(br.readLine());
	    
	    // 배열 할당
	    map = new int[N][N];
	    fishs = new ArrayList<>();
	    
	    // 입력
	    for(int i=0;i<N;i++) {
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=0;j<N;j++) {
	    		map[i][j] = Integer.parseInt(st.nextToken());
	    		// 아기상어 정보 초기화
	    		if(map[i][j]==9) {
	    			baby = new Fish(i, j, 0, 2);
	    			map[i][j]=0;
	    		}
	    		// 물고기 정보 초기화
	    		else if(map[i][j]>0) {
	    			fishs.add(new Fish(i, j, 0, map[i][j]));
	    		}
	    	}
	    }
	    
	    // 크기 순으로 정렬
	    // 크키가 같다면 위쪽, 왼쪽 순으로 정렬
	    Collections.sort(fishs);
	    
	    // 아기 상어의 모험 시작
	    eat();
	    
	    // 아기 상어가 걸린 시간 출력
	    System.out.println(baby.time);
	}
	
	public static void eat() {

		// 남아 있는 물고기가 없을때까지 반복
		while(fishs.size()>0) {
			
			// 먹을 수 있는 크기의 물고기가 없다면 종료
			if(baby.size<fishs.get(0).size) {
				return;
			}
			
			// 가까운 거리의 물고기를 먹으러 간다
			// 만약 먹을 수 있는 물고기가 있는데 먹으러 갈 수 없다면 종료
			// bfs == true이면 물고기를 먹었고, baby의 상태가 변화된 상황이다
			if(!bfs()) {
				return;
			}
		}
		
	}
	
	public static boolean bfs() {
		// 아기 상어가 움직이는 경로 저장
		Deque<Fish> dq = new ArrayDeque<>();
		// 아기 상어가 같은 시간대에 만난 물고기의 정보 저장
		PriorityQueue<Fish> pq = new PriorityQueue<>();
		
		dq.add(baby);
		
		// 방문 처리
		boolean[][] visited = new boolean[N][N];
		visited[baby.y][baby.x] = true;
		
		// 아기 상어가 움직일 수 있을 때까지 반복
		while(!dq.isEmpty()) {
			pq.clear();
			
			// 한 번에 움직일 수 있는 경우의 수를 확인한다
			int len = dq.size();
			
			for(int t=0;t<len;t++) {
				// 현재 좌표
				int y = dq.peek().y;
				int x = dq.peek().x;
				int time = dq.peek().time;
				dq.poll();
				
				// 만약 현재 좌표에 먹을 수 있는 크기의 물고기가 있다면 
				if(map[y][x]>0 && map[y][x]<baby.size) {
					// 해당 물고기의 정보를 저장해둔다
					pq.add(new Fish(y, x, time, 0));
				}
				
				// 사방 탐색을 진행
				for(int i=0;i<4;i++) {
					// 움직일 좌표
					int ny = y + dy[i];
					int nx = x + dx[i];
					
					if(ny<0||nx<0||ny>=N||nx>=N) continue;
					
					// 아기 상어가 움직일 수 있고, 방문하지 않았다면
					if(!visited[ny][nx] && map[ny][nx]<=baby.size) {
						visited[ny][nx] = true;
						// 다음 경로 저장
						dq.add(new Fish(ny, nx, time+1, 0));
					}
				}
			}
			
			// 잡아먹을 물고기가 있다면
			if(!pq.isEmpty()) {
				// priorityQueue로 인해 정렬되었기에
				// 제일 첫번째 물고기가 가장 유효하게 먹을 수있는 물고기이다
				Fish S = pq.poll();
				
				// 잡아 먹은 횟수를 증가시키고
				cnt++;
				// 만약 아기 상어의 크기와 같다면
				if(baby.size==cnt) {
					// 크기를 증가시키고
					baby.size++;
					// 잡아 먹은 횟수를 초기화한다
					cnt=0;
				}
				// 아기 상어의 정보를 갱신하고
				baby.set(S.y, S.x, S.time);
				// 잡아 먹은 물고기의 정보를 제거한다
				for(int i=0;i<fishs.size();i++) {
					if(fishs.get(i).y==S.y&&fishs.get(i).x==S.x) {
						fishs.remove(i);
						break;
					}
				}
				map[S.y][S.x]=0;
				return true;
			}			
		}
		return false;
	}
}


// 물고기 클래스
class Fish implements Comparable<Fish>{
	int y; // y좌표
	int x; // x좌표
	int time; // 지난 시간
	int size; // 물고기 크기
	
	public Fish(int y, int x, int time, int size) {
		super();
		this.y = y;
		this.x = x;
		this.time = time;
		this.size = size;
	}
	
	public void set(int y, int x, int time) {
		this.y = y;
		this.x = x;
		this.time = time;
	}
	
	@Override
	public int compareTo(Fish o) {
		if(this.size==o.size) {
			if(this.y==o.y) {
				return this.x-o.x;
			}
			return this.y-o.y;
		}
		return this.size-o.size;
	}
}
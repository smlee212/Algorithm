import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	// 입력 변수
	static int R,C,M;
	// 낚시왕이 잡은 상어의 크기 합
	static int sum = 0;
	// 낙시터 배열
	static Shark[][] map;
	// 상어가 이동할 때 임시 사용할 배열
	static Shark[][] temp;
	// 북남동서 방향벡터 (index 1부터 시작)
	static int[] dy = {0,-1,1,0,0},
				 dx = {0,0,0,1,-1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;		
		
		// 입력 변수 처리
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
        // 배열 할당
		map = new Shark[R+1][C+1];
		
        // 상어 정보 입력
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());

			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			map[y][x] = new Shark(s,d,z);
		}

        // 낚시왕 알고리즘
		fishKing();

        // 결과 출력
		System.out.println(sum);
	}
	
    // 낚시왕 알고리즘
	static void fishKing() {
		
		// 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
		for(int j=1;j<=C;j++) {
			// 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
			fishing(j);
			// 3. 상어가 이동한다.
			// 3-1. 상어가 이동한 후의 낚시터 배열 생성
			temp = new Shark[R+1][C+1];
			// 3-2. 상어 이동
			move();
			// 3-3. 기존의 낚시터 배열 갱신
			map = temp;
		}
	}
	
	// 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
	//    상어를 잡으면 격자판에서 잡은 상어가 사라진다.
	private static void fishing(int j) {
		// 땅에 가까운 상어 위치 찾기
		int i = 1;
		while(i<=R && map[i][j]==null) i++;
		
		// 상어가 없다면 낚시 종료
		if(i>R) return;
		
		// 상어를 잡고 해당 위치에서 제거
		sum += map[i][j].z;
		map[i][j] = null;
	}
	
	// 3. 상어가 이동한다.
	//    상어는 입력으로 주어진 속도로 이동하고, 속도의 단위는 칸/초이다. 
	//	    상어가 이동하려고 하는 칸이 격자판의 경계를 넘는 경우에는 방향을 반대로 바꿔서 속력을 유지한채로 이동한다.
	private static void move() {
		
		for(int i=1;i<=R;i++) {
			for(int j=1;j<=C;j++) {
				// 상어가 있다면
				if(map[i][j] != null) {
					// 현재 움직일 상어
					Shark shark = map[i][j];
					// 상어의 속력
					int s = shark.s;
					// 상어의 이동 방향
					int d = shark.d;
					// 상어의 크기
					int z = shark.z;
					// 상어의 위치 좌표
					int y = i, x = j;
					
					// 상어의 속력이 0보다 크면 이동 진행
					if(s>0) {		
						y += dy[d];
						x += dx[d];
						int cnt = s;
	
						while(cnt-->=0) {
							if(y<1) { // 북 경계 넘어설때
								d = 2;	
								y += 2*dy[d];
								x += 2*dx[d];
							}
							else if(y>R) { // 남 경계 넘어설때
								d = 1;	
								y += 2*dy[d];
								x += 2*dx[d];
							}
							else if(x>C) { // 동 경계 넘어설때
								d = 4;	
								y += 2*dy[d];
								x += 2*dx[d];
							}
							else if(x<1) { // 서 경계 넘어설때
								d = 3;	
								y += 2*dy[d];
								x += 2*dx[d];
							}
							
							if(cnt==0) break;
							
							y += dy[d];
							x += dx[d];	
						}
					}
					
					// 이동한 좌표에 다른 상어가 있다면 
					if(temp[y][x]!=null) {
						// 현재 상어의 크기가 더 크다면
						if(temp[y][x].z < z) {
							temp[y][x] = new Shark(s, d, z);
						}
					}
					// 이동한 좌표에 다른 상어가 없다면
					else {
						temp[y][x] = new Shark(s, d, z);
					}
				}
			}
		}
	}

	// 상어 클래스
	static class Shark{
		int s; // 속력
		int d; // 이동방향 (1-북,2-남,3-동,4-서)
		int z; // 크기
		public Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}

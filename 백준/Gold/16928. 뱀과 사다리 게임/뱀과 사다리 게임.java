import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 필수 변수 선언
        int[] game = new int[101]; // i번째 칸에 도착했을 시 game[i]번째 칸으로 이동 (0일 경우 이동 없음)
        int N, M;

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0;i<N+M;i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            game[from] = to;
        }

        // 풀이
        BFS(game);
    }

    public static void BFS(int[] game) {
        boolean[] visited = new boolean[101];
        Deque<int[]> dq = new ArrayDeque<>();

        // 1번째 칸에서 시작
        visited[1] = true;
        dq.add(new int[]{1,0});

        while (!dq.isEmpty()) {
            int[] now = dq.poll();
            int x = now[0]; // 현재 칸의 위치
            int cnt = now[1]; // 현재 칸까지 오기까지 굴린 주사위 횟수

            // 100번째 칸에 도착했을 시 로직 종료
            if (x == 100) {
                System.out.println(cnt);
                break;
            }

            // 주사위를 던졌을 시 나올수 있는 모든 경우
            for(int i=1;i<=6;i++) {                
                int nx = x + i; // 주사위 굴린 후 도착할 칸의 위치
                
                // 예외 처리
                if(nx > 100) // 100번째 칸을 넘어갈 경우
                    continue;  
                else if(game[nx] > 0) // 도착할 칸이 뱀 또는 사다리 칸이라면 
                    nx = game[nx];

                // 도착할 칸에 처음 도착한다면
                if(!visited[nx]) {
                    visited[nx] = true;
                    dq.add(new int[]{nx, cnt+1});
                }
            }
        }
    }
}
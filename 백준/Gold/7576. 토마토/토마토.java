import java.io.*;
import java.util.*;

public class Main {

    static int[] dy = {-1, 0, 1, 0},
                 dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    dq.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // BFS 사용
        int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
        int day = -1;
        while(!dq.isEmpty()) {
            day++;
            int size = dq.size();
            while(size-->0){
                int[] now = dq.poll();
                int y = now[0];
                int x = now[1];

                for(int i=0;i<4;i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if(ny<0||nx<0||ny>=N||nx>=M) continue;

                    if(!visited[ny][nx] && map[ny][nx] == 0) {
                        map[ny][nx] = 1;
                        dq.add(new int[]{ny,nx});
                        visited[ny][nx] = true;
                    }
                }
            }
        }

        boolean isTomato = true;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] == 0) {
                    isTomato = false;
                    break;
                }
            }
            if(!isTomato) break;
        }

        System.out.println(isTomato ? day : -1);
    }
}
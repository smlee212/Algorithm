import java.io.*;
import java.util.*;

public class Main {

    private static int N, M, K;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for(int i=1;i<=N;i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=1;j<=M;j++) {
                map[i][j] = input[j-1] - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int[] dy = {1,0,-1,0}, dx = {0,1,0,-1};

        Deque<int[]> dq = new ArrayDeque<>(); // int[] -> [y][x][방문한 칸][부순 벽의 개수]
        boolean[][][] visited = new boolean[N+1][M+1][K+1];

        dq.add(new int[]{1,1,1,0});
        visited[1][1][0] = true;

        while(!dq.isEmpty()) {
            int[] now = dq.poll();
            int y = now[0];
            int x = now[1];
            int cnt = now[2];
            int k = now[3];

            if(y==N&&x==M) {
                return cnt;
            }

            for(int i=0;i<4;i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny<=0||nx<=0||ny>N||nx>M) continue;

                if(map[ny][nx] == 0 && !visited[ny][nx][k]) {
                    visited[ny][nx][k] = true;
                    dq.add(new int[]{ny,nx,cnt+1,k});
                }
                else if(map[ny][nx] == 1 && k+1 <= K && !visited[ny][nx][k+1]){
                    visited[ny][nx][k+1] = true;
                    dq.add(new int[]{ny,nx,cnt+1,k+1});
                }
            }
        }
        return -1;
    }
}

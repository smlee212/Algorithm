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

        Deque<int[]> dq = new ArrayDeque<>(); // int[] -> [y][x][방문한 칸의 개수]
        int[][] breakCnt = new int[N+1][M+1]; // 해당 좌표에 벽을 몇번 부수고 도달했는지
        for(int i=0;i<=N;i++) {
            Arrays.fill(breakCnt[i], Integer.MAX_VALUE);
        }

        dq.add(new int[]{1,1,1});
        breakCnt[1][1] = 0;

        while(!dq.isEmpty()) {
            int[] now = dq.poll();
            int y = now[0];
            int x = now[1];
            int cnt = now[2];

            if(y==N&&x==M) {
                return cnt;
            }

            for(int i=0;i<4;i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny<=0||nx<=0||ny>N||nx>M) continue;
                int nextBreakCnt = breakCnt[y][x] + map[ny][nx];

                if(nextBreakCnt <= K && nextBreakCnt < breakCnt[ny][nx]) {
                    breakCnt[ny][nx] = nextBreakCnt;
                    dq.add(new int[]{ny,nx,cnt+1});
                }
            }
        }
        return -1;
    }
}

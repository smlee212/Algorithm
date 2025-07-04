import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[][] map;
    private static int H, W, sy, sx, fy, fx;
    private static int[] dy = {-1,0,1,0}, // 북 동 남 서
                         dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        read();

        bfs();

    }

    private static void bfs() {
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N+1][M+1];

        dq.add(new int[]{sy, sx});
        visited[sy][sx] = true;

        int cnt = 0;
        while(!dq.isEmpty()) {
            int size = dq.size();
            while(size-->0){
                int[] now = dq.poll();
                int y = now[0];
                int x = now[1];

                if(y==fy && x==fx) {
                    System.out.println(cnt);
                    return;
                }

                for(int i=0;i<4;i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if(ny<=0||nx<=0||ny>N||nx>M||visited[ny][nx]) continue;

                    if(isMove(ny, nx, i)) {
                        dq.add(new int[]{ny,nx});
                        visited[ny][nx] = true;
                    }
                }
            }
            cnt++;
        }
        System.out.println(-1);
    }

    private static boolean isMove(int y, int x, int d) {
        int i, j;
        switch (d) {
            case 0 : // 북
                for (j = 0; j < W; j++) {
                    if (map[y][x + j] == 1) return false;
                }
            break;
            case 1 : // 동
                j = x + W - 1;
                if (j > M) return false;
                for (i = 0; i < H; i++) {
                    if (map[y + i][j] == 1) return false;
                }
            break;
            case 2 : // 남
                i = y + H - 1;
                if (i > N) return false;
                for (j = 0; j < W; j++) {
                    if (map[i][x + j] == 1) return false;
                }
            break;
            case 3 : // 서
                for (i = 0; i < H; i++) {
                    if (map[y + i][x] == 1) return false;
                }
            break;
        }
        return true;
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken());
        fy = Integer.parseInt(st.nextToken());
        fx = Integer.parseInt(st.nextToken());
    }
}
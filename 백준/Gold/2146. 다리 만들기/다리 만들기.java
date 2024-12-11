import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0},
                 dx = {0, 1, 0, -1};
    static int minCnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int islandNum = -1;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 1) {
                    func1(i, j, 1, islandNum--);
                }
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] < 0) {
                    func2(i, j); 
                    func1(i, j, map[i][j], 1);
                }
            }
        }

        System.out.println(minCnt);
    }

    private static void func1(int y, int x, int target, int islandNum) {
        map[y][x] = islandNum;

        for(int i=0;i<4;i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny<0||nx<0||ny>=N||nx>=N) continue;

            if(map[ny][nx] == target) {
                func1(ny, nx, target, islandNum);
            }
        }
    }

    private static void func2(int y, int x) {
        int myIslandNum = map[y][x];
        int cnt = 1;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        boolean[][] visited = new boolean[N][N];

        pq.add(new int[]{y, x, cnt});
        visited[y][x] = true;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            y = now[0];
            x = now[1];
            cnt = now[2];

            for(int i=0;i<4;i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny<0||nx<0||ny>=N||nx>=N||visited[ny][nx]) continue;

                if(map[ny][nx] == myIslandNum) {
                    visited[ny][nx] = true;
                    pq.add(new int[]{ny,nx,cnt});
                }
                else if(map[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    pq.add(new int[]{ny,nx,cnt+1});
                }
                else {
                    minCnt = Math.min(minCnt, cnt-1);
                    return;
                }
            }
        }
    }
}
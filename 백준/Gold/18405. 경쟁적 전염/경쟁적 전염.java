import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, S, X, Y;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        BFS();

        System.out.println(map[X][Y] == 0 ? 0 : map[X][Y]);
    }

    private static void BFS() {
        // [0,1] = x,y / [2] = k / [3] = time
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1[3]==o2[3]) {
                return o1[2] - o2[2];
            }
            return o1[3] - o2[3];
        });

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(map[i][j] > 0) {
                    pq.add(new int[]{i,j,map[i][j],0});
                }
            }
        }

        int[] dx = {0,1,0,-1},
              dy = {1,0,-1,0};
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int x = now[0];
            int y = now[1];
            int k = now[2];
            int s = now[3];

            if(s == S) {
                continue;
            }

            for(int i=0;i<4;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<=0||ny<=0||nx>N||ny>N) continue;

                if(map[nx][ny] == 0) {
                    map[nx][ny] = k;
                    pq.add(new int[]{nx,ny,k,s+1});
                }
            }
        }
    }
}
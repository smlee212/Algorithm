import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[][] map;
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        read();
        func();
    }

    private static void func() {
        Deque<int[]> dq = new ArrayDeque<>();
        visited = new boolean[N+1][M+1][2];
        int[] dy = {-1,0,1,0},
              dx = {0,1,0,-1};

        dq.add(new int[]{1,1,0});
        visited[1][1][0] = true;

        int cnt = 1;
        while(!dq.isEmpty()) {
            int size = dq.size();
            while(size-->0) {
                int[] now = dq.poll();
                int y = now[0];
                int x = now[1];
                int brokenCnt = now[2];

                if(y==N && x==M) {
                    System.out.println(cnt);
                    return;
                }

                for(int i=0;i<4;i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if(ny<=0||nx<=0||ny>N||nx>M) continue;

                    if(map[ny][nx]==1 && brokenCnt==0 && !visited[ny][nx][1]) {
                        visited[ny][nx][1] = true;
                        dq.add(new int[]{ny,nx,1});
                    }
                    else if(map[ny][nx]==0 && !visited[ny][nx][brokenCnt]) {
                        visited[ny][nx][brokenCnt] = true;
                        dq.add(new int[]{ny,nx,brokenCnt});
                    }
                }
            }
            cnt++;
        }
        System.out.println(-1);
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for(int i=1;i<=N;i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=1;j<=M;j++) {
                map[i][j] = input[j-1] - '0';
            }
        }
    }
}
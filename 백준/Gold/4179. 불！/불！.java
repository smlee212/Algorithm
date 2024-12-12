import java.io.*;
import java.util.*;

public class Main {
    static char[][] initMap;
    static int[] dy = {-1, 0, 1, 0},
                 dx = {0, 1, 0, -1};
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[] jihoon = new int[2];
        initMap = new char[R][C];
        for(int i=0;i<R;i++) {
            initMap[i] = br.readLine().toCharArray();
            for(int j=0;j<C;j++) {
                if(initMap[i][j] == 'J'){
                    jihoon[0] = i;
                    jihoon[1] = j;
                }
            }
        }

        BFS(jihoon[0], jihoon[1]);
    }

    private static void BFS(int y, int x) {
        boolean[][] visitedJh = new boolean[R][C];
        boolean[][] visitedFire = new boolean[R][C];
        Deque<int[]> dqJh = new ArrayDeque<>();
        Deque<int[]> dqFire = new ArrayDeque<>();

        int time = 0;

        visitedJh[y][x] = true;
        dqJh.add(new int[]{y,x});

        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(initMap[i][j] == 'F') {
                    visitedFire[i][j] = true;
                    dqFire.add(new int[]{i,j});
                }
            }
        }

        while(!dqJh.isEmpty()) {
            int sizeFire = dqFire.size();
            for(int s=0;s<sizeFire;s++) {
                int[] now = dqFire.poll();
                y = now[0];
                x = now[1];

                for(int i=0;i<4;i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if(ny<0||nx<0||ny>=R||nx>=C||visitedFire[ny][nx]) continue;

                    if(initMap[ny][nx] == '.' || initMap[ny][nx] == 'J') {
                        visitedFire[ny][nx] = true;
                        initMap[ny][nx] = 'F';
                        dqFire.add(new int[]{ny,nx});
                    }
                }
            }

            int sizeJh = dqJh.size();
            for(int s=0;s<sizeJh;s++) {
                int[] now = dqJh.poll();
                y = now[0];
                x = now[1];

                for(int i=0;i<4;i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if(ny<0||nx<0||ny>=R||nx>=C) {
                        System.out.println(time+1);
                        return;
                    }

                    if(!visitedJh[ny][nx] && initMap[ny][nx] == '.') {
                        visitedJh[ny][nx] = true;
                        dqJh.add(new int[]{ny,nx,time+1});
                    }
                }
            }
            time++;
        }
        System.out.println("IMPOSSIBLE");
    }
}
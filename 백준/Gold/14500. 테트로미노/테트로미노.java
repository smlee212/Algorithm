import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;
    private static final int[] dy = {-1,0,1,0};
    private static final int[] dx = {0,1,0,-1};
    private static int maxSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /*
            이 문제의 핵심은 테트로미노 종류를 생각하면 안된다.
            정사각형 4개를 이어붙인 것이 테트로미노이라는 것을 인지하면 된다.
            즉, 시작점에서 4칸 움직인 결과가 테트로미노를 놓은 것과 같다는 것이다

            하지만, T모양의 경우 dfs로는 구할 수 없으므로 추가적으로 계산해준다.
         */

        visited = new boolean[N][M];
        maxSum = 0;

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                // dfs
                visited[i][j] = true;
                dfs(i,j,1, map[i][j]);
                visited[i][j] = false;
                // t모양
                tPattern(i,j);
            }
        }

        System.out.println(maxSum);
    }

    private static void tPattern(int y, int x) {
        int sum = 0;
        if(isValid(y,x-1) && isValid(y,x+1) && isValid(y+1,x)) {
            sum = map[y][x] + map[y][x-1] + map[y][x+1] + map[y+1][x];
            maxSum = Math.max(maxSum,sum);
        }
        if(isValid(y,x-1) && isValid(y,x+1) && isValid(y-1,x)) {
            sum = map[y][x] + map[y][x-1] + map[y][x+1] + map[y-1][x];
            maxSum = Math.max(maxSum,sum);
        }
        if(isValid(y+1,x) && isValid(y-1,x) && isValid(y,x+1)) {
            sum = map[y][x] + map[y+1][x] + map[y-1][x] + map[y][x+1];
            maxSum = Math.max(maxSum,sum);
        }
        if(isValid(y+1,x) && isValid(y-1,x) && isValid(y,x-1)) {
            sum = map[y][x] + map[y+1][x] + map[y-1][x] + map[y][x-1];
            maxSum = Math.max(maxSum,sum);
        }
    }

    private static void dfs(int y, int x, int cnt, int sum) {
        if(cnt==4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for(int i=0;i<4;i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(!isValid(ny,nx)) continue;

            if(!visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(ny,nx,cnt+1,sum+map[ny][nx]);
                visited[ny][nx] = false;
            }
        }
    }

    private static boolean isValid(int y, int x) {
        return y>=0 && x>=0 && y<N && x<M;
    }
}

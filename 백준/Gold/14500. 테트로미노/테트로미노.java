import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;
    private static final int[] dy = {-1,0,1};
    private static final int[] dx = {0,1,0};
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

            근데, 단순한 dfs의 경우 T모양을 만들 수 없으므로, 이동하지 않고 해당 방향의 점수만 먹는 로직을 추가함
         */

        visited = new boolean[N][M];
        maxSum = 0;

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                visited[i][j] = true;
                dfs(i,j,1, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(maxSum);
    }

    private static void dfs(int y, int x, int cnt, int sum) {
        if(cnt==4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        // 위, 아래, 오른쪽 3방향에 대해서만 탐색을 진행
        for(int i=0;i<3;i++) {
            // 첫번째에서 위로 이동은 패스
            if(cnt==1 && i==0) continue;

            int ny = y + dy[i];
            int nx = x + dx[i];

            if(isValid(ny,nx) && !visited[ny][nx]) {
                visited[ny][nx] = true;
                // 해당 방향으로 이동
                dfs(ny,nx,cnt+1,sum+map[ny][nx]);
                // 이동하지 않고 해당 방향의 점수만 먹기
                if(cnt<3)
                    dfs(y,x,cnt+1,sum+map[ny][nx]);
                visited[ny][nx] = false;
            }
        }
    }

    private static boolean isValid(int y, int x) {
        return y>=0 && x>=0 && y<N && x<M;
    }
}

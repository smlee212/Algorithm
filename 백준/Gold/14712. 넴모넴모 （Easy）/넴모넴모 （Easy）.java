import java.io.*;
import java.util.*;

public class Main {

    /**
     *  N과 M은 25 이히이지만, N x M은 25이하여야 하며, N x M은 좌표의 개수이다.
     *  우리는 해당 좌표에 넴모를 놓을지 않놓을지 경우의 수를 따지는 브루트포스 알고리즘을 사용할 것
     *  시간 복잡도는  { ( 2 ^ 25 = 약 3천만 ) * (2x2 격자 확인) = 1억 2천만 }
     *  이때, 백트래킹을 이용하면 1억번 미만으로 계산이 줄어들 것
     */

    private static int N, M;
    private static boolean[][] map;
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        // 0,0에 넴모를 놓고 시작
        map[0][0] = true;
        dfs(0,0);

        // 0,0에 넴모를 놓지 않고 시작
        map[0][0] = false;
        dfs(0, 0);

        System.out.println(cnt);
    }

    private static void dfs(int y, int x) {
        // 2x2 넴모가 있는지 확인
        if(!isValid(y,x)) return;

        // 마지막 좌표에 도달했다면 가짓수 증가
        if(y==N-1&&x==M-1) {
            cnt++;
            return;
        }

        int nx = x + 1 < M ? x + 1 : 0;
        int ny = nx == 0 ? y + 1 : y;

        // 다음 좌표에 넴모를 놓음
        map[ny][nx] = true;
        dfs(ny,nx);

        // 다음 좌표에 넴모를 놓지 않음
        map[ny][nx] = false;
        dfs(ny,nx);
    }

    private static boolean isValid(int y, int x) {
        // 2x2 사각형에 넴모가 모두 놓여져 있는지 확인
        return !(y>=1 && x>=1 && map[y][x] && map[y-1][x] && map[y][x-1] && map[y-1][x-1]);
    }
}

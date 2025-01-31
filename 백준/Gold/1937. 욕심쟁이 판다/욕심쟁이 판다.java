import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] map;
    static int[][] dp;
    static int[] dy = {-1,0,1,0},
                 dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        List<int[]> bamboos = new ArrayList<>();
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                bamboos.add(new int[]{i,j,map[i][j]});
            }
        }

        dp = new int[n][n];
        bamboos.sort((o1, o2) -> o2[2] - o1[2]);
        for(int[] bamboo : bamboos) {
            dfs(bamboo[0], bamboo[1]);
        }

        int result = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                result = Math.max(result, dp[i][j]);
            }
        }

        System.out.println(result+1);
    }

    private static void dfs(int y, int x) {
        for(int i=0;i<4;i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny<0||nx<0||ny>=n||nx>=n) continue;

            if(map[y][x] < map[ny][nx]) {
                dp[y][x] = Math.max(dp[ny][nx]+1, dp[y][x]);
            }
        }
    }
}

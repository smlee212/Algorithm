import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,M,K;
    static int cnt = 0;
    static char[][] map;
    static boolean[][] visited;
    static int[] dy = {-1,0,1,0},
                dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0;i<N;i++){
            map[i] = br.readLine().toCharArray();
        }

        visited = new boolean[N][M];
        visited[N-1][0] = true;
        dfs(N-1,0,1);
        System.out.println(cnt);
    }

    static void dfs(int y, int x, int k){
        if(k > K) return;
        if(y==0 && x==M-1){
            if(k==K) cnt++;
            return;
        }

        for(int i=0;i<4;i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny<0||nx<0||ny>=N||nx>=M||visited[ny][nx]||map[ny][nx]=='T')
                continue;

            visited[ny][nx] = true;
            dfs(ny,nx,k+1);
            visited[ny][nx] = false;
        }
    }
}

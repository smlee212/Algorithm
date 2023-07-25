import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0},
                 dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for(int i=1;i<=N;i++){
            char[] input = br.readLine().toCharArray();
            for(int j=1;j<=M;j++){
                map[i][j] = input[j-1] - '0';
            }
        }

        System.out.println(bfs(1,1));
    }

    static int bfs(int y, int x){
        Deque<Point> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N+1][M+1];

        visited[y][x] = true;
        dq.add(new Point(y,x));
        int cnt = 0;
        int size;

        while (!dq.isEmpty()){
            size = dq.size();
            cnt++;
            for(int s=0;s<size;s++){
                y = dq.peek().y;
                x = dq.peek().x;
                dq.poll();

                if(y==N && x==M){
                    return cnt;
                }

                for(int i=0;i<4;i++){
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if(ny<1||nx<1||ny>N||nx>M) continue;

                    if(!visited[ny][nx] && map[ny][nx] == 1){
                        visited[ny][nx] = true;
                        dq.add(new Point(ny,nx));
                    }
                }
            }
        }

        return -1;
    }

    static class Point{
        int y;
        int x;
        Point(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
}
import java.io.*;
import java.util.*;

public class Main {

    private static int N, L;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for(int i=0;i<N;i++) {
            cnt += func1(i) ? 1 : 0;
        }
        for(int j=0;j<N;j++) {
            cnt += func2(j) ? 1 : 0;
        }
        System.out.println(cnt);
    }

    private static boolean func1(int y) {
        boolean[] visited = new boolean[N];
        int now = 0;

        while(now+1<N) {
            int next = now+1;
            if(map[y][now]+1==map[y][next]) { // 경사가 높아질 경우
                if(check1(visited, next-L, next, y)) {
                    Arrays.fill(visited, next-L, next, true);
                    now = next;
                }
                else {
                    return false;
                }
            }
            else if(map[y][now]-1==map[y][next]) { // 경사가 낮아질 경우
                if(check1(visited, next, next+L, y)) {
                    Arrays.fill(visited, next, next+L, true);
                    now = next+L-1;
                }
                else {
                    return false;
                }
            }
            else if(map[y][now]==map[y][next]){ // 평지일 경우
                now = next;
            }
            else {
                return false;
            }
        }
        return true;
    }

    private static boolean func2(int x) {
        boolean[] visited = new boolean[N];
        int now = 0;

        while(now+1<N) {
            int next = now+1;
            if(map[now][x]+1==map[next][x]) { // 경사가 높아질 경우
                if(check2(visited, next-L, next, x)) {
                    Arrays.fill(visited, next-L, next, true);
                    now = next;
                }
                else {
                    return false;
                }
            }
            else if(map[now][x]-1==map[next][x]) { // 경사가 낮아질 경우
                if(check2(visited, next, next+L, x)) {
                    Arrays.fill(visited, next, next+L, true);
                    now = next+L-1;
                }
                else {
                    return false;
                }
            }
            else if(map[now][x]==map[next][x]){ // 평지일 경우
                now = next;
            }
            else {
                return false;
            }
        }
        return true;
    }

    // 경사로가 놓여있지 않고, 모두 같은 높이인지 확인
    private static boolean check1(boolean[] visited, int start, int end, int y) {
        if(start<0 || end>N) return false;

        for(int j=start;j<end;j++) {
            if(visited[j] || map[y][start] != map[y][j]) return false;
        }
        return true;
    }

    private static boolean check2(boolean[] visited, int start, int end, int x) {
        if(start<0 || end>N) return false;

        for(int i=start;i<end;i++) {
            if(visited[i] || map[start][x] != map[i][x]) return false;
        }
        return true;
    }
}
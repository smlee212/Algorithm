import java.io.*;
import java.util.*;

public class Main {

    static final int N = 12, M = 6;
    static char[][] map; // R, G, B, P, Y, .
    static int[][] connectMap;
    static List<Integer> numberList;
    static int maxConnect;
    static int[] dy = {-1,0,1,0}, dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new char[N][M];
        for(int i=0;i<N;i++) {
            map[i] = br.readLine().toCharArray();
        }

        int cnt = func();
        System.out.println(cnt);
    }

    private static int func() {
        int cnt = 0;
        connectMap = new int[N][M];

        // 4개 이상 연결된 뿌요가 있다면 동작 수행
        while(isConnected()) {
            // 뿌요 터짐
            boom();
            // 뿌요 내려옴
            down();
            // 연쇄 카운트
            cnt++;
        }

        return cnt;
    }

    private static boolean isConnected() {
        numberList = new ArrayList<>();
        int number = 1;

        boolean[][] visited = new boolean[N][M];
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                // 각 뿌요마다 몇개가 연결되어 있는지 dfs로 확인
                if(map[i][j] != '.' && !visited[i][j]) {
                    maxConnect = 1;
                    visited[i][j] = true;
                    connectMap[i][j] = number;
                    dfs(visited,i,j,map[i][j],number);
                    // 4개 이상 모여있을 경우
                    if(maxConnect >= 4) {
                        numberList.add(number);
                    }
                    number++;
                }
            }
        }

        return numberList.size() > 0;
    }

    private static void dfs(boolean[][] visited, int y, int x, char initColor, int number) {
        for(int i=0;i<4;i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny<0||nx<0||ny>=N||nx>=M) continue;

            // 방문하지 않았고, 초기 색깔과 동일한지 확인
            if(!visited[ny][nx] && map[ny][nx]==initColor) {
                maxConnect++;
                visited[ny][nx] = true;
                connectMap[ny][nx] = number;
                dfs(visited,ny,nx,initColor,number);
            }
        }
    }

    private static void boom() {
        // 넘버링 된 뿌요들 중 4개 이상 모인 뿌요들을 제거
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(connectMap[i][j] > 0) { // 넘버링이 되어 있는 뿌요라면
                    for(int number : numberList) {
                        if(connectMap[i][j] == number) { // 조건을 만족한 뿌요일 경우 제거
                            map[i][j] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void down() {
        // 역순으로 살펴보면서 뿌요 떨어뜨림
        for(int i=N-2;i>=0;i--) {
            for(int j=0;j<M;j++) {
                int y = i;
                // 뿌요 밑에 아무것도 없으면 한칸 내림
                while(y<N-1 && map[y][j] != '.' && map[y+1][j] == '.') {
                    map[y+1][j] = map[y][j];
                    map[y][j] = '.';
                    y++;
                }
            }
        }
    }
}

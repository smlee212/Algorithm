import java.io.*;
import java.util.*;

public class Main {
    /*
        먼저, 누적합을 구함
        dfs를 (0,0)부터 (9,9)까지 돌림
        (y,x)에서 +4 ~ +0칸 까지의 누적합을 보고 25, 16, 9, 4, 1인지 큰 값부터 하나씩 확인
         만약 9라면 => +2칸 까지에 대해 방문처리를 하고 dfs(), 이후 +1칸, +0칸에 대해서도 반복
     */

    static final int N = 10;
    static int totalSum = 0;
    static int minCnt = Integer.MAX_VALUE;
    static int[][] map, prefixSum;
    static boolean[][] visited;
    static int[] squareCnt = {5,5,5,5,5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[N][N]; // 주어진 배열
        prefixSum = new int[N][N]; // 누적합
        visited = new boolean[N][N]; // 방문 여부

        StringTokenizer st;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                // 주어진 배열 입력
                map[i][j] = Integer.parseInt(st.nextToken());
                // 전체 1의 개수 계산
                totalSum += map[i][j]; 
                // 누적합 갱신
                prefixSum[i][j] = map[i][j]; 
                if(j>0) prefixSum[i][j] += prefixSum[i][j-1];
            }
        }
        // 누적합 갱신
        for(int j=0;j<N;j++) {
            for(int i=1;i<N;i++) {
                prefixSum[i][j] += prefixSum[i-1][j];
            }
        }

        // (0,0)부터 순서대로 좌표를 확인하며 방문하지 않은 1에 대해 dfs를 진행
        int[] next = findNext(0,0);
        dfs(next[0], next[1], 0, 0);

        System.out.println(minCnt==Integer.MAX_VALUE ? -1 : minCnt);
    }

    static void dfs(int y, int x, int cnt, int sum) {
        if(cnt>minCnt) return; // 백트래킹
        // 종료 조건
        // 모든 좌표를 확인했고, 색종이로 덮은 칸의 개수가 전체 1의 개수와 같은지 확인
        if(y==N && x==N && totalSum==sum) {
            minCnt = cnt;
            return;
        }

        // 현재 좌표에서 +4 ~ +0 까지 범위를 줄여나가며 색종이를 덮음
        for(int len=4;len>=0;len--) {
            // 범위 바깥으로 나가는지 확인
            if(y+len>=N || x+len>=N) continue;
            // 해당 크기의 색종이를 사용할 수 있어야 하며, 모두 방문하지 않은 1로 덮혀있는 사각형인지 확인 
            if(squareCnt[len]>0 && isSquare(y,x,len)) {
                squareCnt[len]--;
                visitSquare(y,x,len,true);

                int[] next = findNext(y,x);
                dfs(next[0], next[1], cnt+1, sum+(len+1)*(len+1));

                squareCnt[len]++;
                visitSquare(y,x,len,false);
            }
        }
    }

    // 다음 좌표 찾기
    static int[] findNext(int y, int x) {
        for(;y<N;y++) {
            for(;x<N;x++) {
                if(map[y][x] == 1 && !visited[y][x]) {
                    return new int[]{y,x};
                }
            }
            x = 0;
        }
        return new int[]{N,N};
    }

    // len : 0~4까지
    static boolean isSquare(int y, int x, int len) {
        // 주어진 사각형에 대해서 방문 여부 확인하기
        for(int i=0;i<=len;i++) {
            for(int j=0;j<=len;j++) {
                if(visited[y+i][x+j]) return false;
            }
        }

        // 주어진 사각형의 누적합 구하기
        int ny = y + len;
        int nx = x + len;

        int sum = prefixSum[ny][nx];
        if(y>0) {
            sum -= prefixSum[y-1][nx];
        }
        if(x>0) {
            sum -= prefixSum[ny][x-1];
        }
        if(y>0 && x>0) {
            sum += prefixSum[y - 1][x - 1];
        }
        return sum == (len+1) * (len+1);
    }

    // 주어진 사각형에 대해 방문 여부 처리하기
    static void visitSquare(int y, int x, int len, boolean isVisited) {
        for(int i=0;i<=len;i++) {
            for(int j=0;j<=len;j++) {
                visited[y+i][x+j] = isVisited;
            }
        }
    }
}

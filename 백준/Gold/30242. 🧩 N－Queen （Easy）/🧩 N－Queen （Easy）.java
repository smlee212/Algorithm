import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static boolean[][] map;
    private static boolean isEnd;
    private static int[] rows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new boolean[N+1][N+1];
        rows = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            int input = Integer.parseInt(st.nextToken());
            if(input!=0) {
                map[i][input] = true;
                rows[i] = input;
            }
        }

        int startCol = searchStartCol();

        isEnd = false;
        for(int row=1;row<=N;row++) {
            if(!map[startCol][row] && isValid(startCol,row)) {
                map[startCol][row] = true;
                rows[startCol] = row;
                dfs(startCol+1);
                map[startCol][row] = false;
                rows[startCol] = 0;
            }
        }

        if(!isEnd) System.out.println(-1);
    }

    private static int searchStartCol() {
        for(int i=1;i<=N;i++) {
            if(rows[i] == 0) return i;
        }
        return -1;
    }

    private static void dfs(int nowCol) {
        if(isEnd) {
            return;
        }
        if(nowCol > N) {
            isEnd = true;
            for(int i=1;i<=N;i++) {
                System.out.print(rows[i]+" ");
            }
            return;
        }
        if(rows[nowCol] > 0) dfs(nowCol+1);

        for(int row=1;row<=N;row++) {
            if(!map[nowCol][row] && isValid(nowCol,row)) {
                map[nowCol][row] = true;
                rows[nowCol] = row;
                dfs(nowCol+1);
                map[nowCol][row] = false;
                rows[nowCol] = 0;
            }
        }
    }

    private static boolean isValid(int y, int x) {
        for(int i=1;i<=N;i++) {
            if(y!=i && map[i][x]) return false;
        }
        for(int j=1;j<=N;j++) {
            if(x!=j && map[y][j]) return false;
        }
        int i=1;
        while(y+i<=N && x+i<=N) {
            if(map[y+i][x+i]) return false;
            i++;
        }
        i=1;
        while(y+i<=N && x-i>0) {
            if(map[y+i][x-i]) return false;
            i++;
        }
        i=1;
        while(y-i>0 && x+i<=N) {
            if(map[y-i][x+i]) return false;
            i++;
        }
        i=1;
        while(y-i>0 && x-i>0) {
            if(map[y-i][x-i]) return false;
            i++;
        }
        return true;
    }
}

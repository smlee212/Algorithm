import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb;
    static int N, M, x, y;
    static int[][] map;
    static int[] dy = {0,0,-1,1},
                 dx = {1,-1,0,0};
    static int[] dice = new int[6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++) {
            int command = Integer.parseInt(st.nextToken());
            func1(command);
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }

    private static void func1(int command) {
        int ny = y + dy[command-1];
        int nx = x + dx[command-1];

        if(ny<0||nx<0||ny>=N||nx>=M) return;

        moveDice(command);
        if(map[ny][nx] > 0) { 
            dice[3] = map[ny][nx];
            map[ny][nx] = 0;
        }
        else {
            map[ny][nx] = dice[3];
        }
        y = ny;
        x = nx;
        sb.append(dice[0]).append('\n');
    }
    
    private static void moveDice(int command) {
        int temp = dice[0];
        if(command == 1) {
            dice[0] = dice[4];
            dice[4] = dice[3];
            dice[3] = dice[1];
            dice[1] = temp;
        } else if (command == 2) {
            dice[0] = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[4];
            dice[4] = temp;
        } else if (command == 3) {
            dice[0] = dice[5];
            dice[5] = dice[3];
            dice[3] = dice[2];
            dice[2] = temp;
        } else if (command == 4) {
            dice[0] = dice[2];
            dice[2] = dice[3];
            dice[3] = dice[5];
            dice[5] = temp;
        }
    }
}
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        char[][] map = new char[M+1][N+1];
        for(int i=1;i<=M;i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=1;j<=N;j++) {
                map[i][j] = input[j-1];
            }
        }

        int[][][] temp = new int[M+1][N+1][3];
        for(int i=1;i<=M;i++) {
            for(int j=1;j<=N;j++) {
                temp[i][j][0] = temp[i-1][j][0] + temp[i][j-1][0] - temp[i-1][j-1][0] + (map[i][j] == 'J' ? 1 : 0);
                temp[i][j][1] = temp[i-1][j][1] + temp[i][j-1][1] - temp[i-1][j-1][1] + (map[i][j] == 'O' ? 1 : 0);
                temp[i][j][2] = temp[i-1][j][2] + temp[i][j-1][2] - temp[i-1][j-1][2] + (map[i][j] == 'I' ? 1 : 0);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int cntJ = temp[c][d][0] - temp[c][b-1][0] - temp[a-1][d][0] + temp[a-1][b-1][0];
            int cntO = temp[c][d][1] - temp[c][b-1][1] - temp[a-1][d][1] + temp[a-1][b-1][1];
            int cntI = temp[c][d][2] - temp[c][b-1][2] - temp[a-1][d][2] + temp[a-1][b-1][2];
            sb.append(cntJ).append(' ').append(cntO).append(' ').append(cntI).append('\n');
        }

        System.out.println(sb);
    }
}

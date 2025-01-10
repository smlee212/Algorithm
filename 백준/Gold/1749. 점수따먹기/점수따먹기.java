import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] prefixSum = new int[N+1][M+1];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++) {
                int input = Integer.parseInt(st.nextToken());
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + input;
            }
        }

        int result = Integer.MIN_VALUE;
        // x1, x2 좌표를 고정
        for(int x1=1;x1<=M;x1++) {
            for(int x2=x1;x2<=M;x2++) {
                int lastSum = 0;
                // y 좌표를 1부터 늘려가며 높이만 다른 직사각형의 행렬을 지정
                for(int y=1;y<=N;y++) {
                    // y행(세로 크기 1) 그리고 x1,x2의 가로를 가진 직사각형의 누적합
                    int rowSum = prefixSum[y][x2] - prefixSum[y][x1-1] - prefixSum[y-1][x2] + prefixSum[y-1][x1-1];

                    // 이전 row들과 더했을때 커지면 더하고, 작아지면 이번행부터 다시 덧셈 시작
                    lastSum = Math.max(lastSum + rowSum, rowSum);

                    result = Math.max(result, lastSum);
                }
            }
        }

        System.out.println(result);
    }
}

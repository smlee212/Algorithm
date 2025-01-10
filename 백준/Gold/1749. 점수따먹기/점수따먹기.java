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
        for(int a=1;a<=N;a++) {
            for(int b=1;b<=M;b++) {
                int maxSum = func(prefixSum, N, M, a, b);
                result = Math.max(result, maxSum);
            }
        }

        System.out.println(result);
    }

    // a,b 부터 c,d 까지 범위의 합
    private static int func(int[][] prefixSum, int N, int M, int a, int b) {
        int maxSum = Integer.MIN_VALUE;
        for(int c=a;c<=N;c++) {
            for(int d=b;d<=M;d++) {
                int sum = prefixSum[c][d] - prefixSum[c][b-1] - prefixSum[a-1][d] + prefixSum[a-1][b-1];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}

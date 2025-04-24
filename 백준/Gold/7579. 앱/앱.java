import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] m = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }
        int[] c = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        final int maxCost = 100000;
        int result = Integer.MAX_VALUE;
        int[][] dp = new int[N][maxCost+1];
        for(int i=0;i<N;i++) {
            int cost = c[i];
            int memory = m[i];

            for(int j=0;j<=maxCost;j++) {
                if(i==0) {
                    if(j>=cost) dp[i][j] = memory;
                }
                else {
                    if(j>=cost) {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost]+memory);
                    }
                    else {
                        dp[i][j] = dp[i-1][j];
                    }
                }

                if(dp[i][j] >= M) {
                    result = Math.min(result, j);
                }
            }
        }

        System.out.println(result);
    }
}

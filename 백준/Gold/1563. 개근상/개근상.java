import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][][] dp = new int[N+1][2][3]; // [i][j][k] => [i] 학기 / [j] 지각횟수 / [k] 연속결석횟수

        // 1일차 초기화
        dp[1][0][0] = 1; // 출석
        dp[1][1][0] = 1; // 지각
        dp[1][0][1] = 1; // 결석

        for(int i=2;i<=N;i++) {
            dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % 1000000; // 출석
            dp[i][1][0] = (dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2] // 출석
                            + dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2])   // 지각
                            % 1000000;
            dp[i][0][1] = (dp[i-1][0][0]) % 1000000; // 결석
            dp[i][1][1] = (dp[i-1][1][0]) % 1000000; // 결석
            dp[i][0][2] = (dp[i-1][0][1]) % 1000000; // 결석
            dp[i][1][2] = (dp[i-1][1][1]) % 1000000; // 결석
        }

        int sum = 0;
        for(int i=0;i<2;i++) {
            for(int j=0;j<3;j++) {
                sum += dp[N][i][j];
                sum %= 1000000;
            }
        }

        System.out.println(sum);
    }
}

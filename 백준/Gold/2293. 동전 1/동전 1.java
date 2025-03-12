import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for(int i=0;i<n;i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        // 주의할 점!
        // [사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다]
        int[] dp = new int[k+1];
        dp[0] = 1;

        // 해당 가치를 각각의 코인들로 만들어 낼 때를 고려 
        for(int coin : coins) {
            for(int i=coin;i<=k;i++) {
                dp[i] += dp[i-coin];
            }
        }

        System.out.println(dp[k]);
    }
}

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            List<Integer> temp = new ArrayList<>();
            while(st.hasMoreTokens()) {
                temp.add(Integer.parseInt(st.nextToken()));
            }
            list.add(temp);
        }

        int[][] dp = new int[N+1][1001];
        for(int i=0;i<=N;i++) {
            dp[i][0] = 1;
        }

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=H;j++) {
                // 블록 사용하기
                for(int block : list.get(i-1)) {
                    if(j>=block) {
                        dp[i][j] += dp[i-1][j-block];
                        dp[i][j] %= 10007;
                    }
                }
                // 블록 사용하지 않기
                dp[i][j] += dp[i-1][j];
                dp[i][j] %= 10007;
            }
        }

        System.out.println(dp[N][H]);
    }
}
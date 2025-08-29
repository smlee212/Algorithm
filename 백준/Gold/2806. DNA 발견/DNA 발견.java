import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] dna = br.readLine().toCharArray();

        int[][] dp = new int[N][2]; // dp[i][j] : i번째 문자를 변형/유지하여 j(0:모두A, 1:모두B)가 되기 위한 최소 개수
        dp[0][0] = dna[0] == 'A' ? 0 : 1;
        dp[0][1] = dna[0] == 'B' ? 0 : 1;

        for(int i=1;i<N;i++) {
            if(dna[i] == 'A') {
                dp[i][0] = dp[i-1][0]; // i번째(A)를 유지하고 모두 A로 만들기
                dp[i][1] = Math.min(dp[i-1][0]+1, dp[i-1][1]+1); // 모두A를 변형하기 또는 i번째(A)를 B로 변경하여 모두 B로 만들기
            }
            else {
                dp[i][0] = Math.min(dp[i-1][1]+1, dp[i-1][0]+1); // 모두B를 변형하기 또는 i번째(B)를 A로 변경하여 모두 A로 만들기
                dp[i][1] = dp[i-1][1]; // i번째(B)를 유지하고 모두 B로 만들기
            }
        }

        // (모두 A 만들기 경우) 또는 (모두 B 만들기 경우에서 전부 변형)
        System.out.println(Math.min(dp[N-1][0], dp[N-1][1] + 1));
    }
}
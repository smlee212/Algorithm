import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] dna = br.readLine().toCharArray();

        int[][] dp = new int[2][N]; // dp[i][j] : j번째 문자를 변형/유지하여 i(0:모두A, 1:모두B)가 되기 위한 최소 개수
        dp[0][0] = dna[0] == 'A' ? 0 : 1;
        dp[1][0] = dna[0] == 'B' ? 0 : 1;

        for(int j=1;j<N;j++) {
            if(dna[j] == 'A') {
                dp[0][j] = dp[0][j-1]; // j번째(A)를 유지하고 모두 A로 만들기
                dp[1][j] = Math.min(dp[0][j-1]+1, dp[1][j-1]+1); // 모두A를 변형하기 또는 j번째(A)를 B로 변경하여 모두 B로 만들기
            }
            else {
                dp[0][j] = Math.min(dp[1][j-1]+1, dp[0][j-1]+1); // 모두B를 변형하기 또는 j번째(B)를 A로 변경하여 모두 A로 만들기
                dp[1][j] = dp[1][j-1]; // j번째(B)를 유지하고 모두 B로 만들기
            }
        }

        // (모두 A 만들기 경우) 또는 (모두 B 만들기 경우에서 전부 변형)
        System.out.println(Math.min(dp[0][N-1], dp[1][N-1] + 1));
    }
}
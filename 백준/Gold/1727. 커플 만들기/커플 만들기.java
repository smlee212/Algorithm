import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] man, woman;

    public static void main(String[] args) throws IOException {
        read();
        func();
    }

    private static void func() {
        Arrays.sort(man);
        Arrays.sort(woman);

        int[][] dp = new int[n+1][m+1];
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if(i==j) {
                    dp[i][j] = dp[i-1][j-1] + Math.abs(man[i-1]-woman[j-1]);
                }
                else if(i<j) {
                    dp[i][j] = Math.min(dp[i-1][j-1] + Math.abs(man[i-1]-woman[j-1]), dp[i][j-1]);
                }
                else {
                    dp[i][j] = Math.min(dp[i-1][j-1] + Math.abs(man[i-1]-woman[j-1]), dp[i-1][j]);
                }
            }
        }

        System.out.println(dp[n][m]);
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        man = new int[n];
        woman = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            man[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++) {
            woman[i] = Integer.parseInt(st.nextToken());
        }
    }
}
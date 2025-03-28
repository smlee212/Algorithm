import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_SCV = 60;
    static int N;
    static int[] scv = new int[3];
    static int[][][] dp = new int[MAX_SCV+1][MAX_SCV+1][MAX_SCV+1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<=MAX_SCV;i++) {
            for(int j=0;j<=MAX_SCV;j++) {
                for(int k=0;k<=MAX_SCV;k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        attack(scv[0],scv[1],scv[2],0);

        System.out.println(dp[0][0][0]);
    }

    static void attack(int a, int b, int c, int cntAttack) {
        a = Math.max(a, 0);
        b = Math.max(b, 0);
        c = Math.max(c, 0);

        // 최소 공격 횟수보다 더 많이 공격한 상태라면 중단
        if(dp[0][0][0] <= cntAttack) return;
        // 현재 공격 횟수가 더 많이 공격한 상태라면 중단
        if(dp[a][b][c] <= cntAttack) return;

        dp[a][b][c] = cntAttack;

        // 최소 공격 횟수 갱신
        if(a==0 && b==0 && c==0) {
            dp[a][b][c] = Math.min(dp[a][b][c], cntAttack);
            return;
        }

        attack(a-9,b-3,c-1,cntAttack+1);
        attack(a-9,b-1,c-3,cntAttack+1);
        attack(a-3,b-9,c-1,cntAttack+1);
        attack(a-1,b-9,c-3,cntAttack+1);
        attack(a-3,b-1,c-9,cntAttack+1);
        attack(a-1,b-3,c-9,cntAttack+1);
    }
}

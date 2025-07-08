import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[] P;

    public static void main(String[] args) throws IOException {
        read();
        dp();
    }

    private static void dp() {
        String[] dp = new String[M+1];
        Arrays.fill(dp, "0");

        // 초기값 설정
        for(int i=0;i<N;i++) {
            if(P[i] > M) continue;
            dp[P[i]] = Integer.toString(i);
        }

        // 각 금액마다 만들 수 있는 큰 숫자를 구함
        for(int i=0;i<=M;i++) {
            if(dp[i].equals("0")) continue;

            for(int j=0;j<N;j++) {
                if(i+P[j]<=M) {
                    String newNumber = dp[i] + j;
                    String nowNumber = dp[i+P[j]];

                    if(compare(newNumber, nowNumber)) {
                        dp[i+P[j]] = newNumber;
                    }
                }
            }
        }

        String result = "0";
        for(String num : dp) {
            if(compare(num, result)) {
                result = num;
            }
        }
        System.out.println(result);
    }

    private static boolean compare(String a, String b) {
        if(a.length()==b.length()) {
            return a.compareTo(b) > 0;
        }
        else {
            return a.length() - b.length() > 0;
        }
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        P = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
    }
}
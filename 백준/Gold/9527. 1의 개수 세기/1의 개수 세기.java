import java.io.*;
import java.util.*;

public class Main {

    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        dp = new long[55];
        dp[1] = 1;
        for(int i=2;i<55;i++) {
            dp[i] = 2 * dp[i-1] + (1L << (i-1));
        }

        System.out.println(calc(b) - calc(a-1));
    }

    private static long calc(long x) {
        if(x==1) return 1;

        int bit = getBit(x); // x의 비트 자리수
        long sum = 0; // 1의 총 합
        int bitCnt = 0; // x의 앞비트부터 카운팅한 1의 개수

        while(bit > 0) {
            // x의 앞 비트부터 1이 나올 경우
            if((x & (1L << bit-1)) != 0) {
                // 최종값 구하기
                if(bit==1) {
                    sum += bitCnt;
                }
                else {
                    sum += dp[bit - 1] + ((1L << bit-1) * bitCnt);
                }
                // 다음 1 비트를 찾기 위해 현재 비트 빼기
                x -= (1L << bit);

                // 현재 1 비트 개수 누적
                bitCnt++;
            }
            bit--;
        }
        // x의 1의 개수 더하기
        return sum + bitCnt;
    }

    private static int getBit(long x) {
        String bit = Long.toBinaryString(x);
        return bit.length();
    }
}
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(N==1?1:pow(N-2)).append("\n");
        }
        System.out.println(sb);
    }

    static long pow(int n) {
        if(n==0) return 1;

        if(n%2==0) {
            long div = pow(n/2);
            return div * div % MOD;
        }
        else {
            return 2 * pow(n-1) % MOD;
        }
    }
}

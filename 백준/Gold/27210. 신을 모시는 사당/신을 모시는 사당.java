import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] map = new int[N+1];
        for(int i=1;i<=N;i++) {
            map[i] = Integer.parseInt(st.nextToken()) == 1 ? 1 : -1;
        }

        int sum = 0, result = 0;
        int maxSum = 0, minSum = 0;

        for(int i=1;i<=N;i++) {
            sum += map[i];

            result = Math.max(result, Math.max(Math.abs(sum-minSum), Math.abs(maxSum-sum)));

            maxSum = Math.max(maxSum, sum);
            minSum = Math.min(minSum, sum);
        }

        System.out.println(result);
    }
}

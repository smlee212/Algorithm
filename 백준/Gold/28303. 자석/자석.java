import java.io.*;
import java.util.*;

public class Main {

    private static int N, K;
    private static int[] map;

    public static void main(String[] args) throws IOException {
        read();
        func();
    }

    private static void func() {
        int[] arrNS = new int[N-1];
        int[] arrSN = new int[N-1];

        for(int i=0;i<N-1;i++) {
            arrNS[i] = map[i] - map[i+1] - K;
            arrSN[i] = map[i+1] - map[i] - K;
        }

        int[] sumNS = new int[N-1];
        int[] sumSN = new int[N-1];
        sumNS[0] = arrNS[0];
        sumSN[0] = arrSN[0];
        for(int i=1;i<N-1;i++) {
            sumNS[i] = Math.max(0, sumNS[i-1]) + arrNS[i];
            sumSN[i] = Math.max(0, sumSN[i-1]) + arrSN[i];
        }

        int maxPrefixSum = Integer.MIN_VALUE;
        for(int i=0;i<N-1;i++) {
            maxPrefixSum = Math.max(maxPrefixSum, Math.max(sumNS[i], sumSN[i]));
        }

        System.out.println(maxPrefixSum);
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        map = new int[N];
        for(int i=0;i<N;i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
    }
}
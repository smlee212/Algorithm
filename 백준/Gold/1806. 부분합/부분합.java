import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] num = new int[N];
        for(int i=0;i<N;i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int l=0, r=0;
        long nowSum = num[0];
        int minSumLength = Integer.MAX_VALUE;

        while(l<N) {
            if(nowSum < S) {
                r++;
                if(r>=N) break;
                nowSum += num[r];
            }
            else {
                minSumLength = Math.min(minSumLength, r-l+1);
                nowSum -= num[l];
                l++;
            }
        }

        System.out.println(minSumLength == Integer.MAX_VALUE ? 0 : minSumLength);
    }
}

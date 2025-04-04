import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] array = new int[N];
        for(int i=0;i<N;i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = N-1;
        int maxSum = 0;

        while(l<r) {
            int sum = (r - l - 1) * Math.min(array[l], array[r]);
            maxSum = Math.max(maxSum, sum);

            if(array[l] < array[r]) {
                l++;
            }
            else if(array[l] > array[r]) {
                r--;
            }
            else {
                l++; r--;
            }
        }

        System.out.println(maxSum);
    }
}

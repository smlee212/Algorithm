import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int l = 0;
        int r = N-1;
        int x=arr[l], y=arr[r];
        int min = Integer.MAX_VALUE;

        while(l < r) {
            int sum = arr[l] + arr[r];

            if(min > Math.abs(sum)) {
                min = Math.abs(sum);
                x = arr[l];
                y = arr[r];

                if(sum == 0) break;
            }

            if(sum < 0) l++;
            else r--;
        }
        System.out.println(x+" "+y);
    }
}
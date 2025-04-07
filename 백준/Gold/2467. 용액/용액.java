import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] map = new int[N];
        for(int i=0;i<N;i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = N-1;
        int minSum = Integer.MAX_VALUE;
        int A=0, B=0;

        while(l<r) {
            int sum = map[l] + map[r];

            if(minSum > Math.abs(sum)) {
                minSum = Math.abs(sum);
                A = map[l];
                B = map[r];
            }

            if(sum > 0){
                r--;
            }
            else {
                l++;
            }
        }

        System.out.println(A+" "+B);
    }
}

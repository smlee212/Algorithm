import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        boolean[] isValue = new boolean[1000001];
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            int a = Integer.parseInt(st.nextToken());
            isValue[a] = true;
            arr[i] = a;
        }
        int x = Integer.parseInt(br.readLine());

        int cnt = 0;
        for(int i=0;i<n;i++) {
            int idx = x - arr[i];
            if(0 <= idx && idx <= 100000 && isValue[x-arr[i]]) {
                cnt++;
            }
        }

        System.out.println(cnt / 2);
    }
}
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if(n <= k) {
            System.out.println(0);
        }
        else {
            int cnt = 0;
            while (Integer.bitCount(n + cnt) > k) {
                cnt++;
            }
            System.out.println(cnt);
        }
    }
}
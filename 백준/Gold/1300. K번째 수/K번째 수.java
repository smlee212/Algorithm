import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int l = 1, r = k;
        int result = 0;

        while(l<=r) {
            int mid = (l+r) / 2;

            if(func(mid, n) < k) {
                l = mid + 1;
            }
            else {
                result = mid;
                r = mid - 1;
            }
        }

        System.out.println(result);
    }

    // mid 값보다 작거나 같은 수의 개수
    private static int func(int mid, int n) {
        int cnt = 0;
        for(int i=1;i<=n;i++) {
            cnt += Math.min(n, mid/i);
        }
        return cnt;
    }
}
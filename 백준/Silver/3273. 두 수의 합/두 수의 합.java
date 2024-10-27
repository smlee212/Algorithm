import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            int a = Integer.parseInt(st.nextToken());
            set.add(a);
        }
        int x = Integer.parseInt(br.readLine());

        int cnt = 0;
        for(int num : set) {
            if(num < x  && num * 2 != x && set.contains(x-num)) {
                cnt++;
            }
        }

        System.out.println(cnt / 2);
    }
}
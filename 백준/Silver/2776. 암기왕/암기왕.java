import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++) {
            int N = Integer.parseInt(br.readLine());
            Set<Integer> set = new HashSet<>();

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                set.add(Integer.parseInt(st.nextToken()));
            }

            int M = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++) {
                int input = Integer.parseInt(st.nextToken());
                if(set.contains(input)) {
                    sb.append("1\n");
                }
                else {
                    sb.append("0\n");
                }
            }
        }
        System.out.println(sb);
    }
}
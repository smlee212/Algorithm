import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<N;i++) {
            int num = Integer.parseInt(st.nextToken());
            if(map.containsKey(num)) {
                int cnt = map.get(num);
                map.put(num, cnt+1);
            }
            else {
                map.put(num, 1);
            }
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<M;i++) {
            int num = Integer.parseInt(st.nextToken());
            if(map.containsKey(num)) {
                sb.append(map.get(num)).append(" ");
            }
            else {
                sb.append("0 ");
            }
        }
        System.out.println(sb);
    }
}
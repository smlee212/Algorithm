import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            int n = Integer.parseInt(br.readLine());

            HashMap<String, Integer> map = new HashMap<>();
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                String w = st.nextToken();
                map.put(w,map.get(w) == null ? 1 : map.get(w)+1);
            }

            int result = 1;
            for(String key : map.keySet()){
                int value = map.get(key);
                result *= value+1;
            }

            sb.append(result-1).append("\n");
        }

        System.out.println(sb);
    }
}
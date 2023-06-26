import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] arr = new String[n+1];
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=1;i<=n;i++){
            String input = br.readLine();
            map.put(input,i);
            arr[i] = input;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m;i++){
            String input = br.readLine();
            if('0'>input.charAt(0) || input.charAt(0)>'9') {
                sb.append(map.get(input)).append("\n");
            }
            else {
                sb.append(arr[Integer.parseInt(input)]).append("\n");
            }
        }

        System.out.println(sb);
    }
}
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] map = new int[N];
        for(int i=0;i<N;i++) {
            map[i] = br.readLine().length();
        }

        int[] lengthCounter = new int[21];
        for(int i=0;i<=K;i++) {
            lengthCounter[map[i]]++;
        }
        int now = map[0];
        long result = lengthCounter[now] - 1;

        for(int i=1;i<N;i++) {
            now = map[i];
            int first = map[i-1];
            int last = i+K < N ? map[i+K] : 0;

            lengthCounter[first]--;
            lengthCounter[last]++;

            result += lengthCounter[now] - 1;
        }

        System.out.println(result);
    }
}

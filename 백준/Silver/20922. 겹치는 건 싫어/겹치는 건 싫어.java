import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0, maxCnt = 0;
        int[] cnt = new int[100001];

        while(end < N){
            while(end < N && cnt[arr[end]] + 1 <= K){
                cnt[arr[end]]++;
                end++;
            }
            int len = end - start;
            maxCnt = Math.max(maxCnt, len);
            cnt[arr[start]]--;
            start++;
        }
        System.out.println(maxCnt);
    }
}
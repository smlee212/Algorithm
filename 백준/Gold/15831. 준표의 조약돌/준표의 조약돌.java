import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        char[] arr = br.readLine().toCharArray();

        int l = 0, r = 0;
        int wCnt = 0, bCnt = 0;
        int maxLen = 0;
        while(l<=r && r<N) {
            // 조약돌 줍기
            if(arr[r] == 'W') wCnt++;
            else bCnt++;

            // 흑돌이 넘칠 경우
            while(bCnt>B && l<=r) {
                if(arr[l] == 'W') wCnt--;
                else bCnt--;
                l++;
            }
            r++;

            if(wCnt>=W && bCnt<=B) {
                maxLen = Math.max(maxLen, r-l);
            }
        }
        System.out.println(maxLen);
    }
}
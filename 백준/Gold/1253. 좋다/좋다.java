import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(num);

        // 이분 탐색 시작
        int cnt = 0;
        for(int i=0;i<N;i++) {
            if(binarySearch(num, N, i)) cnt++;
        }

        System.out.println(cnt);
    }

    private static boolean binarySearch(int[] num, int N, int targetIndex) {
        int l = 0; // num[] 의 왼쪽 인덱스
        int r = N-1; // num[] 의 오른쪽 인덱스
        int target = num[targetIndex];

        while(l<r) {
            int mid = num[l] + num[r]; // 두 원소의 합

            if(mid < target) { // 합이 작다면 왼쪽을 키움
                l++;
            }
            else if(mid > target) { // 합이 크다면 오른쪽을 작게함
                r--;
                if(r==targetIndex) r--;
            }
            else { // 타겟과 같다면 종료
                if (l == targetIndex)
                    l++;
                else if (r == targetIndex)
                    r--;
                else
                    return true;
            }
        }
        return false;
    }
}

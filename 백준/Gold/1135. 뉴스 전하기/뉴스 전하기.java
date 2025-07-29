import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static List<Integer>[] list;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        read();
        func();
    }

    private static void func() {
        dp = new int[N];
        // 전화를 많이 걸어야 하는 부하에게 먼저 전화를 걸어야 함
        int result = search(0);
        System.out.println(result);
    }

    // 현재 노드에서 걸리는 최소 시간
    private static int search(int idx) {
        // 자식 노드 탐색
        for(int child : list[idx]) {
            // 자기 자신 1초 + 자식 노드에서 걸리는 최소 시간
            dp[child] = 1 + search(child);
        }

        // 가장 오래 걸리는 시간 순으로 정렬
        list[idx].sort((o1, o2) -> dp[o2] - dp[o1]);

        // 가장 오래 걸리는 순서대로 전화를 걸기
        int cnt = 0;
        for(int i=0;i<list[idx].size();i++) {
            int child = list[idx].get(i);
            dp[child] += i; // 순서대로 전화를 걸기 때문에 각각 0초, 1초, 2초, ...를 더해준다.
            cnt = Math.max(cnt, dp[child]); // 그 중에서도 가장 오래 걸리는 시간을 갱신
        }
        return cnt;
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        list = new ArrayList[N];
        for(int i=0;i<N;i++) {
            list[i] = new ArrayList<>();
            int node = Integer.parseInt(st.nextToken());
            if(node==-1) continue;
            list[node].add(i);
        }
    }
}
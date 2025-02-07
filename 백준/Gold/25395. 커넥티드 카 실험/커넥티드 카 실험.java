import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[][] car = new int[N+1][2];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            car[i][0] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            car[i][1] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        visited[S] = true;
        dq.add(S);

        int lIdx = S;
        int rIdx = S;

        while(!dq.isEmpty()) {
            int nowIdx = dq.poll();
            int lPos = car[nowIdx][0] - car[nowIdx][1];
            int rPos = car[nowIdx][0] + car[nowIdx][1];

            for(int left=lIdx-1;left>0;left--) {
                if(car[left][0] < lPos) break;
                if(!visited[left]) {
                    lIdx = Math.min(lIdx, left);
                    visited[left] = true;
                    dq.add(left);
                }
            }

            for(int right=rIdx+1;right<=N;right++) {
                if(rPos < car[right][0]) break;
                if(!visited[right]) {
                    rIdx = Math.max(rIdx, right);
                    visited[right] = true;
                    dq.add(right);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=lIdx;i<=rIdx;i++) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}

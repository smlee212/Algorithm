import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] game = new int[101];
        boolean[] visited = new boolean[101];

        for(int i=0;i<N+M;i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            game[from] = to;
        }

        Deque<int[]> dq = new ArrayDeque<>();

        dq.add(new int[]{1,0});
        visited[1] = true;

        while (!dq.isEmpty()) {
            int[] now = dq.poll();
            int x = now[0];
            int cnt = now[1];

            if (x == 100) {
                System.out.println(cnt);
                break;
            }

            for(int i=1;i<=6;i++) {
                int nx = x + i;

                if(nx > 100) continue;
                else if(game[nx] > 0) nx = game[nx];

                if(!visited[nx]) {
                    visited[nx] = true;
                    dq.add(new int[]{nx, cnt+1});
                }
            }
        }
    }
}
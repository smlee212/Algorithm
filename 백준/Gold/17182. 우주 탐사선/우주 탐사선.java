import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[][] graph;
    private static boolean[] visited;
    private static int minTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k=0;k<N;k++) {
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        visited = new boolean[N];
        minTime = Integer.MAX_VALUE;

        visited[start] = true;
        dfs(start, 1, 0);

        System.out.println(minTime);
    }

    private static void dfs(int now, int cnt, int sumTime) {
        if(cnt >= N) {
            minTime = Math.min(sumTime, minTime);
            return;
        }
        if(sumTime > minTime) {
            return;
        }

        for(int next=0;next<N;next++) {
            if(visited[next]) continue;

            visited[next] = true;
            dfs(next, cnt+1, sumTime + graph[now][next]);
            visited[next] = false;
        }
    }
}

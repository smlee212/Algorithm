import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static List<List<int[]>> graph;
    private static int start, end, minCost;
    private static List<Integer>[] path;

    public static void main(String[] args) throws IOException {
        input();

        dijkstra();

        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(minCost).append("\n");
        sb.append(path[end].size()).append("\n");
        for(int node : path[end]) {
            sb.append(node).append(" ");
        }
        System.out.println(sb);
    }

    static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[N+1];

        int[] cost = new int[N+1];
        Arrays.fill(cost, Integer.MAX_VALUE);

        path = new ArrayList[N+1];
        for(int i=0;i<=N;i++) {
            path[i] = new ArrayList<>();
        }
        path[start].add(start);

        pq.add(new int[]{start, 0});
        cost[start] = 0;

        while(!pq.isEmpty()) {
            int[] now = pq.poll();

            if(visited[now[0]]) continue;

            visited[now[0]] = true;

            for(int[] next : graph.get(now[0])) {
                if(cost[next[0]] > cost[now[0]] + next[1]) {
                    cost[next[0]] = cost[now[0]] + next[1];
                    path[next[0]] = new ArrayList<>(path[now[0]]);
                    path[next[0]].add(next[0]);
                    pq.add(new int[]{next[0], cost[next[0]]});
                }
            }
        }

        minCost = cost[end];
    }

    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, cost});
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }
}
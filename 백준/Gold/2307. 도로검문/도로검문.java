import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static List<List<int[]>> graph;

    private static int[] prev; // prev[i] 노드는 i번 노드와 연결되어 있음
    private static final int MAX_PASSING_TIME = 50000000; // t * M (통과시간 * 도로의 수)의 최대치
    private static final int START_NODE = 1;

    public static void main(String[] args) throws IOException {
        read();
        func();
    }

    private static void func() {
        prev = new int[N+1];
        int standardPassingTime = dij(""); // 기본 탈출 시간
        int maxPassingTime = standardPassingTime; // 검문 시 최대 탈출 시간

        // 최단 경로 추적
        List<int[]> shortestPathEdgeList = getShortestPathEdgeList();

        // 모든 간선들을 탐색
        for(int[] edge : shortestPathEdgeList) {
            int a = edge[0];
            int b = edge[1];

            // 해당 간선을 잠시 끊어준 후 다익스트라 동작
            int passingTime = dij(a+"-"+b);
            // 탈출하지 못하게 했다면
            if(passingTime == -1) {
                System.out.println(-1);
                return;
            }
            maxPassingTime = Math.max(maxPassingTime, passingTime);
        }

        // 지연 시간 출력
        System.out.println(maxPassingTime - standardPassingTime);
    }

    private static List<int[]> getShortestPathEdgeList() {
        List<int[]> shortestPathEdgeList = new ArrayList<>();
        int cur = N;
        while(prev[cur] != 0) { // 연결된 노드들을 따라 간선을 구해나감
            int from = prev[cur];
            int to = cur;
            shortestPathEdgeList.add(new int[]{from, to});
            cur = from;
        }
        return shortestPathEdgeList;
    }

    private static int dij(String checkStreet) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{START_NODE,0});

        boolean[] visited = new boolean[N+1];
        int[] dist = new int[N+1];
        Arrays.fill(dist, MAX_PASSING_TIME);
        dist[START_NODE] = 0;

        while(!pq.isEmpty()) {
            int x = pq.poll()[0];

            if(visited[x]) continue;
            visited[x] = true;

            for(int[] next : graph.get(x)) {
                int nx = next[0];
                int nCost = next[1];

                // 만약 검문한 도로라면 넘어감
                if(checkStreet.equals(x+"-"+nx) || checkStreet.equals(nx+"-"+x)) continue;

                // 최소 거리 갱신
                if(dist[nx] > dist[x] + nCost) {
                    dist[nx] = dist[x] + nCost;
                    prev[nx] = x;
                    pq.add(new int[]{nx, dist[nx]});
                }
            }
        }
        return dist[N] == MAX_PASSING_TIME ? -1 : dist[N];
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b,t});
            graph.get(b).add(new int[]{a,t});
        }
    }
}
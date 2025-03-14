import java.io.*;
import java.util.*;

public class Main {

    /**
     *      트리의 지름을 구하는 공식이 있더라....
     *      1. 트리에서 임의의 정점 x에서 가장 먼 정점 y를 찾는다
     *      2. 정점 y에서 가장 먼 정점 z를 찾는다
     *      3. y와 z 사이의 거리가 트리의 지름이 된다.
     */

    private static List<List<int[]>> graph;
    private static boolean[] visited;
    private static int maxDist, maxNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for(int i=0;i<=V;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<V;i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int nextNode = Integer.parseInt(st.nextToken());
            while(nextNode!=-1) {
                int dist = Integer.parseInt(st.nextToken());
                graph.get(node).add(new int[]{nextNode, dist});
                nextNode = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[V+1];
        int x = 1;
        int y = searchMaxDist(x); // 첫번째 탐색 (임의의 정점 x로부터 먼 정점 y를 탐색)
        int z = searchMaxDist(y); // 두번째 탐색 (정점 y로부터 먼 정점 z를 탐색)

        System.out.println(maxDist);
    }

    private static int searchMaxDist(int node) {
        maxDist = -1;
        maxNode = -1;

        visited[node] = true;
        dfs(node,0);
        visited[node] = false;

        return maxNode;
    }

    private static void dfs(int node, int totalDist) {
        if(totalDist > maxDist) {
            maxDist = totalDist;
            maxNode = node;
        }

        for(int[] next : graph.get(node)) {
            int nextNode = next[0];
            int dist = next[1];
            if(!visited[nextNode]) {
                visited[nextNode] = true;
                dfs(nextNode, totalDist + dist);
                visited[nextNode] = false;
            }
        }
    }
}

import java.util.*;

class Solution {
    
    public int solution(int n, int[][] edge) {
        // 12:45
        
        // 간선 리스트
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        
        final int INF = Integer.MAX_VALUE;
        Arrays.fill(dist,INF);
        
        dist[1] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->o1[1]-o2[1]);
        pq.add(new int[]{1,0});
        
        while(!pq.isEmpty()) {            
            int nowNode = pq.poll()[0];
            
            if(visited[nowNode]) continue;
            visited[nowNode] = true;
            
            for(int nextNode : graph.get(nowNode)) {                
                if(dist[nextNode] > dist[nowNode] + 1) {
                    dist[nextNode] = dist[nowNode] + 1;
                    pq.add(new int[]{nextNode, dist[nowNode] + 1});
                }
            }
        }
        
        int maxDist = -1;
        int cnt = 0;
        for(int i=2;i<=n;i++) {
            if(maxDist < dist[i]) {
                maxDist = dist[i];
                cnt = 1;
            }
            else if(maxDist == dist[i]) {
                cnt++;
            }
        }
        
        return cnt;
    }
}
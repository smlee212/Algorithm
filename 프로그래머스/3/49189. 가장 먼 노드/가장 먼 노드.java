import java.util.*;

class Solution {
    
    public int solution(int n, int[][] edge) {
        // 인접 리스트 초기화
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++) {
           graph.add(new ArrayList<>()); 
        }
            
        for(int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        
        int[] visited = new int[n+1];
        for(int i=0;i<=n;i++) {
            visited[i] = Integer.MAX_VALUE;
        }
        
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{1,0});
        
        int maxDist = 0;
        int cnt = 0;
        while(!dq.isEmpty()) {
            int[] temp = dq.poll();
            int now = temp[0];
            int dist = temp[1];
            System.out.println(now+", "+dist);
            
            if(maxDist < dist) {
                maxDist = dist;
                cnt = 1;
            }
            else if(maxDist == dist) {
                cnt++;
            }
            
            for(int next : graph.get(now)) {
                if(next!=1 && dist + 1 < visited[next]) {
                    visited[next] = dist + 1;
                    dq.add(new int[]{next, dist+1});
                }
            }
        }
        
        return cnt;
    }
}
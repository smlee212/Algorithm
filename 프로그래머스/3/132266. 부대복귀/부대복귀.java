import java.util.*;

class Solution {
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // map 초기화
        List<List<Integer>> map = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            map.add(new ArrayList<>());
        }
        
        // roads -> map 변환
        for(int[] road : roads) {
            int x = road[0];
            int y = road[1];
            map.get(x).add(y);
            map.get(y).add(x);
        }
        
        // BFS 준비
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        int[] result = new int[sources.length];
        
        dq.add(new int[]{destination, 1});
        visited[destination] = true;
        
        int cnt = 0;
        while(!dq.isEmpty()) {
            int[] now = dq.poll();
            int region = now[0];
            int dist = now[1];
            
            for(int i=0;i<sources.length;i++) {
                if(sources[i] == region) {
                    cnt++;            
                    result[i] = dist;
                    break;
                }
            }
            
            if(cnt == sources.length) {
                break;
            }
            
            for(int next : map.get(region)) {
                if(!visited[next]) {
                    visited[next] = true;
                    dq.add(new int[]{next, dist+1});
                }
            }
        }
        
        for(int i=0;i<result.length;i++) {
            result[i]--;
        }
        
        return result;
    }
    
}
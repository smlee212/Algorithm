import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1,o2) -> {
            return o1[1] - o2[1];                
        });
        
        int cnt = 0;
        boolean[] visited = new boolean[routes.length];
        
        for(int i=0;i<routes.length;i++) {
            if(visited[i]) continue;
            
            cnt++;
            int end = routes[i][1];
            for(int j=i;j<routes.length;j++) {
                if(routes[j][0] <= end && end <= routes[j][1]) {
                    visited[j] = true;
                }
            }
        }
        
        return cnt;
    }
}
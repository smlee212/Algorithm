import java.util.*;

class Solution {
    // 6:40
    public int solution(int[][] routes) {
        
        // 진출 시간을 기준으로 오름차순 정렬
        Arrays.sort(routes, (o1,o2) -> {
            return o1[1] - o2[1];
        });
        
        int cnt = 0;
        boolean[] visited = new boolean[routes.length];
        
        for(int i=0;i<routes.length;i++) {
            if(!visited[i]) {
                visited[i] = true;
                cnt++;
                for(int j=i+1;j<routes.length;j++) {
                    if(routes[j][0] <= routes[i][1] && routes[i][1] <= routes[j][1]) {
                        visited[j] = true;
                    }
                }
            }
        }
        
        return cnt;
    }
}
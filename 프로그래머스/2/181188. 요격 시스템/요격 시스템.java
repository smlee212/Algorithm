import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        int n = targets.length;
        boolean[] visited = new boolean[n];
        
        Arrays.sort(targets, (o1,o2) -> {
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        
        // 무조건 [0]으로 미사일을 쏜다고 생각
        for(int i=0;i<n;i++) {
            if(!visited[i]) {
                answer++;
                visited[i] = true;
                int shoot = targets[i][1] - 1;
                for(int j=i+1;j<n;j++) {
                    if(targets[j][0] > shoot) break;
                    visited[j] = true;
                }
            }
        }
        
        return answer;
    }
}
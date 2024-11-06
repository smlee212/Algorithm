import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        answer += get(1, stations[0] - w - 1, w);
        for(int i=1;i<stations.length;i++) {
            answer += get(stations[i-1] + w + 1, stations[i] - w - 1, w);
        }
        
        answer += get(stations[stations.length-1] + w + 1, n, w);

        return answer;
    }
    
    int get(int left, int right, int w) {
        int dist = right - left + 1;
        int w_len = 2 * w + 1;
        
        if(dist <= 0) return 0;
        
        if(dist % w_len == 0)
            return dist / w_len;
        return dist / w_len + 1;
    }
}
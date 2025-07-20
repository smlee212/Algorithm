import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        
        int left = 1;
        int right = distance;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            int romoveRockCnt = 0;
            int prevRock = 0;
            
            for(int rock : rocks) {
                if (rock-prevRock < mid) {
                    romoveRockCnt++;
                } else {
                    prevRock = rock;
                }
            }
            
            if (distance-prevRock < mid) {
                romoveRockCnt++;
            }
            
            if (romoveRockCnt > n) {
                right = mid - 1;
            } else if (romoveRockCnt <= n) {
                left = mid + 1;
                answer = mid;
            }
        }
        
        return answer;
    }
}
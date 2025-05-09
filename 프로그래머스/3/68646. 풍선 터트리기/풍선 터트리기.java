import java.util.*;

class Solution {
    
    public int solution(int[] a) {
        
        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];
        Arrays.fill(leftMin, Integer.MAX_VALUE);
        Arrays.fill(rightMin, Integer.MAX_VALUE);
        
        // 왼쪽부터 최소값 갱신
        leftMin[0] = a[0];
        for(int i=1;i<a.length;i++) {
            leftMin[i] = Math.min(a[i], leftMin[i-1]);
        }
        
        // 오른쪽부터 최소값 갱신
        rightMin[a.length-1] = a[a.length-1];
        for(int i=a.length-2;i>=0;i--) {
            rightMin[i] = Math.min(a[i], rightMin[i+1]);
        }
        
        int answer = 0;
        // 남길 수 있는 풍선인지 확인
        for(int i=0;i<a.length;i++) {
            // 해당 풍선의 왼쪽 최소값과 오른쪽 최소값을 비교했을때, 두 최소값보다 크다면 남길 수 없음
            if(a[i] > leftMin[i] && a[i] > rightMin[i]) continue;
            answer++;
        }
        
        return answer;
    }
}
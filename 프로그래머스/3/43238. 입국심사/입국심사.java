class Solution {
    
    public long solution(int n, int[] times) {  
        // 심사관 별로 최대 심사 시간 구하기
        long maxTime = 0;
        for(int time : times) {
            maxTime = Math.max(maxTime, time);
        }
        
        long left = 0; 
        long right = n * maxTime; // n명 모두 최대 심사 시간
        long mid = 0;
        while(left < right) {
            mid = (left + right) / 2;
            
            long cntPeople = 0; // 심사 받은 사람 수
            for(int time : times) {
                cntPeople += mid / time; // 각 심사관별로 심사 받은 사람 수 더하기
            }
            
            if(cntPeople < n) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        
        return left;
    }
}
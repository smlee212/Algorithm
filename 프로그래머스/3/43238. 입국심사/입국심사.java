class Solution {
    public long solution(int n, int[] times) {
        int temp = 0;
        for(int time : times) {
            temp = Math.max(temp, time);
        }
        
        long left = 0;
        long right = (long)(n / times.length) *  (long)temp;
        long mid = 0;
        
        while(left < right) {
            mid = (left + right) / 2;
            long cnt = 0;
            
            for(int time : times) {
                cnt += mid / time;
            }
            
            
            if(cnt < n) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        
        return left;
    }
}
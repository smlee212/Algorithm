class Solution {
    
    public int solution(int[] money) {
        int n = money.length;
        // i번째 집을 털었을 때 최대값
        // j = 0 이면 i-1번째 집을 털지 않았을때 최대값 / j = 1 이면 i-1번째 집을 털었을때 최대값
        int[][] dpFirst = new int[2][n]; // 첫번째 집을 털었을 경우
        int[][] dpNotFirst = new int[2][n]; // 첫번째 집을 털지 않았을 경우
        
        // 0번째 집을 털었을 경우에 대해 초기값 설정
        dpFirst[1][0] = money[0];
        
        for(int i=1;i<n-1;i++) {
            dpFirst[0][i] = Math.max(dpFirst[1][i-1], dpFirst[0][i-1]);
            dpFirst[1][i] = dpFirst[0][i-1] + money[i];
            dpNotFirst[0][i] = Math.max(dpNotFirst[1][i-1], dpNotFirst[0][i-1]);
            dpNotFirst[1][i] = dpNotFirst[0][i-1] + money[i];
        }        
        
        // 첫번째 집을 털었던 dpFirst의 경우, 마지막 집은 털수 없다
        int resultFirst = Math.max(dpFirst[0][n-2], dpFirst[1][n-2]);        
        
        // 첫번째 집을 털지 않은 dpNotFirst의 경우, 마지막 집을 털수 있다
        dpNotFirst[0][n-1] = Math.max(dpNotFirst[1][n-2], dpNotFirst[0][n-2]);
        dpNotFirst[1][n-1] = dpNotFirst[0][n-2] + money[n-1];        
        int resultNotFirst = Math.max(dpNotFirst[0][n-1], dpNotFirst[1][n-1]);
                
        return Math.max(resultFirst, resultNotFirst);
    }
}
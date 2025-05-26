class Solution {
    
    public int solution(int[] money) {
        int n = money.length;
        // i번째 집을 털었을 때 최대값
        int[] dpFirst = new int[n]; // 첫번째 집을 털었을 경우
        int[] dpNotFirst = new int[n]; // 첫번째 집을 털지 않았을 경우
        
        // 초기값 설정
        dpFirst[0] = money[0];
        dpFirst[1] = Math.max(money[0], money[1]);
        dpNotFirst[0] = 0;
        dpNotFirst[1] = money[1];
        
        for(int i=2;i<n-1;i++) {
            dpFirst[i] = Math.max(dpFirst[i-2] + money[i], dpFirst[i-1]);
            dpNotFirst[i] = Math.max(dpNotFirst[i-2] + money[i], dpNotFirst[i-1]);
        }        
        // 첫번째 집을 털었기 때문에, 마지막 집은 털 수 없다
        dpFirst[n-1] = Math.max(dpFirst[n-3], dpFirst[n-2]);
        // 첫번째 집을 털지 않았기에, 마지막 집을 털 수 있다
        dpNotFirst[n-1] = Math.max(dpNotFirst[n-3] + money[n-1], dpNotFirst[n-2]);
                
        return Math.max(dpFirst[n-1], dpNotFirst[n-1]);
    }
}
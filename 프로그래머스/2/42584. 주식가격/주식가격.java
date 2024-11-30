class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        for(int i=0;i<prices.length;i++) {
            int temp = 0;
            for(int j=i+1;j<prices.length;j++) {
                temp++;
                if(prices[i] > prices[j]) {
                    break;
                }
            }
            answer[i] = temp;
        }
        
        return answer;
    }
}
import java.util.*;

class Solution {
    
    Map<String, String> parentMap;
    Map<String, Integer> indexMap;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {          
        int[] answer = new int[enroll.length];
        
        parentMap = new HashMap<>(); // 판매원 별 부모 (key:판매원, value:부모)
        indexMap = new HashMap<>(); // 판매원 index
        for(int i=0;i<enroll.length;i++) {
            parentMap.put(enroll[i], referral[i]);
            indexMap.put(enroll[i], i);
        }
        
        for(int i=0;i<seller.length;i++) {
            dfs(answer, seller[i], amount[i] * 100);
        }
        
        return answer;
    }
    
    private void dfs(int[] answer, String now, int money){
        if(now.equals("-") || money <= 0) {
            return;
        }
        
        int index = indexMap.get(now);
        String parent = parentMap.get(now);
        
        int temp = money / 10;
        answer[index] += money - temp;
        
        dfs(answer, parent, temp);
    }
}
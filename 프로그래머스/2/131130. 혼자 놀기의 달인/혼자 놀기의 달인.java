import java.util.*;

class Solution {
    
    public int solution(int[] cards) {        
        int N = cards.length;
        boolean[] visited = new boolean[N];
        List<Integer> cardGroups = new ArrayList<>();
        
        for(int i=0;i<N;i++) {
            int now = i;
            int cnt = 0;
            while(!visited[now]) {
                cnt++;
                visited[now] = true;
                now = cards[now] - 1;
            }
            if(cnt > 0) cardGroups.add(cnt);
        }
        
        Collections.sort(cardGroups, Comparator.reverseOrder());   
        
        if(cardGroups.size() >= 2) {
            return cardGroups.get(0) * cardGroups.get(1);
        }
        else {        
            return 0;
        }
    }
}
import java.util.*;

class Solution {
    
    List<List<Integer>> cardGroups;
    boolean[] visited;
    int N;
    
    public int solution(int[] cards) {        
        N = cards.length;
        visited = new boolean[N];
        cardGroups = new ArrayList<>();
        
        for(int i=0;i<N;i++) {
            if(!visited[i]) {
                visited[i] = true;
                List<Integer> group = new ArrayList<>();
                group.add(cards[i]);
                dfs(cards, cards[i]-1, group);
            }
        }
        
        List<Integer> result = new ArrayList<>();        
        for(List<Integer> group : cardGroups) {
            result.add(group.size());
        }
        Collections.sort(result, Comparator.reverseOrder());   
        
        if(result.size() >= 2) {
            return result.get(0) * result.get(1);
        }
        else {        
            return 0;
        }
    }
    
    void dfs(int[] cards, int now, List<Integer> group) {
        if(!visited[now]) {
            visited[now] = true;
            group.add(cards[now]);
            dfs(cards, cards[now]-1, group);
        }
        else {
            cardGroups.add(group);
        }
    }
}
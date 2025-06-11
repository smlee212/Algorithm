import java.util.*;

class Solution {
    
    int minA;
    
    public int solution(int[][] info, int n, int m) {
        minA = Integer.MAX_VALUE;
        
        dfs(info, n, m, 0, 0, 0, new HashSet<>());
        
        return minA == Integer.MAX_VALUE ? -1 : minA;
    }
    
    private void dfs(int[][] info, int n, int m, int now, int sumA, int sumB, Set<Integer> set) {
        if(sumA >= n || sumB >= m || sumA > minA) return;
        if(now >= info.length) {
            minA = Math.min(minA, sumA);
            return;
        }
        
        // i번째 물건을 훔칠때 현재 상황을 메모이제이션
        int key = now * 1000000 + sumA * 1000 + sumB; 
        if(set.contains(key)) {
            return;
        }
        set.add(key);
        
        dfs(info, n, m, now+1, sumA + info[now][0], sumB, set);
        dfs(info, n, m, now+1, sumA, sumB + info[now][1], set);        
    }
}
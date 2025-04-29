import java.util.*;

class Solution {
    // 5:07
    
    int[] selectNumber;
    int result = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        selectNumber = new int[5];
        
        for(int i=1;i<=n;i++) {
            selectNumber[0] = i;
            dfs(n,q,ans,1,i+1);
        }
        
        return result;
    }
    
    void dfs(int n, int[][] q, int[] ans, int depth, int index) {
        if(depth>=5) {
            if(check(n,q,ans))
                result++;
            return;
        }
        
        for(int i=index;i<=n;i++) {
            selectNumber[depth] = i;
            dfs(n,q,ans,depth+1,i+1);
        }
    }
    
    boolean check(int n, int[][] q, int[] ans) {
        Set<Integer> set = new HashSet<>();
        for(int i : selectNumber) set.add(i);
        
        for(int i=0;i<q.length;i++) {
            int cnt = 0;
            for(int j=0;j<5;j++) {
                if(set.contains(q[i][j])) {
                    cnt++;
                }
            }
            if(cnt != ans[i]) return false;
        }
        return true;
    }
}
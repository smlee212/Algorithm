import java.util.*;

// 0701
class Solution {
    
    int n, m;
    boolean[] visited;
    int[] selectArray;
    Set<String> resultSet;
    
    public int solution(String[] user_id, String[] banned_id) {
        n = user_id.length;
        m = banned_id.length;
        visited = new boolean[n];
        selectArray = new int[m];
        resultSet = new HashSet<>();
        
        for(int i=0;i<n;i++) {
            visited[i] = true;
            selectArray[0] = i;
            dfs(user_id, banned_id, 1);
            visited[i] = false;
        }
        
        return resultSet.size();
    }
    
    void dfs(String[] user_id, String[] banned_id, int index) {
        if(index == m) {
            func(user_id, banned_id);
            return;
        }
        
        for(int i=0;i<n;i++) {
            if(!visited[i]) {
                visited[i] = true;
                selectArray[index] = i;
                dfs(user_id, banned_id, index+1);
                visited[i] = false;
            }
        }
    }
    
    void func(String[] user_id, String[] banned_id) {        
        for(int i=0;i<m;i++) {
            if(!checkId(user_id[selectArray[i]], banned_id[i])) {
                return;
            }
        }
        String result = "";
        for(int i=0;i<n;i++) {
            result += visited[i] ? "1" : "0";
        }
        resultSet.add(result);
    }
    
    boolean checkId(String userId, String bannedId) {
        if(userId.length() != bannedId.length()) {
            return false;
        }
        
        for(int i=0;i<userId.length();i++) {
            char u = userId.charAt(i);
            char b = bannedId.charAt(i);
            
            if(b == '*' || u == b) {
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }
}
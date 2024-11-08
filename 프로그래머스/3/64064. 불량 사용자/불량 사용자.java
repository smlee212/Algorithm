import java.util.*;

class Solution {
    List<List<Integer>> guess_banned_user;
    Set<String> set; 
    boolean[] visited;
    
    public int solution(String[] user_id, String[] banned_id) {
        guess_banned_user = new ArrayList<>();
        
        for(String bid : banned_id) {
            List<Integer> list = new ArrayList<>();
            for(int user_idx=0;user_idx<user_id.length;user_idx++) {
                String uid = user_id[user_idx];
                if(bid.length() != uid.length()) continue;
                
                boolean check = true;
                for(int i=0;i<bid.length();i++) {
                    if(bid.charAt(i) != uid.charAt(i) && bid.charAt(i) != '*') {
                        check = false;
                        break;
                    }
                }
                
                if(check) {
                    list.add(user_idx);
                }
            }
            guess_banned_user.add(list);
        }
        
        set = new HashSet<>();
        visited = new boolean[user_id.length];
        
        dfs(0, banned_id.length);     
        
        return set.size();
    }
    
    void dfs(int idx, int len) {
        if(idx==len) {
            StringBuilder result = new StringBuilder();
            for(int i=0;i<visited.length;i++) {
                if(visited[i]) {
                    result.append(i);
                }
            }
            set.add(result.toString());
            return;
        }
        
        List<Integer> list = guess_banned_user.get(idx);
        for(Integer uid : list) {
            if(!visited[uid]) {
                visited[uid] = true;
                dfs(idx+1, len);
                visited[uid] = false;
            }
        }        
    }
}
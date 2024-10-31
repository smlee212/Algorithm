class Solution {
    
    boolean[] visited;
    int minCnt;
    
    public int solution(String begin, String target, String[] words) {
        minCnt = Integer.MAX_VALUE;
        visited = new boolean[words.length];
        
        for(int i=0;i<words.length;i++) {
            String word = words[i];
            if(!visited[i] && check(word, begin)) {
                visited[i] = true;
                dfs(words, 1, word, target);    
                visited[i] = false;                
            }
        }        
        
        return minCnt==Integer.MAX_VALUE ? 0 : minCnt ;
    }
    
    boolean check(String a, String b) {
        int cnt = 0;
        for(int i=0;i<a.length();i++) {
            if(a.charAt(i)!=b.charAt(i)) {
                cnt++;
            }
        }
        
        if (cnt != 1) return false;
        else return true;
    }
    
    void dfs(String[] words, int cnt, String now, String target) {
        if(now.equals(target)) {
            minCnt = Math.min(minCnt, cnt);
            return;            
        }
        else if(minCnt < cnt) {
            return;
        }
        
        for(int i=0;i<words.length;i++) {
            String word = words[i];
            if(!visited[i] && check(word, now)) {
                visited[i] = true;
                dfs(words, cnt+1, word, target);    
                visited[i] = false;                
            }
        }        
    }
}
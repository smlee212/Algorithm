class Solution {
    
    int minCnt = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        
        for(int i=0;i<words.length;i++) {
            if(!visited[i] && isValid(begin, words[i])){
                visited[i] = true;
                dfs(words[i], target, words, visited, 1);
                visited[i] = false;
            }
        }
        
        return minCnt == Integer.MAX_VALUE ? 0 : minCnt;
    }
    
    void dfs(String begin, String target, String[] words, boolean[] visited, int cnt) {
        if(begin.equals(target)) {
            minCnt = Math.min(minCnt, cnt);
            return;
        }
        if(minCnt <= cnt) {
            return;
        }
        
        for(int i=0;i<words.length;i++) {
            if(!visited[i] && isValid(begin, words[i])){
                visited[i] = true;
                dfs(words[i], target, words, visited, cnt+1);
                visited[i] = false;
            }
        }
    }
    
    boolean isValid(String a, String b) {
        int cnt = 0;
        for(int i=0;i<a.length();i++) {
            if(a.charAt(i)!=b.charAt(i)) {
                cnt++;
            }
            if(cnt>=2) return false;
        }
        return true;
    }
}
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] visited = new boolean[n];
        for(int i=0;i<n;i++) {
            if(!visited[i]) {
                answer++;
                visited[i] = true;
                dfs(n, computers, visited, i);
            }
        }
        
        return answer;
    }
    
    private void dfs(int n, int[][] computers, boolean[] visited, int now) {
        for(int next=0;next<n;next++) {
            if(computers[now][next]==1 && !visited[next]) {
                visited[next] = true;
                dfs(n, computers, visited, next);
            }
        }
    }
}
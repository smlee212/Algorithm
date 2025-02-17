import java.util.*;

class Solution {
    
    private List<Integer>[] graph;
    private boolean isCycle, isDoughnut;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        int maxNode = 0;
        
        // 한 정점에서 나가고 들어오는 간선의 개수 체크
        int[][] inAndOut = new int[1000000][2];
        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            
            inAndOut[a][1]++;
            inAndOut[b][0]++;
            
            maxNode = Math.max(maxNode, Math.max(a,b));
        }
        
        // 생성한 정점 찾기
        int targetNode = 0;
        for(int i=1;i<=maxNode;i++) {
            // 나가는 간선이 2개 이상이고, 들어오는 간선이 0개
            if(inAndOut[i][1]>=2 && inAndOut[i][0]==0) {
                targetNode = i;
                break;
            }
        }
        answer[0] = targetNode;
        
        // 간선 리스트 생성
        graph = new ArrayList[maxNode+1];
        for(int i=0;i<=maxNode;i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 간선 연결
        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            
            graph[a].add(b);
        }
        
        // 생성한 정점과 연결된 노드들을 각각 탐색하여 어떤 구조인지 확인
        boolean[] visited = new boolean[maxNode+1];
        for(int next : graph[targetNode]) {
            if(!visited[next]) {     
                visited[next] = true;
                isCycle = false;
                isDoughnut = true;
                dfs(visited, next, next);
                
                if(isCycle) {
                    // 도넛
                    if(isDoughnut) {
                        answer[1]++;
                    }
                    // 8자
                    else {
                        answer[3]++;
                    }
                }
                // 막대
                else {
                    answer[2]++;
                }
            }
        }
        
        return answer;
    }
    
    private void dfs(boolean[] visited, int start, int now) {
        // 나가는 간선이 2개 이상 존재한다면 도넛이 아닌 8차
        if(graph[now].size() > 1) isDoughnut = false;
        
        for(int next : graph[now]) {    
            if(next == start) { // 시작 노드를 만난다면 순환구조
                isCycle = true;
            }
            if(!visited[next]) {
                visited[next] = true;
                dfs(visited, start, next);
            }
        }
    }    
}
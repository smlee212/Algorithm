import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        int[][] graph = new int[n+1][n+1];
        
        for(int[] result : results) {
            graph[result[0]][result[1]] = 1;
            graph[result[1]][result[0]] = -1;
        }
        
        // for(int i=1;i<=n;i++) {
        //     System.out.println(Arrays.toString(graph[i]));
        // }
        
        boolean isChange = false;
        boolean[][] visited = new boolean[n+1][n+1];
        while(!isChange) {
            // A->B 이고 B->C 이면 A->C
            for(int A=1;A<=n;A++) {
                for(int B=1;B<=n;B++) {
                    if(graph[A][B] == 1 && !visited[A][B]) {
                        isChange = true;
                        visited[A][B] = true;                        
                        dfs(graph, visited, A, B, 1);
                    }
                }
            }
            
            for(int C=1;C<=n;C++) {
                for(int B=1;B<=n;B++) {
                    if(graph[C][B] == -1 && !visited[C][B]) {
                        isChange = true;
                        visited[C][B] = true;                        
                        dfs(graph, visited, C, B, -1);
                    }
                }
            }
            
        }
//         System.out.println();
        
//         for(int i=1;i<=n;i++) {
//             System.out.println(Arrays.toString(graph[i]));
//         }
        
        for(int i=1;i<=n;i++) {
            int cnt = 1;
            for(int j=1;j<=n;j++) {
                cnt += graph[i][j] != 0 ? 1 : 0;
            }
            answer += cnt == n ? 1 : 0;
        }
         
        return answer;
    }
    
    private void dfs(int[][] graph, boolean[][] visited, int start, int now, int winOrLose) {
        for(int next=1;next<graph[now].length;next++) {
            if(graph[now][next] == winOrLose && !visited[start][next]) {
                visited[start][next] = true;
                graph[start][next] = winOrLose;
                dfs(graph, visited, start, next, winOrLose);
            }
        }   
    }
}
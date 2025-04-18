import java.util.*;

class Solution {
    // 2:00
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        // n은 200이므로 n^3인 플로이드 워샬 알고리즘을 사용해도 됨
        // (s -> 합승지점) + (합승지점->A) + (합승지점->B) 의 가격 중 최소 가격을 구함
        // 합승지점은 s부터 모든 지점까지 루프를 돌려가면서 확인하기
        
        int[][] graph = new int[n+1][n+1];
        final int INF = 1000000;        
        for(int i=1;i<=n;i++) {
            Arrays.fill(graph[i],INF);
        }    
        for(int i=1;i<=n;i++) {
            graph[i][i] = 0;
        }
        
        for(int[] fare : fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        } 
        
        for(int k=1;k<=n;k++) {
            for(int i=1;i<=n;i++) {
                for(int j=1;j<=n;j++) {
                    if(graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = Math.min(graph[i][k] + graph[k][j], INF);
                    }
                }
            }
        }
        
        int minCost = Integer.MAX_VALUE;
        for(int i=1;i<=n;i++) {
            int cost = graph[s][i] + graph[i][a] + graph[i][b];
            minCost = Math.min(minCost, cost);
        }
        
        return minCost;
    }
}
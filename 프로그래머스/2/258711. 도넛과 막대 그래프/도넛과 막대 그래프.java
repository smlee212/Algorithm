import java.util.*;

class Solution {
    
    public int[] solution(int[][] edges) {        
        int N = 1000000;
        // 한 정점에서 나가고 들어오는 간선의 개수 체크
        int[] in = new int[N+1];
        int[] out = new int[N+1];
        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            
            out[a]++;
            in[b]++;
        }
        
        int targetNode = -1;
        int stick = 0;
        int eight = 0;
        
        for(int i=1;i<=N;i++) {
            // 진출 간선이 2개 이상이고
            if(out[i] >= 2) { 
                // 진입 간선이 있을 경우
                if(in[i] > 0) {
                    eight++; // 8자 모양
                }
                // 진입 간선이 없을 경우
                else {
                    targetNode = i; // 생성된 정점
                }
            }
            // 진출 간선이 없고, 진입 간선이 1개라도 있다면
            else if(out[i] == 0 && in[i] > 0) {
                stick++; // 막대 모양
                // 이때, size가 1이여도 생성된 정점으로부터 연결되어 있기 때문에 진입 간선은 존재한다
            }
        }
        
        // 생성된 정점과 연결된 노드의 개수는 전체 그래프의 개수
        return new int[]{targetNode, out[targetNode]-stick-eight, stick, eight};
    }    
}
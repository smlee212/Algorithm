import java.util.*;

class Solution {
    
    int[] parent;
    
    public int solution(int n, int[][] costs) {        
        // 각 노드의 부모 노드
        parent = new int[n];
        for(int i=0;i<n;i++) {
            parent[i] = i;
        }
        
        // 비용을 기준으로 오른차순 정렬
        Arrays.sort(costs, (o1,o2) -> o1[2] - o2[2]);
        
        int finalCost = 0;
        // 낮은 비용부터 크루스칼 알고리즘 사용 (적은 비용의 간선부터 더해나감)
        for(int[] now : costs) {
            int a = now[0];
            int b = now[1];
            int cost = now[2];
            // 사이클이 존재하지 않는 경우에만 간선을 선택
            if(find(a) != find(b)) {
                union(a, b);
                finalCost += cost;
            }
        }
        
        return finalCost;
    }
    
    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if(a > b) {
            parent[a] = b;
        }
        else {
            parent[b] = a;
        }
    }
    
    private int find(int x) {
        if(parent[x] == x) 
            return x;
        else 
            return find(parent[x]);
    }
}
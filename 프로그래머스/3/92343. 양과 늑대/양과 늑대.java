import java.util.*;

class Solution {
    
    List<List<int[]>> graph;
    int maxCntSheep = 0;
    
    // 3:10
    public int solution(int[] info, int[][] edges) {
        // graph[0] : 다음 노드, graph[1] : 양or늑대
        graph = new ArrayList<>();
        for(int i=0;i<info.length;i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], info[edge[1]]});
        }    
        
        Set<Integer> nodeList = new HashSet<>();
        nodeList.add(0);
        dfs(nodeList, 1, 0);
        
        return maxCntSheep;
    }
    
    void dfs(Set<Integer> nodeList, int cntSheep, int cntWolf) {
        if(cntSheep <= cntWolf) {
            return;
        }
        maxCntSheep = Math.max(maxCntSheep, cntSheep);
        
        for(int node : nodeList) {
            for(int[] child : graph.get(node)) {
                // 방문하지 않은 노드라면
                if(!nodeList.contains(child[0])) {
                    Set<Integer> newList = new HashSet<>(nodeList);
                    newList.add(child[0]);
                    // 그 노드가 양이라면
                    if(child[1] == 0) {
                        dfs(newList, cntSheep+1, cntWolf);
                    }
                    // 늑대라면
                    else {
                        dfs(newList, cntSheep, cntWolf+1);
                    }
                }
            }   
        }        
    }
}
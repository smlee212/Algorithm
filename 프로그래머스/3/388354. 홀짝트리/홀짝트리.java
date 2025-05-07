import java.util.*;

class Solution {
    
    // 2:53
    
    int n;
    List<List<Integer>> graph;
    int[] cntChild;
    
    public int[] solution(int[] nodes, int[][] edges) {
        for(int node : nodes) {
            n = Math.max(n, node);
        } 
        
        graph = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }
        
        cntChild = new int[n+1];
        
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            cntChild[edge[0]]++;
            cntChild[edge[1]]++;
        }
        
        int[] answer = new int[2];
        for(int root : nodes) {
            int state = bfs(root);
            if(state > 0) answer[state-1]++;        
        }
        
        return answer;
    }
    
    int bfs(int root) {
        int stateRoot = calcState(root, true);
        
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(root);
        
        Set<Integer> set = new HashSet<Integer>();
        set.add(root);
        
        while(!dq.isEmpty()) {
            int node = dq.poll();
            for(int child : graph.get(node)) {
                if(set.contains(child)) continue;
                
                int stateChild = calcState(child, false);
                if(stateRoot == stateChild) {
                    dq.add(child);
                    set.add(child);
                }
                else {
                    return -1;
                }
            }
        }
        
        return stateRoot;
    }
    
    // state => 1 : 홀짝, 2 : 역홀짝
    int calcState(int node, boolean isRoot) {
        int cnt = isRoot ? cntChild[node] : cntChild[node] - 1;
        if(node % 2 == 1) {
            return cnt % 2 == 1 ? 1 : 2;
        }
        else {
            return cnt % 2 == 1 ? 2 : 1;
        }
    }
}
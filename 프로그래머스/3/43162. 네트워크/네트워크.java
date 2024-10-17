import java.util.*;

class Solution {    
    List<List<Integer>> list;
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        // 초기화
        // list[i][j] : i 컴에 list[i][j]가 연결되어 있음
        list = new ArrayList<>();
        for(int i=0;i<n;i++){
            List<Integer> temp = new ArrayList<>();
            for(int j=0;j<n;j++) {
                if(computers[i][j]==1)
                    temp.add(j);
            }
            list.add(temp);
        }
        
        // i 컴에 연결된 네트워크 확인하기
        visited = new boolean[n]; 
        int cntNetwork = 0;
        for(int i=0;i<n;i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i);
                cntNetwork++;
            }
        }        
        
        return cntNetwork;
    }
    
    void dfs(int nowCompIndex) {
        // 현재 방문한 컴퓨터에 인접한 컴퓨터들 확인
        for(int nextCompIndex : list.get(nowCompIndex)) {
            if(!visited[nextCompIndex]) {
                visited[nextCompIndex] = true;
                dfs(nextCompIndex);
            }
        }
    }
}
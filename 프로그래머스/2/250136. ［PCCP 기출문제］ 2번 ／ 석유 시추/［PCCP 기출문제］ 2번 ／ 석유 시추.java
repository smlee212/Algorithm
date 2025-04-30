import java.util.*;

class Solution {
    //9:52
    
    // dfs로 각 영역별 번호를 매김 (1번부터)
    // 각 번호당 석유량을 표시하는 Map 생성
    // land에서 열마다 포함한 영역별 번호를 찾고, Map에서 석유량을 찾아냄
    
    int n, m;
    int oilCnt;
    int[][] numberArea;
    Map<Integer, Integer> oilCntInArea;
    int[] dy = {0,1,0,-1}, dx = {1,0,-1,0};
    
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        numberArea = new int[n][m];
        oilCntInArea = new HashMap<>();
        
        // 영역별 넘버링
        int number = 1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(land[i][j]==1 && numberArea[i][j]==0) {
                    oilCnt = 1;
                    numberArea[i][j] = number;
                    dfs(land,i,j,number);
                    
                    oilCntInArea.put(number, oilCnt);
                    number++;
                }
            }
        }
        
        // 시추관 설치
        int result = 0;
        for(int j=0;j<m;j++) {
            int sum = 0;
            Set<Integer> setArea = new HashSet<>();
            for(int i=0;i<n;i++) {
                if(numberArea[i][j]>0) setArea.add(numberArea[i][j]);
            }
            
            for(int area : setArea) {
                sum += oilCntInArea.get(area);
            }
            result = Math.max(result, sum);
        }
        
        return result;
    }
    
    void dfs(int[][] land, int y, int x, int number) {
        for(int i=0;i<4;i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny<0||nx<0||ny>=n||nx>=m) continue;
            if(land[ny][nx]==1 && numberArea[ny][nx]==0) {
                oilCnt++;
                numberArea[ny][nx] = number;
                dfs(land,ny,nx,number);
            }
        }
    }
}
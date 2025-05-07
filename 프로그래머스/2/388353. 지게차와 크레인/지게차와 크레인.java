import java.util.*;

class Solution {
    
    /*
        주어진 storage를 한겹 더 감싸서 빈 공간을 생성
        빈 공간에서 만난 컨테이너가 외각에 있는 컨테이너인 것임
    */
    
    int n, m, N, M;
    char[][] map;
    int[] dy = {-1,0,1,0}, dx = {0,1,0,-1};
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        N = n+2;
        M = m+2;
        
        map = new char[N][M];
        for(int i=0;i<N;i++) {
            Arrays.fill(map[i], '-');
        }
        
        for(int i=1;i<=n;i++) {
            char[] temp = storage[i-1].toCharArray();
            for(int j=0;j<m;j++) {
                map[i][j+1] = temp[j];
            }
        }
        
        // 요청 처리
        for(String request : requests) {
            // 단일 요청일 경우
            if(request.length() == 1) {
                boolean[][] visited = new boolean[N][M];
                visited[0][0] = true;
                singleReq(0, 0, request.charAt(0), visited);
            }
            // 반복 요청일 경우
            else {
                doubleReq(request.charAt(0));
            }
        }
        
        int answer = getRemainContainer();
        
        return answer;
    }
    
    private void singleReq(int y, int x, char request, boolean[][] visited) {
        for(int i=0;i<4;i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            if(ny<0||nx<0||ny>=N||nx>=M) continue;
            
            // 외각일 경우 dfs 이어나감
            if(map[ny][nx] == '-' && !visited[ny][nx]) {
                visited[ny][nx] = true;
                singleReq(ny, nx, request, visited);
            }
            // 외각에 접한 컨테이너일 경우 접근 처리만 해줌
            else if(map[ny][nx] == request) {                
                visited[ny][nx] = true;
                map[ny][nx] = '-';
            }
        }
    }
    
    private void doubleReq(char request) {
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] == request) {
                    map[i][j] = '-';
                }
            }
        }
    }
    
    private int getRemainContainer() {
        int cnt = 0;
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if(map[i][j] != '-') cnt++;
            }
        }
        return cnt;
    }
}
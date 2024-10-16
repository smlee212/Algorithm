import java.util.*;

class Solution {
    int N, M;
    int sy, sx, ey, ex, ly, lx;
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();        
        
        int answer = bfs(maps);
        return answer;
    }
    
    public char[][] initMaps(String[] input) {        
        char[][] maps = new char[N][M];
        for(int i=0;i<N;i++) {
            maps[i] = input[i].toCharArray();
            for(int j=0;j<M;j++) {
                if(maps[i][j]=='S') {
                    sy = i;
                    sx = j;
                }
                else if(maps[i][j]=='E') {
                    ey = i;
                    ex = j;
                }
                else if(maps[i][j]=='L') {
                    ly = i;
                    lx = j;
                }
            }
        }
        return maps;
    }
    
    public int bfs(String[] input) {
        int[][] visited = new int[N][M];
        int dy[] = {-1, 0, 1, 0}, 
            dx[] = {0, 1, 0, -1};
        char[][] maps = initMaps(input);
        Deque<Point> dq = new ArrayDeque<>();
        
        // 1. 시작 -> 레버 최단 거리 찾기
        // 시작 지점 세팅
        dq.add(new Point(sy, sx));
        visited[sy][sx] = 1;
        int firstTime = 0;
        while(!dq.isEmpty()){
            Point p = dq.peek();
            int y = p.y;
            int x = p.x;
            dq.poll();
            
            // 레버 도달시
            if(y==ly&&x==lx) {
                firstTime = visited[y][x] - 1;
                break;
            }
            
            for(int i=0;i<4;i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                if(ny<0||nx<0||ny>=N||nx>=M) continue;
                
                if(maps[ny][nx]!='X' && visited[ny][nx]==0) {
                    visited[ny][nx] = visited[y][x] + 1;
                    dq.add(new Point(ny,nx));
                }
            }
        }
        
        // 탈출 불가능 고려
        if(firstTime==0) {
            return -1;
        }
        
        // 2. 레버 -> 출구 최단 거리 찾기
        visited = new int[N][M];
        dq = new ArrayDeque<>();
        
        // 레버 지점 세팅
        dq.add(new Point(ly, lx));
        visited[ly][lx] = 1;
        int SecondTime = 0;
        
        while(!dq.isEmpty()){
            Point p = dq.peek();
            int y = p.y;
            int x = p.x;
            dq.poll();
            
            // 출구 도달시
            if(y==ey&&x==ex) {
                SecondTime = visited[y][x] - 1;
                break;
            }
            
            for(int i=0;i<4;i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                if(ny<0||nx<0||ny>=N||nx>=M) continue;
                
                if(maps[ny][nx]!='X' && visited[ny][nx]==0) {
                    visited[ny][nx] = visited[y][x] + 1;
                    dq.add(new Point(ny,nx));
                }
            }
        }
        
        // 탈출 불가능 고려
        if(SecondTime==0) {
            return -1;
        }        
     
        return firstTime + SecondTime;
    }
    
    class Point{
        public int y;
        public int x;
        public Point(int y, int x) {
            this.y=y;
            this.x=x;
        }
    }
}
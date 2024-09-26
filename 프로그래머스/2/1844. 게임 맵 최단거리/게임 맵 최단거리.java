import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    public int solution(int[][] maps) {
        return bfs(maps);
    }

    int bfs(int[][] maps){
        Deque<int[]> dq = new ArrayDeque<>();
        int n = maps.length;
        int m = maps[0].length;
        int[][] visited = new int[n][m];
        int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};

        visited[0][0] = 1;
        dq.add(new int[]{0,0});

        while(!dq.isEmpty()){
            int y = dq.peekFirst()[0];
            int x = dq.peekFirst()[1];
            dq.pop();

            if(y==n-1 && x==m-1) {
                return visited[y][x];
            }

            for(int i=0;i<4;i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny>=n||nx>=m||ny<0||nx<0||maps[ny][nx]==0) continue;

                if(visited[ny][nx]==0){
                    visited[ny][nx] = visited[y][x]+1;
                    dq.add(new int[]{ny,nx});
                }
            }
        }

        return -1;
    }
}
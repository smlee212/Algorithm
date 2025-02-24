import java.util.*;
class Solution {
    
    int N;
    boolean[][][] map;
    
    public List<int[]> solution(int n, int[][] build_frame) {
        N = n+1;
        map = new boolean[N][N][2];
        
        
        // 설치 & 삭제
        for(int[] frame : build_frame) {
            if(frame[3] == 1) add(frame);
            else remove(frame);
        }
        
        // 최종 구조물 확인
        List<int[]> answer = new ArrayList<>();
        for(int j=0;j<N;j++) {
            for(int i=0;i<N;i++) {
                for(int k=0;k<2;k++) {
                    if(map[i][j][k]) answer.add(new int[]{j,i,k});
                }
            }
        }
        
        return answer;
    }
    
    // 설치
    private void add(int[] frame) {
        // 1. 기둥 설치
        if(frame[2] == 0) 
            add_pillars(frame[1], frame[0]);
        // 2. 보 설치
        else 
            add_beams(frame[1], frame[0]);
    }
    
    // 기둥 설치
    private void add_pillars(int y, int x) {
        // 기둥을 설치할 수 있는지 확인
        if(!isPillars(y,x)) return;
        
        map[y][x][0] = true;
    }
    
    private boolean isPillars(int y, int x) {
        // 바닥 위에 있는지
        if(y==0) {
            return true;
        }
        // 보의 한쪽 끝 부분 위에 있는지
        else if((x-1>=0 && map[y][x-1][1]) || map[y][x][1]) {
            return true;
        }
        // 다른 기둥 위에 있는지
        else if(y-1>=0 && map[y-1][x][0]) {
            return true;
        }
        return false;
    }
    
    // 보 설치
    private void add_beams(int y, int x) {
        // 보를 설치할 수 있는지 확인
        if(!isBeams(y,x)) return;
        
        map[y][x][1] = true;
    }
    
    private boolean isBeams(int y, int x) {
        // 한쪽 끝 부분이 기둥 위에 있는지
        if(y-1>=0 && (map[y-1][x][0] || (x+1<N && map[y-1][x+1][0]))) {
            return true;
        }
        // 양쪽 끝 부분이 다른 보와 동시에 연결되어 있는지
        else if((x-1>=0 && map[y][x-1][1]) && (x+1<N && map[y][x+1][1])) {
            return true;
        }
        return false;
    }
    
    // 삭제
   private void remove(int[] frame) {
        // 1. 기둥 삭제
        if(frame[2] == 0) 
            remove_pillars(frame[1], frame[0]);
        // 2. 보 삭제
        else 
            remove_beams(frame[1], frame[0]);
   } 

    private void remove_pillars(int y, int x) {
        if(!map[y][x][0]) return;
        map[y][x][0] = false;
        
        // 위 기둥 확인
        if(y+1<N && map[y+1][x][0] && !isPillars(y+1,x)) {
            map[y][x][0] = true;
            return;
        }
        // 위 왼쪽 보 확인
        if(x-1>=0 && y+1<N && map[y+1][x-1][1] && !isBeams(y+1,x-1)) {
            map[y][x][0] = true;
            return;
        }
        // 위 오른쪽 보 확인
        if(y+1<N && map[y+1][x][1] && !isBeams(y+1,x)) {
            map[y][x][0] = true;
            return;
        }
    }

    private void remove_beams(int y, int x) {
        if(!map[y][x][1]) return;
        map[y][x][1] = false;
        
        // 왼쪽 보 확인
        if(x-1>=0 && map[y][x-1][1] && !isBeams(y,x-1)) {
            map[y][x][1] = true;
            return;
        }
        // 오른쪽 보 확인
        if(x+1<N && map[y][x+1][1] && !isBeams(y,x+1)) {
            map[y][x][1] = true;
            return;
        }
        // 위 기둥 확인
        if(map[y][x][0] && !isPillars(y,x)) {
            map[y][x][1] = true;
            return;
        }
        // 오른쪽 기둥 확인
        if(x+1<N && map[y][x+1][0] && !isPillars(y,x+1)) {
            map[y][x][1] = true;
            return;
        }
    }
    
}
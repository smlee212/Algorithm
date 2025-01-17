import java.util.*;

class Solution {
    
    int N;
    int[] moveCntStick;
    int minMove;
    
    public int solution(String name) {
        N = name.length();
        moveCntStick = new int[N];
        minMove = Integer.MAX_VALUE;
        
        int totalMoveCnt = 0;
        for(int i=0;i<N;i++) {
            moveCntStick[i] = Math.min((name.charAt(i) - 'A'),('Z' + 1 - name.charAt(i)));
            totalMoveCnt += moveCntStick[i];
        }
        
        boolean[] visited = new boolean[N];
        visited[0] = true;
        char[] changeName = name.toCharArray();
        changeName[0] = 'A';
        dfs(changeName, visited, 0, moveCntStick[0], totalMoveCnt - moveCntStick[0]);     
        
        return minMove;
    }
    
    public void dfs(char[] changeName, boolean[] visited, int now, int sumMoveCnt, int remainMoveCnt) {
        if(remainMoveCnt == 0) {
            minMove = Math.min(minMove, sumMoveCnt);
            return;
        }
        
        int r = (now + 1) % N;
        int l = (N + now - 1) % N;
        
        if(!visited[r]) {
            if(changeName[r] == 'A') {
                visited[r] = true;
                dfs(changeName, visited, r, sumMoveCnt + 1, remainMoveCnt);                
            }
            else {
                char temp = changeName[r];
                boolean[] newVisited = new boolean[N];
                newVisited[r] = true;
                changeName[r] = 'A';
                dfs(changeName, newVisited, r, sumMoveCnt + 1 + moveCntStick[r], remainMoveCnt - moveCntStick[r]);    
                changeName[r] = temp;
            }
        }
        if(!visited[l]) {
            if(changeName[l] == 'A') {
                visited[l] = true;
                dfs(changeName, visited, l, sumMoveCnt + 1, remainMoveCnt);                
            }
            else {
                char temp = changeName[l];
                boolean[] newVisited = new boolean[N];
                newVisited[l] = true;
                changeName[l] = 'A';
                dfs(changeName, newVisited, l, sumMoveCnt + 1 + moveCntStick[l], remainMoveCnt - moveCntStick[l]);
                changeName[l] = temp;
            }
        }
    }
}
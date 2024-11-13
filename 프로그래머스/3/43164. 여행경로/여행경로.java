import java.util.*;

class Solution {
    
    int n;
    boolean[] visited;
    String[] temp;
    List<String> result = new ArrayList<>();
    
    public List<String> solution(String[][] tickets) {        
        Arrays.sort(tickets, (o1,o2) -> {
            if(o1[0].equals(o2[0])) {
                return o1[1].compareTo(o2[1]);
            }
            else
                return o1[0].compareTo(o2[0]);
        });
        
        n = tickets.length;
        visited = new boolean[n];
        temp = new String[n+1];
        
        temp[0] = "ICN";
        dfs(tickets, "ICN", 1);        
        
        return result;
    }
    
    void dfs(String[][] tickets, String start, int cnt) {
        if(result.size() > 0) {
            return;
        }
        else if(cnt > n) {
            for(String str : temp) {
                result.add(str);
            }
            return;
        }
        
        for(int i=0;i<n;i++) {
            if(!visited[i] && tickets[i][0].equals(start)) {
                temp[cnt] = tickets[i][1];
                visited[i] = true;
                dfs(tickets, tickets[i][1], cnt+1);
                visited[i] = false;
            }
        }
    }
}
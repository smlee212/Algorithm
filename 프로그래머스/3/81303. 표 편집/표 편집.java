import java.util.*;

class Solution {
    
    public String solution(int n, int k, String[] cmd) {     
        Stack<Integer> deleteRecord = new Stack<>();             
        int size = n;
        
        for(int i=0;i<cmd.length;i++) {
            char c = cmd[i].charAt(0);
            if(c == 'U') {
                k -= Integer.parseInt(cmd[i].substring(2));
            }
            else if(c == 'D') {
                k += Integer.parseInt(cmd[i].substring(2));
            }
            else if(c == 'C') {
                deleteRecord.add(k);
                size--;
                if(k == size) k--;
            }
            else if(c == 'Z') {
                if(deleteRecord.pop() <= k) k++;
                size++;
            }
        } 
        
        StringBuilder answer = new StringBuilder();
        for(int i=0;i<size;i++)
            answer.append("O");
        
        while(!deleteRecord.isEmpty())
            answer.insert(deleteRecord.pop().intValue(), "X");
        
        return answer.toString();
    }
}
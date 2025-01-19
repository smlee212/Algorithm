import java.util.*;

class Solution {
    public int solution(String s) {
        int len = s.length();
        int answer = len;
        
        for(int size=1;size<=len/2;size++) {
            String zipStr = zipString(s, size);
            answer = Math.min(answer, zipStr.length());
        }
        
        return answer;
    }
    
    public String zipString(String s, int size) {
        String target = s.substring(0,size);
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<s.length();i+=size) {
            String now = "";
        
            if(i+size > s.length()) now = s.substring(i,s.length()); 
            else now = s.substring(i,i+size);
            
            if(now.equals(target)) {
                cnt++;
            }
            else {
                if(cnt > 1) sb.append(cnt);
                sb.append(target);
                
                target = now;
                cnt = 1;
            }
        }
        
        if(cnt>1) sb.append(cnt);
        sb.append(target);
        
        return sb.toString();
    }
}
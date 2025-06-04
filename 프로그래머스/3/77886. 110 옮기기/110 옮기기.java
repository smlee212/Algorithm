import java.util.*;

class Solution {
    // 12:18
    
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        Stack<Character> stack = new Stack<>();
        
        for(int i=0;i<s.length;i++) {
            stack.clear();
            char[] c = s[i].toCharArray();
            int cnt110 = 0;
            
            for(int j=0;j<c.length;j++) {
                if(c[j] == '1') {
                    stack.push('1');
                }
                else {
                    if(stack.size() >= 2) {
                        char second = stack.pop();
                        char first = stack.pop();
                        if(first == '1' && second == '1') {
                            cnt110++;
                        }
                        else {
                            stack.push(first);
                            stack.push(second);
                            stack.push('0');
                        }
                    }
                    else {
                        stack.push('0');
                    }
                }
            }
            
            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            String str = sb.reverse().toString();
            String result = "";
            sb.setLength(0);
            while(cnt110-->0) {
                sb.append("110");
            }
            
            for(int j=str.length()-1;j>=0;j--) {
                if(str.charAt(j) == '0') {
                    result = str.substring(0,j+1) + sb.toString() + str.substring(j+1, str.length());
                    break;
                }
            }
            if(result.length()==0) {
                result = sb.toString() + str;
            }
            
            answer[i] = result;
        }
        
        return answer;
    }
}
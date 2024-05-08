import java.util.*;
class Solution {
    boolean solution(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        for(char c : s.toCharArray()) {
            if(c == '(') {
                dq.push(c);
            }
            else if(!dq.isEmpty()) {
                dq.pop();
            }
            else {
                return false;
            }
        }

        return dq.isEmpty();
    }
}
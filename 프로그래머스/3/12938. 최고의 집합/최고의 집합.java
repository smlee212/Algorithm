import java.util.*;

class Solution {
    public List<Integer> solution(int n, int s) {
        List<Integer> list = new ArrayList<>();        
        
        int a = s / n;
        int b = s % n;
        
        if(a == 0) {
            list.add(-1);
        }
        else if(b == 0) {
            for(int i=0;i<n;i++) {
                list.add(a);
            }
        }
        else {
            for(int i=0;i<n-b;i++) {
                list.add(a);
            }
            for(int i=0;i<b;i++) {
                list.add(a+1);
            }
        }
        
        return list;
    }
}
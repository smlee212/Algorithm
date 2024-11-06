import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>();
        
        Set<Integer> set = new HashSet<>();
        set.add(N);
        dp.add(set);
        
        if(N == number) {
            return 1;
        }
        
        int NN = N;
        for(int i=2;i<=8;i++) {
            set = new HashSet<>();
            NN = NN * 10 + N;
            set.add(NN);
            for(int j=1;j<i;j++) {
                int z = i-j;
                
                Set<Integer> A = dp.get(j-1);
                Set<Integer> B = dp.get(z-1);
                
                for(Integer a : A) {
                    for(Integer b : B) {
                        calc(set,a,b);
                    }
                }
            }
            
            if(set.contains(number)) {
                return i;
            }
            
            dp.add(set);
        }
        
        return -1;
    }
    
    void calc(Set<Integer> set, Integer a, Integer b) {            
        // a+b
        set.add(a + b);
        
        // a-b
        set.add(a - b);
        
        // a*b
        set.add(a * b);
        
        // a/b
        if(b!=0) 
            set.add(a / b);
    }
}
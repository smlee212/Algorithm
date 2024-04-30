import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int num : nums) {
            hs.add(num);
        }
        
        return (nums.length / 2) > hs.size() ? hs.size() : (nums.length / 2);
    }
}
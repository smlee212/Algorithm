class Solution {
    public String solution(String s) {
        String[] arr = s.split(" ");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for(String str : arr) {
            int num = Integer.parseInt(str);
            max = Math.max(max, num);
            min = Math.min(min, num);            
        }
        
        return min+" "+max;
    }
}
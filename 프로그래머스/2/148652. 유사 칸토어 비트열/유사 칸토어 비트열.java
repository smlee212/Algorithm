class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        
        for(long i=l-1;i<r;i++) {
            answer += find(i);
        }
        
        return answer;
    }
    
    private int find(long x) {
        // 5개의 그룹으로 나누었을 때, 2번째에 있다면 0
        if(x%5==2) return 0;
        // 2번째인적이 없다면 1
        if(x==0) return 1;
        
        return find(x/5);
    }   
}
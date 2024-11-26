import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        
        int[] wanho = scores[0];
        
        // 1. a마다 최소값/최대값 구하기, [a]는 a 이상의 점수에서 나올 수 있는 최소/최대값        
        int[] maxBByA = new int[100001];
        for(int[] score : scores) {
            int a = score[0];
            int b = score[1];
            maxBByA[a] = Math.max(maxBByA[a], b);
        }        
        
        for(int i=99999;i>=0;i--) {
            maxBByA[i] = Math.max(maxBByA[i], maxBByA[i+1]);
        }        
        
        List<int[]> arr = new ArrayList<>();
        boolean check = false;
        // 비교
        for(int[] score : scores) {
            int a = score[0];
            int b = score[1];
            
            if(a == 100000 || b >= maxBByA[a+1]) {
                arr.add(new int[]{a,b});
                if(wanho[0] == a && wanho[1] == b) {
                    check = true;
                }
            }
        }    
        
        if(!check)
            return -1;
        
        // 2. 석차 계산        
        Map<Integer,Integer> map = new HashMap<>();
        for(int[] a : arr) {
            int sum = a[0] + a[1];
            map.put(sum, map.getOrDefault(sum, 0)+1);            
        }
        
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, Collections.reverseOrder());
        int rank = 1;
        int wanhoTotal = wanho[0] + wanho[1];
        for(int i : list) {
            if(wanhoTotal == i) {
                break;
            }
            // System.out.println(i+", "+map.get(i));
            rank += map.get(i);
        }        
        
        return rank;
    }
}
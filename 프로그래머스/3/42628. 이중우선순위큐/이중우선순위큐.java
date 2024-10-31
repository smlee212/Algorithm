import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        LinkedList<Integer> list = new LinkedList<>();
            
        for(String s : operations) {
            if(s.charAt(0)=='I') {
                int num = Integer.parseInt(s.substring(2));
                // 숫자 삽입
                int index = 0;
                for(Integer e : list) {
                    if(e < num) {
                        index++;
                    }
                    else {
                        break;
                    }
                }
                list.add(index, num);
            }
            else if(!list.isEmpty()) {
                // 최소값 삭제
                if(s.charAt(2)=='-') {
                    list.removeFirst();
                }
                // 최대값 삭제
                else {
                    list.removeLast();
                }
            }
        }
        
        int[] answer = new int[2];
        if(list.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        }
        else {
            answer[0] = list.get(list.size()-1);
            answer[1] = list.get(0);
        }
        return answer;
    }
    
    void print(LinkedList<Integer> list) {
        for(Integer i : list) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
    
    
}
import java.util.*;

class Solution {
    
    // 2:20
    
    public int[] solution(String[] operations) {      
        LinkedList<Integer> list = new LinkedList<>();
        
        for(String op : operations) {
            String[] temp = op.split(" ");
            int num = Integer.parseInt(temp[1]);
            if(temp[0].equals("I")) {
                int index = 0;
                for(Integer item : list) {
                    if(item < num) {
                        index++;
                    }
                    else {
                        break;
                    }
                }
                list.add(index, num);
            }
            else if(!list.isEmpty()){
                if(num < 0) {
                    list.removeFirst();
                }
                else {
                    list.removeLast();
                }
            }
        }
        
        if(list.isEmpty()) {
            return new int[]{0,0};
        }
        else {
            return new int[]{list.get(list.size()-1), list.get(0)};
        }
    }
}
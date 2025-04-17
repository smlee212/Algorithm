import java.util.*;
class Solution {
    // 11:45
    
    
    public String[] solution(String[] expressions) {
        int maxValue = 0;
        boolean isCorrect = false;
        int cntX = 0;
        
        for(int i=0;i<expressions.length;i++) {
            String[] item = expressions[i].split(" ");
            String A = item[0].length() == 1 ? "0"+item[0] : item[0];
            char expression = item[1].charAt(0);
            String B = item[2].length() == 1 ? "0"+item[2] : item[2];
            String C = item[4];
            
            maxValue = Math.max(maxValue, Math.max(A.charAt(0)-'0', A.charAt(1)-'0') + 1);
            maxValue = Math.max(maxValue, Math.max(B.charAt(0)-'0', B.charAt(1)-'0') + 1);
            
            if(C.equals("X")) {
                cntX++;
                continue;
            }
            
            for(int j=0;j<C.length();j++) {
                maxValue = Math.max(maxValue, (C.charAt(j)-'0')+1);
            }
            
            // 덧셈일때
            if(expression == '+') {
                int sum0 = (A.charAt(1)-'0') + (B.charAt(1)-'0'); // 10^0 자리 수
                int sum10 = (A.charAt(0)-'0') + (B.charAt(0)-'0'); // 10^1 자리 수
                    
                // 0 자리수에서 덧셈으로 +1이 된 경우
                if(sum0 != C.charAt(C.length()-1)-'0') {
                    maxValue = sum0 - (C.charAt(C.length()-1)-'0');
                    System.out.println("1 ! " + sum0 + " " + (C.charAt(C.length()-1)-'0'));
                    isCorrect = true;
                    break;
                }
                // 10 자리수에서 덧셈으로 +1이 된 경우
                else if(C.length() > 2) {
                    maxValue = sum10 - (C.charAt(1)-'0');
                    System.out.println("2 ! " + maxValue);
                    isCorrect = true;
                    break;                    
                }
                // 변화가 없을 경우
                else {
                    maxValue = Math.max(maxValue, sum0);
                }
            }
            // 뺄셈일때
            else {
                int dif0 = (A.charAt(1)-'0') - (B.charAt(1)-'0'); // 10^0 자리 수
                int dif10 = (A.charAt(0)-'0') - (B.charAt(0)-'0'); // 10^1 자리 수
                
                // 0 자리수에서 뺄셈으로 음수가 된 경우
                if(dif0 < 0) {
                    maxValue = (C.charAt(C.length()-1)-'0') - dif0;
                    System.out.println("3 ! " + maxValue);
                    isCorrect = true;
                    break;
                }
            }
        }
        
        List<String> answer = new ArrayList<>();
        int index = 0;
        
        for(int i=0;i<expressions.length;i++) {
            String[] item = expressions[i].split(" ");
            String C = item[4];
            
            if(!C.equals("X")) continue;
            
            char expression = item[1].charAt(0);
            
            if(isCorrect) {
                int A = Integer.parseInt(item[0], maxValue);
                int B = Integer.parseInt(item[2], maxValue);
                C = Integer.toString(expression=='+'?A+B:A-B, maxValue);
                answer.add(item[0] + " " + item[1] + " " + item[2] + " = " + C);
            }
            else {
                int A = Integer.parseInt(item[0], maxValue);
                int B = Integer.parseInt(item[2], maxValue);
                int c = expression == '+' ? A+B : A-B;
                String temp = Integer.toString(c, maxValue);
                boolean isEquals = true;
                // 9진법까지 변환해보며 비교
                for(int j=maxValue+1;j<10;j++) {
                    A = Integer.parseInt(item[0], j);
                    B = Integer.parseInt(item[2], j);
                    c = expression == '+' ? A+B : A-B;
                    if(!temp.equals(Integer.toString(c, j))) {
                        isEquals = false;
                        break;
                    }
                }
                
                answer.add(item[0] + " " + item[1] + " " + item[2] + " = " + (isEquals?temp:"?"));
            }
        }
        
        return answer.toArray(new String[answer.size()]);
    }
}
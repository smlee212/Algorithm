import java.io.*;
import java.util.*;

public class Main {
    
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        boolean isPPAP = true;
        for(char c : input.toCharArray()) {
            // 현재 ...AP인 상황일 경우
            if(!stack.isEmpty() && c=='P' && stack.peek()=='A') {
                if(stack.size()<3) {
                    isPPAP = false;
                    break;
                }
                int A = stack.pop();
                int p2 = stack.pop();
                int p1 = stack.pop();
                if(p2=='P' && p1=='P') {
                    stack.add('P');
                }
                else {
                    isPPAP = false;
                    break;
                }
            }
            else {
                stack.add(c);
            }
        }

        if(stack.size()==1 && stack.peek()=='P' && isPPAP) {
            System.out.println("PPAP");
        }
        else {
            System.out.println("NP");
        }
    }
}

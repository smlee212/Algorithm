import java.io.*;
import java.util.*;

public class Main {

    private static char[] map;

    public static void main(String[] args) throws IOException {
        read();
        func();
    }

    private static void func() {
        Stack<Character> stack = new Stack<>();

        int left = 0, right = 0, total = 0;
        int result = 0;

        for(char c : map) {
            if(c == '(') {
                left++;
                total++;
            }
            else {
                right++;
                total--;
            }

            if(total == 1) {
                left = 0;
            }
            if(total == -1) {
                result = right;
                break;
            }
        }

        if(total==2) {
            result = left;
        }

        System.out.println(result);
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = br.readLine().toCharArray();
    }
}
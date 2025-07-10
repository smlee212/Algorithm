import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static char[] map1, map2, target;

    public static void main(String[] args) throws IOException {
        read();
        func();
    }

    private static void func() {
        // 0번 스위치를 누르지 않았다고 가정
        int result = greedy(map1);
        int answer = result >= 0 ? result : -1;

        // 0번 스위치를 눌렀다고 가정
        push(map2, 0);
        result = greedy(map2); // 0번을 눌렀기 때문에 +1
        result = result == -1 ? -1 : result+1;
        if(result >= 0) {
            if(answer >= 0) answer = Math.min(answer, result);
            else answer = result;
        }

        System.out.println(answer);
    }

    private static int greedy(char[] map) {
        int count = 0;

        // i-1번째가 다를 경우 i번째 버튼 누르기
        for(int i=1;i<N;i++) {
            if(map[i-1] != target[i-1]) {
                push(map, i);
                count++;
            }
        }

        for(int i=0;i<N;i++) {
            if(map[i]!=target[i]) {
                return -1;
            }
        }
        return count;
    }

    private static void push(char[] map, int num) {
        for(int i=num-1;i<=num+1;i++) {
            if(i>=0 && i<N) {
                if(map[i]=='0') map[i]='1';
                else map[i]='0';
            }
        }
    }

    private static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map1 = br.readLine().toCharArray();
        map2 = Arrays.copyOf(map1, map1.length);
        target = br.readLine().toCharArray();
    }
}
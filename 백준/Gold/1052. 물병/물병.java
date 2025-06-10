import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if(n <= k) {
            System.out.println(0);
        }
        else {
            String number = Integer.toBinaryString(n);
            int size = number.length();
            int cnt1 = 0;
            int lastIdx = -1;
            for (int i = 0; i < number.length(); i++) {
                if (number.charAt(i) == '1') {
                    cnt1++;
                    if (cnt1 == k) lastIdx = i;
                }
            }

            // 1의 개수가 동일하거나 k보다 적을때
            if (cnt1 <= k) {
                System.out.println('0');
            }
            // 1의 개수가 넘쳐날 때
            else {
                StringBuilder temp = new StringBuilder();
                for (int i = lastIdx + 1; i < size; i++) {
                    if (number.charAt(i) == '1') {
                        temp.append(0);
                    } else {
                        temp.append(1);
                    }
                }
                int need = Integer.parseInt(temp.toString(), 2);
                System.out.println(need + 1);
            }
        }
    }
}
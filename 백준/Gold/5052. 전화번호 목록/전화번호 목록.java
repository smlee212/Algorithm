import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int tc=0;tc<t;tc++) {
            int n = Integer.parseInt(br.readLine());

            String[] number = new String[n];
            for(int i=0;i<n;i++) {
                number[i] = br.readLine();
            }

            Arrays.sort(number, (o1, o2)-> {
                int index = 0;
                while(index < o1.length() && index < o2.length()) {
                    if(o1.charAt(index) != o2.charAt(index)) {
                        return o1.charAt(index) - o2.charAt(index);
                    }
                    index++;
                }
                return o1.length() - o2.length();
            });

            boolean isValid = true;
            for(int i=1;i<n;i++) {
                String A = number[i-1];
                String B = number[i];

                if(B.startsWith(A)) {
                    isValid = false;
                    break;
                }
            }

            sb.append(isValid ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
    }
}
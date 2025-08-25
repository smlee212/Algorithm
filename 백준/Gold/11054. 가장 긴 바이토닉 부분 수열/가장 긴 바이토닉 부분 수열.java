import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int A = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] map = new int[A];
        for(int i=0;i<A;i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        int[] up = new int[A];
        int[] down = new int[A];

        up[0] = 1;
        for(int i=1;i<A;i++) {
            int temp = 0;
            for(int j=0;j<i;j++) {
                if(map[j] < map[i])
                    temp = Math.max(temp, up[j]);
            }
            up[i] = temp + 1;
        }

        down[A-1] = 1;
        for(int i=A-2;i>=0;i--) {
            int temp = 0;
            for(int j=A-1;j>i;j--) {
                if(map[j] < map[i])
                    temp = Math.max(temp, down[j]);
            }
            down[i] = temp + 1;
        }


        int max = 0;
        for(int i=0;i<A;i++) {
            max = Math.max(max, up[i]+down[i]-1);
        }
        System.out.println(max);
    }
}
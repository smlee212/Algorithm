import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] array = new int[101];
        for(int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine());
            array[Integer.parseInt(st.nextToken())]++;
            array[Integer.parseInt(st.nextToken())]--;
        }

        int x = 0;
        int sum = 0;
        for(int i=1;i<=100;i++){
            x += array[i];
            if(x == 1)
                sum += A;
            else if(x == 2)
                sum += 2 * B;
            else if(x == 3)
                sum += 3 * C;
        }

        System.out.println(sum);
    }
}
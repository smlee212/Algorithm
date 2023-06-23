import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder(br.readLine());
        String str = sb.toString();
        sb.reverse();

        if(str.contentEquals(sb)){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }
}
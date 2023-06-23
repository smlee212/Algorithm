import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] array = new int[26];
        int n = Integer.parseInt(br.readLine());

        for(int i=0;i<n;i++){
            array[br.readLine().charAt(0) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<26;i++){
            if(array[i] >= 5){
                sb.append((char)('a' + i));
            }
        }

        System.out.println(sb.toString().isEmpty() ? "PREDAJA" : sb);
    }
}
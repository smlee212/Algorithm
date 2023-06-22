import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();
        int[] array = new int[26];
        for(char c : str){
            array[c-'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<26;i++){
            sb.append(array[i]).append(" ");
        }

        System.out.println(sb);
    }
}
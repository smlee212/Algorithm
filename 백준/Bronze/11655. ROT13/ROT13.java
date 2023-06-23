import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length;i++){
            char c = str[i];
            if(c >= 'a' && c <= 'z'){
                c += 13;
                if(c > 'z'){
                    c =(char)(c - 'z' + 'a' - 1);
                }
                str[i] = c;
            }
            else if(c >= 'A' && c <= 'Z'){
                c += 13;
                if(c > 'Z'){
                    c =(char)(c - 'Z' + 'A' - 1);
                }
                str[i] = c;
            }
            sb.append(str[i]);
        }

        System.out.println(sb);
    }
}
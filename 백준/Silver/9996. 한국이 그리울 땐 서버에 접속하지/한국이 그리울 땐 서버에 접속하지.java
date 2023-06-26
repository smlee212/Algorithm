import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String pattern = br.readLine();
        String[] pArr = pattern.split("\\*");

        for(int n=0;n<N;n++){
            String str = br.readLine();

            if(str.length() < pArr[0].length() + pArr[1].length()){
                System.out.println("NE");
                continue;
            }

            String front = str.substring(0, pArr[0].length());
            String back = str.substring(str.length() - pArr[1].length());

            if(front.equals(pArr[0]) && back.equals(pArr[1])){
                System.out.println("DA");
            }
            else{
                System.out.println("NE");
            }
        }
    }
}
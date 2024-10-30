import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] number = new int[10];
        String input = br.readLine();

        int sum = 0;
        for(char c : input.toCharArray()) {
            number[c - '0']++;
            sum += c - '0';
        }

        StringBuilder result = new StringBuilder();
        if(number[0]==0 || sum%3!=0) {
            result.append("-1");
        }
        else{
            for(int i=0;i<number.length;i++) {
                for(int j=0;j<number[i];j++) {
                    result.append(i);
                }
            }
            result.reverse();
        }

        System.out.println(result);
    }
}
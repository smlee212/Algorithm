import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[] array;
    static int targetCnt;
    static boolean[] visited;
    static String result;
    static int nowCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String input;
        while ((input = br.readLine())!=null){
            st = new StringTokenizer(input);
            array = st.nextToken().toCharArray();
            targetCnt = Integer.parseInt(st.nextToken());
            visited = new boolean[array.length];
            result = "";
            nowCnt = 0;

            permutation(new char[array.length], 0);
            if(result.equals("")) result = "No permutation";

            StringBuilder sb = new StringBuilder();
            for(char c : array)
                sb.append(c);
            sb.append(" ").append(targetCnt).append(" = ").append(result);
            System.out.println(sb);
        }
    }

    static void permutation(char[] arrayPermutation, int nowIndex){
        if(!result.equals("")){
            return;
        }

        if(nowIndex == array.length) {
            nowCnt++;
            if(nowCnt == targetCnt) {
                for(char c : arrayPermutation) result += c;
                return;
            }
            else if(nowCnt > targetCnt) {
                result = "No permutation";
                return;
            }
            return;
        }

        for(int i=0;i<array.length;i++) {
            if(!visited[i]) {
                arrayPermutation[nowIndex] = array[i];
                visited[i] = true;
                permutation(arrayPermutation, nowIndex+1);
                visited[i] = false;
            }
        }
    }
}
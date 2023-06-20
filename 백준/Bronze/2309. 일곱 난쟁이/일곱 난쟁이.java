import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] heights = new int[9];
        int sum = 0;
        for(int i=0;i<9;i++){
            heights[i] = Integer.parseInt(br.readLine());
            sum += heights[i];
        }

        Arrays.sort(heights);

        int diff = sum - 100;
        int indexA = 0, indexB = 0;
        
        for(int i=0;i<8;i++){
            indexA = i;
            int b = diff - heights[i];
            for(int j=i+1;j<9;j++){
               if(b > heights[j]) continue;
               if(b == heights[j]){
                   indexB = j;
               }
               break;
            }
            if(indexB > 0) break;
        }

        for(int i=0;i<9;i++){
            if(i != indexA && i != indexB){
                System.out.println(heights[i]);
            }
        }
    }
}
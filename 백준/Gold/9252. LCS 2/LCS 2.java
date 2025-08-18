import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();
        int aSize = a.length;
        int bSize = b.length;

        int[][] lcs = new int[aSize+1][bSize+1];

        for(int i=1;i<=aSize;i++) {
            for(int j=1;j<=bSize;j++) {
                if(a[i-1]==b[j-1]) {
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                }
                else {
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }

        int maxL = lcs[aSize][bSize];
        System.out.println(maxL);

        StringBuilder sb = new StringBuilder();

        int i = aSize;
        int j = bSize;
        while(i>0 && j>0) {
            if(lcs[i][j] == lcs[i-1][j]) {
                i--;
            }
            else if(lcs[i][j] == lcs[i][j-1]) {
                j--;
            }
            else {
                sb.append(a[i-1]);
                i--;j--;
            }
        }

        System.out.println(sb.reverse());
    }
}
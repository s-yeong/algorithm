import java.util.ArrayList;
import java.util.Scanner;

public class Main4 {

    public static ArrayList<String> solution(int n, String[] strArr) {

        ArrayList<String> answer = new ArrayList<>();
        for(String x : strArr) {
//            String tmp = new StringBuilder(x).reverse().toString();
//            answer.add(tmp);

            char[] s = x.toCharArray();
            int lt = 0, rt = x.length() - 1;

            while(lt<rt) {
                char tmp = s[lt];
                s[lt] = s[rt];
                s[rt] = tmp;
                lt++;
                rt--;
            }
            String tmp = String.valueOf(s);
            answer.add(tmp);
        };
        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        int n = sc.nextInt();
        String[] strArr = new String[n];

        for(int i=0; i<n; i++) {
            strArr[i] = sc.next();
        }

        for(String x : solution(n, strArr)) {
            System.out.println(x);
        }

    }
}
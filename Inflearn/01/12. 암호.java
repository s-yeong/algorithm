import java.util.Scanner;

public class Main12 {

    public static String solution(int n, String str) {

        String answer = "";
        str = str.replace('#', '1').replace('*','0');

//        int j=0;
        for(int i=0; i<n; i++) {

//            String tmp = str.substring(j, j+7);
            String tmp = str.substring(0, 7);
            int num = Integer.parseInt(tmp, 2);
            answer += (char)num;
//            j += 7;
            str = str.substring(7);
        }



        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        int n = sc.nextInt();
        String str = sc.next();


        System.out.println(solution(n, str));

    }
}
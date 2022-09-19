import java.util.Scanner;

public class Main3 {

    public static String solution(String str) {
        String answer = "";
        int m = Integer.MIN_VALUE;  // 최대 값으로 계속 갱신해야하니까, 가장 작은 값으로 초기화
        int pos;
        while((pos=str.indexOf(' '))!=-1) { // ' '가 발견되지 않을 때 까지
            String tmp = str.substring(0, pos); // 0 ~ pos-1

            int len = tmp.length();
            if(len > m) {
                m = len;
                answer = tmp;
            }   // 마지막 단어는 포함X
            str = str.substring(pos+1);
        }
        if(str.length() > m) answer = str;

//        String[] split = str.split(" ");

//        for(int i=0; i< split.length; i++) {
//            if(answer.length() < split[i].length()) {
//                answer = split[i];
//            }
//        }

        return answer;
    }

    public static void main(String[] args){

        Scanner sc =new Scanner(System.in);

        String str = sc.nextLine();

        System.out.println(solution(str));
    }
}
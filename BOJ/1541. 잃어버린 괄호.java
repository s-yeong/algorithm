import java.util.*;

public class Main {

    public static void main(String[] args) {


        // 최소값이 되도록 괄호를 치고, 그 때 최소값을 구하라

        // 0~9, +, -
        // 처음과 마지막 문자 숫자(0으로 시작할 수 있음)
        // 연속해서 두 개이상의 연산자X
        // 숫자는 최대 5자리

        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        String[] subSplit = str.split("-");

        ArrayList<Integer> answer = new ArrayList<>();

        for(int i=0; i<subSplit.length; i++) {

            String[] addSplit = subSplit[i].split("\\+");
            int sum = 0;
            for(int j=0; j< addSplit.length; j++) {
                sum += Integer.parseInt(addSplit[j]);
            }

            answer.add(sum);
        }

        int answerSum = answer.get(0);

        for(int i=1; i<answer.size(); i++) {
            answerSum -= answer.get(i);
        }

        System.out.println(answerSum);



    }
}
import java.util.*;

class Student {
    int sex;
    int num;

    public Student(int sex, int num) {
        this.sex = sex;
        this.num = num;
    }
}


public class Main {

    public static int swchF(int num) {
        if(num == 0) return 1;
        else return 0;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 1  켜짐
        // 0  꺼짐
        /*
        남 - 배수 상태 바꿈
        여 - 번호 중심 좌우 대칭 같은지 확인, 가장 많은 스위치를 포함하는 구간 찾기
        // ex) 4번 -> 3번 5번 다르면 4번만 바꿈
         */

        int n = sc.nextInt(); // 스위치 개수
        int[] swch = new int[n+1];
        for (int i = 1; i <= n; i++) swch[i] = sc.nextInt();

        int m = sc.nextInt(); // 학생 수

        ArrayList<Student> arr = new ArrayList<>();
        for(int i=0; i<m; i++) {
            int sex = sc.nextInt();
            int num = sc.nextInt();
            arr.add(new Student(sex, num));
        }

        for(Student s : arr) {

            // 남학생
            if(s.sex == 1) {
                int tmp = s.num;

                for(int i=tmp; i<=n; i=i+tmp) {
                    swch[i] = swchF(swch[i]);
                }
            }

            // 여학생
            else {
                int tmp = s.num;
                swch[tmp] = swchF(swch[tmp]);

                int lt = tmp-1, rt = tmp+1;
                while (lt>=1 && rt<=n && swch[lt] == swch[rt]) {
                    swch[lt] = swchF(swch[lt]);
                    swch[rt] = swchF(swch[rt]);
                    lt--;
                    rt++;
                    }
            }
        }

        int size = 0;
        for(int i=1; i<swch.length; i++) {
            System.out.print(swch[i] + " ");
            size++;
            if(size == 20) {
                System.out.println();
                size = 0;
            }
        }




    }
}
import java.util.*;

class Square {
    int h1, h2;
    int w1, w2;

    public Square(int h1, int h2, int w1, int w2) {
        this.h1 = h1;
        this.h2 = h2;
        this.w1 = w1;
        this.w2 = w2;
    }
}

public class Main {


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);


        // 최대 100
        int w = sc.nextInt();   // 가로
        int h = sc.nextInt();   // 세로

        ArrayList<Square> arr = new ArrayList<>();

        int n = sc.nextInt();   // 점선의 개수

        // 0 -> 가로로 자르는 점선
        // 1 -> 세로로 자르는 점선

        arr.add(new Square(0, h, 0, w));

        ArrayList<Square> rmSquare = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int is = sc.nextInt();  // 0 - 가로, 1 - 세로
            int num = sc.nextInt(); // 점선 번호

            int len = arr.size();
            for (int j=0; j<len; j++) {
                Square tmp = arr.get(j);

                if (is == 0) {   // 가로
                    // 세로 영향 받음 - h
                    if(tmp.h1 <= num && tmp.h2 >= num) {
                        rmSquare.add(tmp);
                        arr.add(new Square(tmp.h1, num, tmp.w1, tmp.w2));
                        arr.add(new Square(num, tmp.h2, tmp.w1, tmp.w2));
                    }
                } else {
                    // 가로 영향 받음 - w
                    if(tmp.w1 <= num && tmp.w2 >= num) {
                        rmSquare.add(tmp);
                        arr.add(new Square(tmp.h1, tmp.h2, tmp.w1, num));
                        arr.add(new Square(tmp.h1, tmp.h2, num, tmp.w2));
                    }
                }
            }

            // 자르기 전 종이 조각 삭제
            for(Square rm : rmSquare) arr.remove(rm);
        }

        int max = Integer.MIN_VALUE;
        for(Square tmp : arr) {
            int area = (tmp.h2-tmp.h1) * (tmp.w2 - tmp.w1);
            max = Math.max(max, area);
        }

        System.out.println(max);
    }
}
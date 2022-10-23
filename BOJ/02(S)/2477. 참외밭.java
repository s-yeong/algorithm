import java.util.*;

public class Main {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        // 1 : 동쪽   2 : 서쪽  3 : 남쪽  4 : 북쪽
        // 반시계방향

        int wMaxIdx = 0, hMaxIdx = 0;
        int wMax = Integer.MIN_VALUE, hMax = Integer.MIN_VALUE;

        int[] dirArr = new int[6];
        int[] lenArr = new int[6];

        for(int i=0; i<6; i++){
            dirArr[i] = sc.nextInt();
            lenArr[i] = sc.nextInt();
        }

        // 1, 2 - width
        // 3, 4 - height
        for(int i=0; i<6; i++) {
            if (dirArr[i] == 1 || dirArr[i] == 2) {
                if (lenArr[i] > wMax) {  // width
                    wMax = lenArr[i];
                    wMaxIdx = i;
                }
            } else {
                    if (lenArr[i] > hMax) {  // height
                        hMax = lenArr[i];
                        hMaxIdx = i;
                    }
                }
        }


        int wL, wR, hL, hR;

        if(wMaxIdx == 0) {
            wL = 5;
            wR = wMaxIdx + 1;
        } else if(wMaxIdx == 5) {
            wL = wMaxIdx - 1;
            wR = 0;
        } else {
            wL = wMaxIdx - 1;
            wR = wMaxIdx + 1;
        }

        if(hMaxIdx == 0) {
            hL = 5;
            hR = hMaxIdx + 1;
        } else if(hMaxIdx == 5) {
            hL = hMaxIdx - 1;
            hR = 0;
        } else {
            hL = hMaxIdx - 1;
            hR = hMaxIdx + 1;
        }

        int sumBig = wMax * hMax;
        int sumSmall = Math.abs(lenArr[wL] - lenArr[wR]) * Math.abs(lenArr[hL] - lenArr[hR]);

        System.out.println(k*(sumBig-sumSmall));











    }
}

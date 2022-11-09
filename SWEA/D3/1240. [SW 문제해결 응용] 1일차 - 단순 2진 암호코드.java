import java.util.*;
import java.io.*;

class Main {
    static int N,M;

    public static int encode(int code) {

        switch(code) {
            case 1101:
                return 0;
            case 11001:
                return 1;
            case 10011:
                return 2;
            case 111101:
                return 3;
            case 100011:
                return 4;
            case 110001:
                return 5;
            case 101111:
                return 6;
            case 111011:
                return 7;
            case 110111:
                return 8;
            case 1011:
                return 9;
        }
        return 0;
    }

    public static int[] extractCode(int[][] arr) {

        int idx = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(arr[i][j] != 0) {
                    idx = i;
                    break;
                }
            }
        }

        String str = "";
        for(int j=M-1; j>=0; j--) {
            if(arr[idx][j] == 1) {  // 무조건 맨 뒷자리 1
                for(int i=j-55; i<j+1; i++) {
                    str += arr[idx][i];
                }
                break;
            }
        }


        int[] code = new int[8];
        for(int i=0; i<7; i++) {
            code[i] = encode(Integer.parseInt(str.substring(0, 7)));
            str = str.substring(7);
        }
        code[7] = encode(Integer.parseInt(str));
        return code;
    }

    public static int validate(int[] code) {

        // 홀수 자리 합 x 3
        int sum1 = 0;
        for(int i=0; i<8; i=i+2) {
            sum1 += code[i];
        }

        // 짝수 자리 합
        int sum2 = 0;
        for(int i=1; i<8; i=i+2) {
            sum2 += code[i];
        }

        // 10의 배수?
        if(((sum1*3) + sum2) % 10 == 0) {
            return sum1+sum2;
        }
        return 0;
    }
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int test_case=0; test_case<T; test_case++) {

            // 세로 크기 N, 가로 크기 M

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][M];
            // NxM 직사각형 배열

            // 입력
            for(int i=0; i<N; i++) {
                String s = br.readLine();

                // N줄에 걸쳐 직사각형 배열 - 암호코드 1개 포함되어 있음
                String[] str = s.split("");
                for(int j=0; j<M; j++) {
                    arr[i][j] = Integer.parseInt(str[j]);
                }
            }

            // 암호코드 추출
            int[] code = extractCode(arr);

            // 암호코드 검증
            int answer = validate(code);


            sb.append("#").append(test_case+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);

    }
}
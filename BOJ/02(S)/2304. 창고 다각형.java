import java.io.*;
import java.util.*;

class Square implements Comparable<Square> {
    int l;
    int h;

    public Square(int l, int h) {
        this.l = l;
        this.h = h;
    }

    @Override
    public int compareTo(Square o) {
        return this.l - o.l;
    }
}

public class Main {

    // 창고 다각형의 면적이 가장 작은 창고
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int sum = 0;
        ArrayList<Square> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // 왼쪽 면의 위치 L, 높이 H
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            arr.add(new Square(L, H));
        }

        // 왼쪽 l 기준 정렬
        Collections.sort(arr);

        // 왼쪽 더하기
        int tmpH = 0;
        int tmpIdx = 0;
        for(int i=0; i<n; i++) {
            if(arr.get(i).h >= tmpH) {  // >=인 이유는 max기둥이 여러 개일 수도 있으니까
                sum += (arr.get(i).l - arr.get(tmpIdx).l) * tmpH;
                tmpH = arr.get(i).h;
                tmpIdx = i;
            }
        }

        // 오른쪽 더하기
        int maxIdx = tmpIdx;
        int maxH = tmpH;
        tmpH=0;
        tmpIdx=0;
        for(int i=arr.size()-1; i>=maxIdx; i--) {
            if(arr.get(i).h > tmpH) {
                sum += Math.abs(arr.get(i).l - arr.get(tmpIdx).l) * tmpH;
                tmpH = arr.get(i).h;
                tmpIdx = i;
            }
        }

        // 큰 막대기 더하기
        sum += maxH;

        System.out.println(sum);

    }
}
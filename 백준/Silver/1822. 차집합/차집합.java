import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int aLen = Integer.parseInt(st.nextToken());
        int bLen = Integer.parseInt(st.nextToken());
        int[] a = new int[aLen];
        int[] b = new int[bLen];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aLen; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bLen; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(a);
        Arrays.sort(b);

        //a에 속하면서 b에 속하지 않은 원소
        List<Integer> answer = new ArrayList<>();
        for(int num : a){
            if(!binarySearch(b, num)) {
                answer.add(num);
            }
        }

        System.out.println(answer.size());
        answer.forEach(integer -> System.out.print(integer + " "));
    }
    static boolean binarySearch(int[] b, int target){

        int lt = 0, rt = b.length - 1;
        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            if(b[mid] == target){
                return true;
            }
            if(b[mid] < target) {
                lt = mid + 1;
            } else {
                rt = mid - 1;
            }
        }
        return false;
    }

}
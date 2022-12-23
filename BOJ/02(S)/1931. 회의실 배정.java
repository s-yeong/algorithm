import java.io.*;
import java.util.*;

class Conf implements Comparable<Conf>{

    int start;
    int end;

    public Conf(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Conf ob) {
        if(this.end != ob.end) return this.end - ob.end;
        else return this.start - ob.start;
    }

}


class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Conf> arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr.add(new Conf(s, e));
        }

        Collections.sort(arr);

        int cnt = 0;
        int et = 0;
        for(Conf ob : arr) {
            if(et <= ob.start) {
                cnt++;
                et = ob.end;
            }
        }

        System.out.println(cnt);
    }
}
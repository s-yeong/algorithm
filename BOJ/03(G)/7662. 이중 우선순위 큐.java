import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /**
         * 정수만 저장하는 이중 우선순위 큐
         * 두 가지 연산
         * 1. 데이터 삽입
         * 2. 데이터 삭제 (우선순위 가장 높은 것, 가장 낮은 것)
         */

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-->0) {

            // Q에 적용할 연산의 개수
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();    //  키로 정렬

            while(k --> 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String operation = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if ("I".equals(operation)) {

                    // 정수 삽입
                    map.put(num, map.getOrDefault(num, 0) + 1);
                }
                else if ("D".equals(operation)) {

                    if (num == 1) {

                        // 최대값 삭제
                        if(map.isEmpty()) continue;

                        int last = map.lastKey();

                        map.put(last, map.get(last) - 1);
                        if(map.get(last) == 0) map.remove(last);
                    }
                    else if (num == -1) {

                        // 최소값 삭제
                        if(map.isEmpty()) continue;

                        int first = map.firstKey();

                        map.put(first, map.get(first) - 1);
                        if(map.get(first) == 0) map.remove(first);
                    }
                }
            }

            if(map.isEmpty()) {
                sb.append("EMPTY").append("\n");
            }
            else if(map.size() == 1) {

                int first = map.firstKey();
                sb.append(first).append(" ").append(first).append("\n");
            }
            else {

                int first = map.firstKey();
                int last = map.lastKey();
                sb.append(last).append(" ").append(first).append("\n");
            }
        }
        System.out.print(sb);
    }
}
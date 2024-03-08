import java.io.*;
import java.util.StringTokenizer;

/**
 * 20546. 기적의 매매법
 * 1. BNP : 주식 살 수 있으면 무조건 최대한 많이 산다.
 * 2-1. TIMING : 3일 연속 전일 대비 상승 -> 전량 매도
 * 2-2. 3일 연속 전일 대비 하락 -> 전량 매수
 * 3. 자산 계산 : (현금 + 1월 14일 주가 x 주식 수)
 */
public class Main {
    static final int NUM_OF_DAYS = 14;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = Integer.parseInt(br.readLine());
        int[] stock = new int[NUM_OF_DAYS];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int idx=0; idx<NUM_OF_DAYS; idx++) {
            stock[idx] = Integer.parseInt(st.nextToken());
        }

        // BNP
        int bnpMoney = money;
        int bnpStockCount = 0;

        // * 1. BNP : 주식 살 수 있으면 무조건 최대한 많이 산다.
        for(int idx=0; idx<NUM_OF_DAYS; idx++) {
            // 하나라도 살 수 있으면,
            if(bnpMoney >= stock[idx]) {
                int count = bnpMoney / stock[idx];
                bnpMoney -= count * stock[idx];
                bnpStockCount += count;
            }
        }

        // TIMING
        int timingMoney = money;
        int timingStockCount = 0;

        // * 2-1. TIMING : 3일 연속 전일 대비 상승 -> 전량 매도
        // * 2-2. 3일 연속 전일 대비 하락 -> 전량 매수
        int increaseCount = 0;
        int decreaseCount = 0;
        for(int idx=1; idx<NUM_OF_DAYS; idx++) {
            if(stock[idx] > stock[idx-1]) {
                increaseCount++;
                decreaseCount = 0;
            }
            else if(stock[idx] < stock[idx-1]) {
                decreaseCount++;
                increaseCount = 0;
            }
            else {
                increaseCount = 0;
                decreaseCount = 0;
            }

            if(increaseCount >= 3) {
                timingMoney += timingStockCount * stock[idx];
                timingStockCount = 0;
            }
            else if(decreaseCount >= 3) {
                if(timingMoney >= stock[idx]) {
                    int count = timingMoney / stock[idx];
                    timingMoney -= count * stock[idx];
                    timingStockCount += count;
                }
            }
        }
        int bnp = bnpMoney + bnpStockCount * stock[NUM_OF_DAYS - 1];
        int timing = timingMoney + timingStockCount * stock[NUM_OF_DAYS - 1];

        String answer;
        if(bnp > timing) {
            answer = "BNP";
        } else if(timing > bnp) {
            answer = "TIMING";
        } else {
            answer = "SAMESAME";
        }
        System.out.println(answer);
    }
}

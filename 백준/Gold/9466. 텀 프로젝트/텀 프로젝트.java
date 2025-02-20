import java.io.*;
import java.util.*;

/**
 * 1. 팀원 수 제한 없음
 * => 한 팀만 있을 수 있음
 * 2. 프로젝트를 함께하고 싶은 학생을 선택해야 함
 *
 * Q. 어느 프로젝트 팀에도 속하지 않은 학생의 수 계산
 * - 사이클 만들어진 경우에만 팀
 * - 사이클 만들어지 않은 학생 수 구하기
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int studentCount = Integer.parseInt(br.readLine());
            int[] students = new int[studentCount+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= studentCount; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }
            team = new int[studentCount + 1];

            for(int i = 1; i <= studentCount; i++) {
                if(team[i] == 0) {
                    first = 0;
                    dfs(i, students);
                    // 사이클 만들어 졌으면 처음 학생 실패 -> 성공 처리
                    if(first != 0) {
                        team[first] = SUCCESS;
                    }
                }
            }

            int count = 0;
            for(int i = 1; i <= studentCount; i++) {
                if(team[i] == FAIL) {
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.print(sb);
    }
    static int[] team;
    static int first;
    static final int SELF = 1, SUCCESS = 2, FAIL = 3, PENDING = 4;
    static void dfs(int cur, int[] students) {

        int next = students[cur];

        // 셀프인 경우
        if(cur == next) {
            team[cur] = SELF;
            return;
        }

        // 다음 노드가 셀프 -> 현재 노드 사이클 될 수 x
        if(team[next] == SELF) {
            team[cur] = FAIL;
            return;
        }

        // 다음 노드가 실패 -> 현재 노드 사이클 될 수 X
        if(team[next] == FAIL) {
            team[cur] = FAIL;
            return;
        }

        // 다음 노드가 이전에 방문 -> 사이클 형성
        if(team[next] == PENDING) {
            team[cur] = SUCCESS;
            // 사이클 첫번째 첫번째 노드
            first = next;
            return;
        }

        // 방문
        team[cur] = PENDING;

        dfs(next, students);

        if(team[next] == SELF || team[next] == FAIL) {
            team[cur] = FAIL;
        }
        else if(team[next] == SUCCESS) {
            team[cur] = SUCCESS;
        }

        // 사이클 성공 처리 끝
        // 이전에 붙어있는 노드 끝내기 위함
        if(first == cur) {
            team[cur] = FAIL;
        }
    }
}
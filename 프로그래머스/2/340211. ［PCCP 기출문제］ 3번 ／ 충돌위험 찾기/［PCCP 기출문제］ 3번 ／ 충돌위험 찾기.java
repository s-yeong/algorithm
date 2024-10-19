import java.util.*;

class Solution {
    static int answer;

    public int solution(int[][] points, int[][] routes) {
        answer = 0;
        int robotCnt = routes.length;
        Queue<Point> Q = new ArrayDeque<>();

        for (int idx = 0; idx < robotCnt; idx++) {
            int startIdx = routes[idx][0];
            int[] start = points[startIdx - 1];
            int nextIdx = routes[idx][1];
            int[] next = points[nextIdx - 1];
            Q.offer(new Point(start[0], start[1], next[0], next[1], 1, routes[idx]));
        }

        while (!Q.isEmpty()) {
            checkCollision(Q);
            moveRobots(Q, points);
        }

        return answer;
    }

    private void moveRobots(Queue<Point> Q, int[][] points) {
        int size = Q.size();
        while (size-- > 0) {
            Point p = Q.poll();

            if (p.isCompleted()) {
                if (!p.next(points)) {
                    continue;
                }
            }

            if (p.row != p.nextRow) {
                if (p.nextRow > p.row) p.row++;
                else p.row--;
            } else if (p.col != p.nextCol) {
                if (p.nextCol > p.col) p.col++;
                else p.col--;
            }

            Q.offer(p);
        }
    }

    private void checkCollision(Queue<Point> Q) {
        Map<String, Integer> positionMap = new HashMap<>();

        for (Point p : Q) {
            String position = p.row + "," + p.col;
            positionMap.put(position, positionMap.getOrDefault(position, 0) + 1);
        }

        for (int count : positionMap.values()) {
            if (count > 1) {
                answer++;
            }
        }
    }

    static class Point {
        int row;
        int col;
        int nextRow;
        int nextCol;
        int routeIdx;
        int[] route;

        Point(int row, int col, int nextRow, int nextCol, int routeIdx, int[] route) {
            this.row = row;
            this.col = col;
            this.nextRow = nextRow;
            this.nextCol = nextCol;
            this.routeIdx = routeIdx;
            this.route = route;
        }

        boolean isCompleted() {
            return this.row == this.nextRow && this.col == this.nextCol;
        }

        boolean next(int[][] points) {
            if (routeIdx >= route.length - 1) return false;
            routeIdx++;
            int[] nextPoint = points[route[routeIdx] - 1];
            this.nextRow = nextPoint[0];
            this.nextCol = nextPoint[1];
            return true;
        }
    }
}

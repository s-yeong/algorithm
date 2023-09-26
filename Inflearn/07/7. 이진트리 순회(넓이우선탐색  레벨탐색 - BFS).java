import java.util.LinkedList;
import java.util.Queue;

class Node {

    int data;
    Node lt, rt;

    public Node(int data) {
        this.data = data;
        lt = rt = null;
    }

}
public class Main {

    Node root;

    public void BFS(Node root) {

        Queue<Node> Q = new LinkedList<>();
        Q.offer(root);  // root 원소 하나
        int L = 0;
        while(!Q.isEmpty()) {
            int len=Q.size();   // Q의 원소
            System.out.print(L+" : ");    // L 레벨을 어떤 노드들이 있는지
            for(int i=0; i<len; i++) {
                Node cur = Q.poll();
                System.out.print(cur.data + " ");
                // 왼쪽 자식 노드, 오른쪽 자식 노드 넣기
                if(cur.lt != null) {    // 말단 노드가 아니면
                    Q.offer(cur.lt);
                }
                if(cur.rt != null) Q.offer(cur.rt);
            }   // 레벨이 끝남
            L++;
            System.out.println();   // 줄바꿈
        }

    }



    public static void main(String[] args) {

        Main tree = new Main();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        tree.root.rt.lt = new Node(6);
        tree.root.rt.rt = new Node(7);
        tree.BFS(tree.root);
    }
}
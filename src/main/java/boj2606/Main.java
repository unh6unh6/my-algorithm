package boj2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int computerCnt;
    static int connectCnt;
    static List<Integer>[] connections;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        computerCnt = Integer.parseInt(br.readLine());
        connectCnt = Integer.parseInt(br.readLine());

        visit = new boolean[computerCnt + 1];

        connections = new List[computerCnt + 1];
        for (int i = 0; i <= computerCnt; i++) {
            connections[i] = new ArrayList<>();
        }
        for (int i = 0; i < connectCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            connections[node1].add(node2);
            connections[node2].add(node1);
        }

        visit[1] = true;
        dfs(1);
        int result = 0;
        for (final boolean b : visit) {
            if (b) {
                result++;
            }
        }
        System.out.println(result - 1);
    }

    private static void dfs(int target) {
        List<Integer> connectedNodes = connections[target];
        for (final int connectedNode : connectedNodes) {
            if (!visit[connectedNode]) {
                visit[connectedNode] = true;
                dfs(connectedNode);
            }
        }
    }
}

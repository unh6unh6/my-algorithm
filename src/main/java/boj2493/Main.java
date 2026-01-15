package boj2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Top> stack = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            int height = Integer.parseInt(st.nextToken());
            while (true) {
                if (stack.isEmpty()) {
                    sb.append("0 ");
                    stack.add(new Top(height, i));
                    break;
                }
                if (stack.get(stack.size() -1).height > height) {
                    sb.append(stack.get(stack.size() - 1).idx).append(" ");
                    stack.add(new Top(height, i));
                    break;
                }
                if (stack.get(stack.size() -1).height < height) {
                    stack.remove(stack.size() - 1);
                }
            }
        }
        System.out.println(sb);
    }
}

class Top {
    final int height;
    final int idx;

    Top(
            final int height,
            final int idx
    ) {
        this.height = height;
        this.idx = idx;
    }
}

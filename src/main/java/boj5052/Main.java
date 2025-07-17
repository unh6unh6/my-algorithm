package boj5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            List<char[]> numbers = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                final String strNum = br.readLine();
                numbers.add(strNum.toCharArray());
            }
            numbers.sort(Comparator.comparingInt(arr -> arr.length));
            process(numbers);
        }
    }

    private static void process(final List<char[]> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            final char[] targetNumber = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) {
                final char[] compareNumber = numbers.get(j);
                if (compareStart(targetNumber, compareNumber)) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println("YES");
    }

    private static boolean compareStart(
            final char[] targetNumber,
            final char[] compareNumber
    ) {
        for (int i = 0; i < targetNumber.length; i++) {
            if (targetNumber[i] != compareNumber[i]) {
                return false;
            }
        }
        return true;
    }
}

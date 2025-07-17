package boj9342;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 08:08 ~
    static int t;
    static int index = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String testCase = br.readLine();
            checkAndPrint(testCase);
        }
    }

    private static void checkAndPrint(final String testCase) {
        char[] chars = testCase.toCharArray();
        if (checkCondition1(chars[0]) &&
            checkCondition2(chars) &&
            checkCondition3(chars) &&
            checkCondition4(chars) &&
            checkCondition5(chars)
        ) {
            System.out.println("Infected!");
            return;
        }
        System.out.println("Good");
    }

    private static boolean checkCondition1(final char c) {
        char[] allow = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};
        for (int i = 0; i < allow.length; i++) {
            if (c == allow[i]) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkCondition2(final char[] chars) {
        if (chars[0] != 'A' && chars[1] != 'A') {
            return false;
        }
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != 'A') {
                index = i;
                break;
            }
        }
        return true;
    }

    private static boolean checkCondition3(final char[] chars) {
        if (chars[index] != 'F') {
            return false;
        }
        for (int i = index + 1; i < chars.length; i++) {
            if (chars[i] != 'F') {
                index = i;
                break;
            }
        }
        return true;
    }

    private static boolean checkCondition4(final char[] chars) {
        if (chars[index] != 'C') {
            return false;
        }
        for (int i = index + 1; i < chars.length; i++) {
            if (chars[i] != 'C') {
                break;
            }
            index ++;
        }
        return true;
    }

    private static boolean checkCondition5(final char[] chars) {
        char[] allow = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};
        if (chars.length - 1 == index) {
            for (int i = 0; i < allow.length; i++) {
                if (chars[index] == allow[i]) {
                    return true;
                }
            }
        }
        return false;
    }
}

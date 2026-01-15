package prac.prac1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    // 20:30 ~
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();

        int count = 1;
        char before = chars[0];
        int max = -1;

        for (int i = 1; i < chars.length; i++) {
            char thisChar = chars[i];
            if (thisChar == before) {
                count ++;
            } else {
                count = 1;
            }

            if (count == 3) {
                int value = thisChar - '0';
                max = Integer.max(toCoolNumber(value), max);
            }
            before = thisChar;
        }

        System.out.println(max);
    }

    private static int toCoolNumber(int value) {
        return value * 100 + value * 10 + value;
    }
}

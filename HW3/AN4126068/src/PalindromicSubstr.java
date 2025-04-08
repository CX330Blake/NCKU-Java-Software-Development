import java.util.Scanner;

public class PalindromicSubstr {
    public static int countSubstr(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n]; // represent whether dp[i][j] is palindromic
        int count = 0;

        // Lenght from 1 to n
        for (int len = 1; len <= n; len++) { // outer: control substring length
            for (int i = 0; i <= n - len; i++) { // inner: control substring start index
                int j = i + len - 1; // j = end index of the substring

                if (s.charAt(i) == s.charAt(j)) { // check if the first and last characters are equal

                    /*
                     * Thoughts:
                     * We know "abcba" is a palindrome, so we can sure that the substring "bcb" is
                     * also a palindrome.
                     */

                    if (len == 1 || len == 2) { // if len == 1 or len == 2, it's a palindrome
                        dp[i][j] = true;
                    } else { // else, check if the substring between i and j is a palindrome
                        dp[i][j] = dp[i + 1][j - 1];
                    }

                    if (dp[i][j]) { // if s[i..j] is palindrome, count plus 1
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        sc.close();
        System.out.println(countSubstr(s));
    }
}

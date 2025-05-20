package example;


import java.util.ArrayList;

public class Leetcode {
    public boolean isPalindrome(String s) {
            String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            return cleaned.contentEquals(new StringBuilder(cleaned).reverse());
    }
    public int fib(int n) {
        if (n <= 1) return n;

        int a = 0;
        int b = 1;
        for (int i = 2; i<=n; i++){
            int sum = a+b;
            a = b;
            b = sum;
        }
        return b;
    }
}
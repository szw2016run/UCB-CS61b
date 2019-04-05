import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
        //Checking empty string case
        assertTrue(palindrome.isPalindrome(""));

        //Checking length 1 string case
        assertTrue(palindrome.isPalindrome("a"));

        //Checking true statements
        assertTrue(palindrome.isPalindrome("mom"));
        assertTrue(palindrome.isPalindrome("repaper"));
        assertTrue(palindrome.isPalindrome("deified"));

        //Checking false statements
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("awesome"));
        assertFalse(palindrome.isPalindrome("clock"));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        //Checking empty string case
        OffByOne test = new OffByOne();

        assertTrue(palindrome.isPalindrome("", test));

        //Checking length 1 string case
        assertTrue(palindrome.isPalindrome("a", test));
        assertTrue(palindrome.isPalindrome("Z", test));

        //Checking true statements
        assertTrue(palindrome.isPalindrome("flake", test));

        //Checking false statements
        assertFalse(palindrome.isPalindrome("awesome", test));

    }
}     //Uncomment this class once you've created your Palindrome class. */

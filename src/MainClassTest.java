import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    String actual = getClassString();
    String wordOne = "hello";
    String wordTwo = "Hello";
    boolean state = false;

    @Test
    public void testGetClassString() {
        String actual = getClassString();
        String wordOne = "hello";
        String wordTwo = "Hello";

        Assert.assertTrue(actual + " has no " + wordOne + " or " + wordTwo, checkContain());
    }

    public boolean checkContain() {
        if (actual.contains(wordOne)) {
            state = true;
        }

        if (actual.contains(wordTwo)) {
            state = true;
        }

        return state;
    }
}

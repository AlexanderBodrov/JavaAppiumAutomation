import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test
    public void testGetClassNumber(){
        int actual = getClassNumber();
        int expected = 45;

        Assert.assertTrue("The number is less " + expected, actual > expected);
    }
}

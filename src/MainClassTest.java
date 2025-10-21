import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test
    public void testGetLocalNumber(){
        int actual = getLocalNumber();
        int expected = 14;

        Assert.assertTrue("The number is not equals " + expected, actual == expected);
    }
}

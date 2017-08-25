import org.junit.Assert;
import org.junit.Test;

public class TestGuessingGame {

    @Test
    public void testCreate() {
        GuessingGame guessingGame = new GuessingGame();

        Assert.assertEquals(1, guessingGame.getMin());
        Assert.assertEquals(10, guessingGame.getMax());
    }

    @Test
    public void testCheck() {
        GuessingGame guessingGame = new GuessingGame();

        Status status = guessingGame.check(100);
        Assert.assertEquals(Status.TRY_SMALLER, status);
    }
}

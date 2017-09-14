import game.GuessingGame;
import game.Status;
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
    public void testTrySmaller() {
        GuessingGame guessingGame = new GuessingGame();

        Status status = guessingGame.check(100);
        Assert.assertEquals(Status.TRY_SMALLER, status);
    }

    @Test
    public void testTryBigger() {
        GuessingGame guessingGame = new GuessingGame(10, 20);

        Status status = guessingGame.check(1);
        Assert.assertEquals(Status.TRY_BIGGER, status);
    }

    @Test
    public void testFound() {
        mainLoop: for (int trys = 0; trys < 100000000; ++trys) {
            GuessingGame guessingGame = new GuessingGame(10, 1000);

            int number;
            for (number = 10; number < 1001; ++number) {
                Status status = guessingGame.check(number);
                if (status == Status.FOUND) {
                    continue mainLoop;
                }
            }
            Assert.fail(String.format("didn't find secret between %d-%d for %d",
                    guessingGame.getMin(), guessingGame.getMax(), number));
        }
    }
}

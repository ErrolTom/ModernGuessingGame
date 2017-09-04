import java.util.Random;

public class GuessingGame {
    private int secret;
    private static final Random random = new Random();
    private int min, max;

    public GuessingGame(int min, int max) {
        this.min = min;
        this.max = max;
        secret = min + random.nextInt(max-min+1);
    }

    public GuessingGame() {
        this(1,10);
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public Status check(int guess) {
        if (guess == secret) {
            return Status.FOUND;
        } else if (guess < secret) {
            return Status.TRY_BIGGER;
        }
        return Status.TRY_SMALLER;
    }
}

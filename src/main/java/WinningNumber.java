import java.util.List;

public class WinningNumber {
    private static final String DUPLICATE_BONUS_BALL_MESSAGE = "[ERROR] 보너스 볼은 당첨 번호와 중복될수 없습니다.";
    private static final String WINNING_NUMBER_LENGTH_ONLY_SIX = "[ERROR] 당첨 번호는 6자리여야 합니다.";
    public static final int LENGTH_STANDARD = 6;
    List<Integer> winningNumbers;

    public WinningNumber(List<Integer> winningNumbers) {
        checkWinningNumberLength(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    public void checkBonusBall(int bonusBall) {
        if (winningNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_BALL_MESSAGE);
        }
    }

    public boolean contains(int number) {
        return winningNumbers.contains(number);
    }

    private void checkWinningNumberLength(List<Integer> winningNumbers) {
        if (winningNumbers.size() != LENGTH_STANDARD) {
            throw new IllegalArgumentException(WINNING_NUMBER_LENGTH_ONLY_SIX);
        }

    }
}
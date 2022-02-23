import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    public static final String INPUT_MONEY_ONLY_NUMBER_MESSAGE = "[ERROR] 금액은 숫자로만 입력해주세요.";
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final Scanner scanner = new Scanner(System.in);
    public static final String REGEX_NUMBER = "^[0-9]*$";
    private static final Pattern pattern = Pattern.compile(REGEX_NUMBER);

    public static int askInputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String input = scanner.nextLine();
        return convertToInt(input);
    }

    private static int convertToInt(String input) {
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException(INPUT_MONEY_ONLY_NUMBER_MESSAGE);
        }
        return Integer.parseInt(input);
    }

    public static String askInputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        return scanner.nextLine();
    }

    public static String askInputBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return scanner.nextLine();
    }
}
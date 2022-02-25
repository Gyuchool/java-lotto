package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_START = 0;
    private static final int LOTTO_END = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getLotto() {
        return numbers;
    }

    public static Lotto generateNumber() {
        List<Integer> lottoRange = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoRange.add(i);
        }
        Collections.shuffle(lottoRange);
        List<Integer> numbers = lottoRange.subList(LOTTO_START, LOTTO_END);
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    public Rank match(WinningNumber winningNumber) {
        int matchCount = getMatchCount(winningNumber);
        boolean hasBonusBall = winningNumber.isBonusBallMatch(numbers);
        return Rank.valueOf(matchCount, hasBonusBall);
    }

    private int getMatchCount(WinningNumber winningNumber) {
        return (int) numbers.stream().mapToInt(number -> number).filter(winningNumber::contains).count();

    }

}

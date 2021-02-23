package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int PRICE = 1000;

    private final LottoNumbers lottoNumbers;

    public Lotto(final LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public Lotto(final int[] numbers) {
        this(new LottoNumbers(Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList())));
    }

    public static int getNumberOfAvailablePurchases(Money money) {
        return money.divide(PRICE);
    }

    public static Money getPurchasedAmount(int purchaseCount) {
        return new Money(PRICE * purchaseCount);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int findMatchCount(Lotto targetLotto) {
        return lottoNumbers.findMatchCount(targetLotto.lottoNumbers);
    }

    public List<Integer> getNumbers() {
        return lottoNumbers.getNumbers();
    }
}
package domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {
    private final Set<Integer> numbers;

    public ManualLottoGenerator(final Set<Integer> numbers) {
        this.numbers = new HashSet<>(numbers);
    }

    @Override
    public Lotto generateLotto() {
        Set<LottoNumber> collect = numbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toSet());
        return new Lotto(collect);
    }
}

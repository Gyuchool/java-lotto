package controller.dto;

import domain.LottoNumber;
import domain.Lottos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottosDto {
    private final List<Set<Integer>> lottoDtos;
    private final int size;
    private final int money;

    public LottosDto(List<Set<Integer>> lottoDtos, int size, int money) {
        this.lottoDtos = new ArrayList<>(lottoDtos);
        this.size = size;
        this.money = money;
    }

    public List<Set<Integer>> getLottoDtos() {
        return lottoDtos;
    }

    public int getSize() {
        return size;
    }

    public int getMoney() {
        return money;
    }

    public static LottosDto from(Lottos lottos, int size, int money) {
        return lottos.getLottos()
                .stream()
                .map(lotto -> {
                    return lotto.getNumbers().stream()
                            .map(LottoNumber::getLottoNumber)
                            .collect(Collectors.toSet());
                })
                .collect(Collectors.collectingAndThen(
                        Collectors.toUnmodifiableList(),
                        s -> new LottosDto(s, size, money)
                ));
    }
}

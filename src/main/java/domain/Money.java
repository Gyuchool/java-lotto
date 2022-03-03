package domain;

import java.util.Objects;

public class Money {

    private static final String MIN_UNIT_MESSAGE = "[ERROR] 구입 금액은 1000원 단위이어야 합니다.";
    private static final int UNIT = 1000;

    private final int money;

    public Money(int money) {
        validateUnit(money);
        this.money = money;
    }

    private void validateUnit(int money) {
        if (money < UNIT) {
            throw new IllegalArgumentException(MIN_UNIT_MESSAGE);
        }
    }

    public int generateCount() {
        return money / UNIT;
    }

    public int getMoney() {
        return money;
    }

    public void validateCountRange(int maxLottoCount, int inputCount) {
        if (inputCount < 0 || inputCount > maxLottoCount) {
            throw new IllegalArgumentException("구매할 로또 수를 다시 입력해주세요.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money that = (Money) o;
        return money == that.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}

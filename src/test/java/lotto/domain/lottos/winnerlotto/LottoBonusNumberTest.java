package lotto.domain.lottos.winnerlotto;

import lotto.domain.lottos.LottoNumber;
import lotto.domain.lottos.LottoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.lottos.winnerlotto.LottoBonusNumber.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoBonusNumberTest {

    private LottoTicket lottoWinnerTicket;

    @BeforeEach
    void setUp() {
        List<LottoNumber> lottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );

        lottoWinnerTicket = new LottoTicket(lottoWinnerNumbers);
    }

    @Test
    @DisplayName("보너스 숫자를 생성한다.")
    public void createNumberTest() {
        LottoBonusNumber number = of("6", lottoWinnerTicket);

        assertThat(number).isEqualTo(of("6", lottoWinnerTicket));
    }

    @Test
    @DisplayName("보너스 로또 숫자는 1~45 사이의 숫자여야한다.")
    public void validateNumberTest() {
        assertThatThrownBy(() -> {
            of("46", lottoWinnerTicket);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(RANGE_ERROR_MESSAGE, MIN_NUMBER_RANGE, MAX_NUMBER_RANGE));
    }

    @Test
    @DisplayName("보너스 번호는 당첨번호 6개와 중복되면 IllegalArgument 예외가 발생한다.")
    public void duplicateBonusNumberTest() {
        assertThatThrownBy(() -> {
            of("45", lottoWinnerTicket);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(DUPLICATE_ERROR_MESSAGE);
    }
}
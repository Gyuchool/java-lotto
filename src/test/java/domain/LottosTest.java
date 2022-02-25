package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @Test
    @DisplayName("구입 금액에 따른 개수만큼 로또 생성")
    public void generateLottosTest() {
        int count = 14;
        Lottos lottos = Lottos.generateLottos(count);
        assertThat(lottos.size()).isEqualTo(count);
    }

    @Test
    @DisplayName("당첨 번호 통계 테스트")
    public void checkWinningStatisticsTest() {

        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto firstLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto fifthLotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        Lotto missLotto = new Lotto(List.of(13, 14, 15, 16, 17, 18));

        Lottos lottos = new Lottos(Arrays.asList(firstLotto, fifthLotto, missLotto));
        Statistic winningStatistics = lottos.getWinningStatistics(winningNumber);

        assertThat(winningStatistics.getStatistics().get(Rank.FIRST)).isEqualTo(1);
        assertThat(winningStatistics.getStatistics().get(Rank.SECOND)).isEqualTo(0);
        assertThat(winningStatistics.getStatistics().get(Rank.FIFTH)).isEqualTo(1);

    }
}
package lottogame.domain.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.*;

class LottoResultsTest {
    LottoResults lottoResults;

    @BeforeEach
    void setUp() {
        EnumMap<Rank, Integer> results = new EnumMap<>(Rank.class);
        results.put(Rank.NOT_FOUND, 0);
        results.put(Rank.FIFTH, 5);
        results.put(Rank.FOURTH, 0);
        results.put(Rank.THIRD, 0);
        results.put(Rank.SECOND, 1);
        results.put(Rank.FIRST, 0);
        lottoResults = new LottoResults(results, Yield.of(Money.of(30025000), Money.of(10000)));
    }

    @Test
    @DisplayName("같은 값을 가지면 같은 객체인지 확인")
    void constructor() {
        EnumMap<Rank, Integer> newResults = new EnumMap<>(Rank.class);
        newResults.put(Rank.NOT_FOUND, 0);
        newResults.put(Rank.FIFTH, 5);
        newResults.put(Rank.FOURTH, 0);
        newResults.put(Rank.THIRD, 0);
        newResults.put(Rank.SECOND, 1);
        newResults.put(Rank.FIRST, 0);
        LottoResults newLottoResults = new LottoResults(newResults, Yield.of(Money.of(30025000), Money.of(10000)));
        assertEquals(newLottoResults, lottoResults);
        newResults.put(Rank.FIFTH, newResults.get(Rank.FIFTH) - 2);
        newLottoResults = new LottoResults(newResults, Yield.of(Money.of(30025000), Money.of(10000)));
        assertNotEquals(newLottoResults, lottoResults);
    }
}
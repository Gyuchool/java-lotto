package controller.dto;

import domain.LottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoGeneratorDto {
    private final int autoLottoCount;
    private final List<LottoGenerator> lottoGenerators;

    public LottoGeneratorDto(int autoLottoCount, List<LottoGenerator> lottoGenerators) {
        this.autoLottoCount = autoLottoCount;
        this.lottoGenerators = new ArrayList<>(lottoGenerators);
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }

    public List<LottoGenerator> getLottoGenerators() {
        return new ArrayList<>(lottoGenerators);
    }
}

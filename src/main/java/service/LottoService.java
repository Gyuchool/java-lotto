package service;

import controller.dto.LottosDto;
import domain.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoService {

    public WinningLotto generateWinningLotto(LottoGenerator lottoGenerator, int inputBonusBall) {
        Lotto lotto = lottoGenerator.generateLotto();
        LottoNumber bonusBall = LottoNumber.of(inputBonusBall);

        return new WinningLotto(lotto, bonusBall);
    }

    public Lottos purcahse(int inputMoney, List<Set<Integer>> manualLottoNumbers) {
        Money money = new Money(inputMoney);
        int autoLottoCount = money.getAutoLottoCount(manualLottoNumbers.size());
        return generateLottos(manualLottoNumbers, autoLottoCount);
    }

    private Lottos generateLottos(List<Set<Integer>> manualLottoNumbers, int autoLottoCount) {
        List<LottoGenerator> lottoGenerators = manualLottoNumbers.stream()
                .map(ManualLottoGenerator::new)
                .collect(Collectors.toList());
        IntStream.range(0, autoLottoCount)
                .mapToObj(i -> new AutoLottoGenerator())
                .forEach(lottoGenerators::add);
        return Lottos.generateLottos(lottoGenerators);
    }

    public Statistic getWinningResult(Set<Integer> inputWinningNumber, int inputBonusBall, LottosDto lottosDto) {
        WinningLotto winningLotto = generateWinningLotto(new ManualLottoGenerator(inputWinningNumber), inputBonusBall);

        Lottos lottos = generateLottos(lottosDto.getLottoDtos(), 0);
        return lottos.getWinningStatistics(winningLotto);
    }
}

package controller;

import controller.dto.LottosDto;
import controller.dto.StatisticDto;
import domain.*;
import service.LottoService;
import service.StatisticService;

import java.util.List;
import java.util.Set;

public class LottoController {

    private final LottoService lottoService;
    private final StatisticService statisticService;

    public LottoController(LottoService lottoService, StatisticService statisticService) {
        this.lottoService = lottoService;
        this.statisticService = statisticService;
    }

    public LottosDto purchase(int inputMoney, List<Set<Integer>> manualLottoNumbers) {

        Lottos lottos = lottoService.purcahse(inputMoney, manualLottoNumbers);
        return LottosDto.from(lottos, lottos.size(), inputMoney);
    }

    public StatisticDto winningResult(Set<Integer> inputWinningNumber,
                                      int inputBonusBall,
                                      LottosDto lottosDto) {
        Statistic winningStatistics  = lottoService.getWinningResult(inputWinningNumber, inputBonusBall, lottosDto);
        double profitRate = statisticService.getProfitRate(winningStatistics, lottosDto.getMoney());
        return StatisticDto.from(winningStatistics, profitRate);
    }
}

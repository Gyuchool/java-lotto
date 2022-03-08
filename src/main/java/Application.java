import controller.LottoController;
import controller.dto.LottosDto;
import controller.dto.StatisticDto;
import service.LottoService;
import service.StatisticService;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoService(), new StatisticService());
        int inputMoney = InputView.askInputMoney();
        int manualLottoCount = InputView.askManualLottoCount();
        List<Set<Integer>> manualLottoNumbers = InputView.askManualLottoNumbers(manualLottoCount);
        LottosDto lottosDto = lottoController.purchase(inputMoney, manualLottoNumbers);
        OutputView.printCountOfLotto(lottosDto.getSize(), manualLottoCount);

        OutputView.printLottos(lottosDto);
        Set<Integer> inputWinningNumber = InputView.askInputWinningNumber();
        int inputBonusBall = InputView.askInputBonusBall();

        StatisticDto statisticDto = lottoController.winningResult(inputWinningNumber, inputBonusBall, lottosDto);
        OutputView.printStatistics(statisticDto);
        OutputView.printProfitRate(statisticDto.getProfitRate());

    }
}

import domain.Lottos;
import domain.Money;
import domain.Statistic;
import domain.WinningNumber;
import view.InputView;
import view.OutputView;

public class LottoMachine {

    private Money money;
    private Lottos lottos = new Lottos();
    private WinningNumber winningNumber;

    public void start() {

        int lottoCount = inputMoney();
        lottos.generateLottos(lottoCount);
        OutputView.printLottos(lottos);
        int bonusBall = inputWinningNumber();
        getStatistics(bonusBall);
    }

    private int inputMoney() {
        money = new Money(InputView.askInputMoney());
        int count = money.generateCount();
        OutputView.printCountOfLotto(count);
        return count;
    }

    private int inputWinningNumber() {
        winningNumber = InputView.askInputWinningNumber();
        int bonusBall = InputView.askInputBonusBall();
        winningNumber.checkBonusBall(bonusBall);
        return bonusBall;
    }

    private void getStatistics(int bonusBall) {
        Statistic winningStatistics = lottos.getWinningStatistics(winningNumber, bonusBall);
        OutputView.printStatistics(winningStatistics);
        OutputView.printProfitRate(winningStatistics, money);
    }
}

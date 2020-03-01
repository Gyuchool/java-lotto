package lotto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoCount;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.dto.LottoCountDto;
import lotto.dto.LottoResultDto;
import lotto.dto.LottosDto;
import lotto.utils.InputUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	private static LottoMachine lottoMachine = LottoMachine.getInstance();

	public static void run() {
		Lottos lottos = buyLottos();
		OutputView.printLottos(LottosDto.from(lottos));
		LottoResult lottoResult = new LottoResult(lottos.findAllLottoPrizes(readWinningLotto()));
		OutputView.printLottoResult(LottoResultDto.from(lottoResult));
	}

	private static Lottos buyLottos() {
		try {
			LottoCount lottoCount = readLottoCount(readMoney());
			OutputView.printLottoCount(LottoCountDto.from(lottoCount));
			return new Lottos(lottoMachine.makeLottos(lottoCount, readAllManualLottoNumbersBy(lottoCount)));
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e);
			return buyLottos();
		}
	}

	private static Money readMoney() {
		try {
			InputView.printInsertMoney();
			return new Money(InputUtil.inputMoney());
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongMoneyInput(e);
			return readMoney();
		}
	}

	private static LottoCount readLottoCount(Money money) {
		try {
			return new LottoCount(money, readManualLottoCount());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e);
			return readLottoCount(money);
		}
	}

	private static int readManualLottoCount() {
		try {
			InputView.printInsertManualLottoCount();
			return InputUtil.inputManualLottoCount();
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongManualLottoCount(e);
			return readManualLottoCount();
		}
	}

	private static List<LottoNumbers> readAllManualLottoNumbersBy(LottoCount lottoCount) {
		List<LottoNumbers> manualLottoNumbers = new ArrayList<>();

		for (int i = 1; i <= lottoCount.getManualLottoCount(); i++) {
			manualLottoNumbers.add(readManualLottoNumbers(i));
		}
		return manualLottoNumbers;
	}

	private static LottoNumbers readManualLottoNumbers(int lottoIndex) {
		try {
			InputView.printInsertManualLottoNumbers(lottoIndex);
			return InputUtil.inputManualLottoNumbers().stream()
				.map(value -> new LottoNumber(Integer.parseInt(value)))
				.collect(Collectors.collectingAndThen(Collectors.toList(), LottoNumbers::new));
		} catch (IllegalArgumentException | IOException e) {
			OutputView.printExceptionMessage(e);
			return readManualLottoNumbers(lottoIndex);
		}
	}

	private static WinningLotto readWinningLotto() {
		try {
			return new WinningLotto(readWinningNumber(), readBonusNumber());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e);
			return readWinningLotto();
		}
	}

	private static List<String> readWinningNumber() {
		try {
			InputView.printInsertWinningNumber();
			return InputUtil.inputWinningNumber();
		} catch (IOException e) {
			e.printStackTrace();
			return readWinningNumber();
		}
	}

	private static int readBonusNumber() {
		try {
			InputView.printInsertBonusNumber();
			return InputUtil.inputBonusNumber();
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongBonusNumberInput(e);
			return readBonusNumber();
		}
	}
}
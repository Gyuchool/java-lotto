package service;

import domain.Money;
import domain.Statistic;

public class StatisticService {
    public double getProfitRate(Statistic statistic, int money){
        return statistic.getProfitRate(new Money(money));
    }
}

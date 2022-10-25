package lotto.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WinningPrize {

    FIRST(6, false, BigDecimal.valueOf(2_000_000_000)),
    SECOND(5, true, BigDecimal.valueOf(30_000_000)),
    THIRD(5, false, BigDecimal.valueOf(1_500_000)),
    FOURTH(4, false, BigDecimal.valueOf(50_000)),
    FIFTH(3, false, BigDecimal.valueOf(5_000)),
    MISS(0, false, BigDecimal.valueOf(0));

    private static final int MATCH_COUNT_FIVE = 5;

    private final int countOfMatch;
    private final boolean matchOfBonus;
    private final BigDecimal price;

    WinningPrize(final int countOfMatch, final boolean matchOfBonus, final BigDecimal price) {

        this.countOfMatch = countOfMatch;
        this.matchOfBonus = matchOfBonus;
        this.price = price;
    }

    public static WinningPrize of(final WinningLotto winningLotto, final Lotto lotto) {

        final int countOfMatch = winningLotto.match(lotto);
        final boolean matchBonus = canMatch(countOfMatch, winningLotto, lotto);
        return getWinningPrize(countOfMatch, matchBonus);
    }

    public static WinningPrize getWinningPrize(int countOfMatch, boolean matchBonus) {

        return Arrays.stream(WinningPrize.values())
                .filter(oper -> oper.countOfMatch == countOfMatch && oper.matchOfBonus == matchBonus)
                .findFirst()
                .orElse(WinningPrize.MISS);
    }

    private static boolean canMatch(final int countOfMatch, final WinningLotto winningLotto, final Lotto lotto) {

        if (countOfMatch == MATCH_COUNT_FIVE) {
            return winningLotto.matchBonus(lotto);
        }
        return false;
    }

    public static Map<WinningPrize, BigDecimal> init() {

        return Collections.unmodifiableMap(Stream.of(values())
                .collect(Collectors.toMap(value -> value, WinningPrize::getPrice)));
    }

    public int getCountOfMatch() {

        return countOfMatch;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public boolean isMatchOfBonus() {

        return matchOfBonus;
    }
}

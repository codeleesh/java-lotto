package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoNumberTest {

    @DisplayName("null 또는 빈 값일 경우 익셉션 처리한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void error_create_lotto(final String input) {

        assertThatThrownBy(() -> new WinningLottoNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 값이 null 또는 빈 공백 입니다.");

    }

    @DisplayName("1등 로또 번호를 5개만 입력 또는 7개 이상 입력하거나 중복된 숫자를 입력하면 익셉션 처리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 2, 4, 5, 6", "1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 6, 7"})
    void error_create_lotto2(final String input) {

        assertThatThrownBy(() -> new WinningLottoNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 숫자를 입력해야 하며 중복 숫자는 입력할 수 없습니다.");
    }

    @DisplayName("입력받은 값으로 1등 로또 번호를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void create_winning_lotto(final int number) {

        final WinningLottoNumber winningLottoNumber = new WinningLottoNumber("1, 2, 3, 4, 5, 6");
        assertThat(winningLottoNumber.getWinnerLottoNumber()).contains(new Number(number));
    }

    @DisplayName("1등 로또와 매칭하는 결과를 구한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, 2, 3, 4, 5, 6:6", "1, 2, 3, 4, 5, 7:5", "1, 2, 3, 4, 7, 8:4", "1, 2, 3, 7, 8, 9:3"
            , "1, 2, 7, 8, 9, 10:2", "1, 7, 8, 9, 10, 11:1", "7, 8, 9, 10, 11, 12:0"}, delimiter = ':')
    void winning_lotto(final String autoLotto, final int countOfMatch) {

        final WinningLottoNumber winningLottoNumber = new WinningLottoNumber("1, 2, 3, 4, 5, 6");
        final LottoNumber lottoNumber = new LottoNumber(autoLotto);

        assertThat(winningLottoNumber.match(lottoNumber)).isEqualTo(countOfMatch);
    }
}
package baseball.core;

import baseball.Enum.ConsoleNumberSizeEnum;
import baseball.exception.IllegalCountException;
import baseball.exception.IllegalRangeException;
import baseball.exception.IllegalTypeException;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;

public class NumberPicker {

    protected NumberPicker() {
    }

    public static NumberPicker createNumberPicker() {
        return new NumberPicker();
    }

    public List<Integer> pickRandomNumber() {
        List<Integer> pickedNumbers = Randoms.pickUniqueNumbersInRange(
            ConsoleNumberSizeEnum.START_INCLUSIVE.getSize(),
            ConsoleNumberSizeEnum.END_INCLUSIVE.getSize(),
            ConsoleNumberSizeEnum.MAX_SIZE.getSize()
        );
        return pickedNumbers;
    }

    public List<Integer> picConsoleNumber(String unprocessedNumber) {
        String[] stringBeforeNumber = unprocessedNumber.split("\\s+");
        validateInput(stringBeforeNumber);
        return Arrays.stream(stringBeforeNumber).map(Integer::parseInt)
            .toList();
    }

    private static void validateInput(String[] stringBeforeNumber) {
        validateIsNumeric(stringBeforeNumber);
        validateRange(stringBeforeNumber);
        validateCount(stringBeforeNumber.length);
    }

    private static void validateIsNumeric(String[] stringBeforeNumber) {
        for (String string : stringBeforeNumber) {
            if (!string.matches("\\d+")) {
                throw new IllegalTypeException();
            }
        }
    }

    private static void validateRange(String[] stringBeforeNumber) {
        for (String string : stringBeforeNumber) {
            int stringToInt = Integer.parseInt(string);
            if (stringToInt < ConsoleNumberSizeEnum.START_INCLUSIVE.getSize() ||
                    stringToInt > ConsoleNumberSizeEnum.END_INCLUSIVE.getSize()) {
                throw new IllegalRangeException();
            }
        }
    }

    private static void validateCount(final int count) {
        if (ConsoleNumberSizeEnum.MAX_SIZE.getSize() < count) {
            throw new IllegalCountException();
        }
    }

}

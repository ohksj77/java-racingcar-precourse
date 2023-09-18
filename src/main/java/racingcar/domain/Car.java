package racingcar.domain;

import racingcar.constant.ErrorMessage;
import racingcar.constant.MoveStatus;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Car {

    private static final Integer MAX_SIZE = 5;
    private static final Integer START_INDEX = 0;
    private static final String DIRECTION_TAG = "-";
    private static final String NAME_DELIMITER = " : ";
    private final String name;
    private int position = 0;

    public Car(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        if (name.length() > MAX_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CAR_NAME_LENGTH.getMessage());
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Car car = (Car) o;
        return position == car.position && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }

    public void move(final MoveStatus moveStatus) {
        if (moveStatus.isMove()) {
            this.position++;
        }
    }

    public Integer getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasMaxPosition(final int maxPosition) {
        return this.position == maxPosition;
    }

    public String getCurrentStatus() {
        return this.name + NAME_DELIMITER +  IntStream.range(START_INDEX, position)
                .mapToObj(i -> DIRECTION_TAG)
                .collect(Collectors.joining());
    }
}

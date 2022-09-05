import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;

public class HorseTest {


    @Test
    void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 1.0, 1.0);
        });
    }

    @Test
    void nullNameExceptionMassage() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 1.0, 1.0);
        });
        assertEquals("Name cannot be null.", throwable.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    void isBlankNameException(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 1.0, 1.0);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    void isBlankNameExceptionMassage(String name) {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 1.0, 1.0);
        });
        assertEquals("Name cannot be blank.", throwable.getMessage());
    }

    @Test
    void negativeSpeedException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Name", -1.0, 1.0);
        });
    }

    @Test
    void negativeSpeedExceptionMassage() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Name", -1.0, 1.0);
        });
        assertEquals("Speed cannot be negative.", throwable.getMessage());
    }

    @Test
    void negativeDistanceException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Name", 1.0, -1.0);
        });
    }

    @Test
    void negativeDistanceExceptionMassage() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Name", 1.0, -1.0);
        });
        assertEquals("Distance cannot be negative.", throwable.getMessage());
    }

    @Test
    void getName() {
        String name = "Лошадь";
        Horse horse = new Horse(name, 1.0, 1.0);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeed() {
        double speed = 1.2;
        Horse horse = new Horse("Ауф", speed, 1.0);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        double distance = 1.3;
        Horse horse = new Horse("Иа", 1.0, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void getZeroDistance() {
        Horse horse = new Horse("Пятачек", 1.0);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    void moveVerifyGetRandomMethod() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Rabbit", 1.0);
            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"5,2,0.6,6.2",
            "4,1,0.7,4.7"})
    void checkTheCorrectnessOfTheFormula(double distance, double speed, double resultRandom, double expected) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(resultRandom);

            Horse horse = new Horse("Rabbit", speed, distance);
            horse.move();
            assertEquals(expected, horse.getDistance());
        }
    }
}

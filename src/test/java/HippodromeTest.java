import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    @Test
    void nullHorsesException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome((null));
        });
    }

    @Test
    void nullHorsesExceptionMassage() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", throwable.getMessage());
    }

    @Test
    void isEmptyHorsesException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome((new ArrayList<>()));
        });
    }

    @Test
    void isEmptyHorsesExceptionMassage() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
        assertEquals("Horses cannot be empty.", throwable.getMessage());
    }

    @Test
    void getHorses() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        ArrayList<Horse> horses = new ArrayList<>();
        Horse horseWinner = new Horse("Лошадка1", 1.0, 2.0);
        horses.add(horseWinner);
        horses.add(new Horse("Лошадка2", 1.0, 1.0));
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horseWinner, hippodrome.getWinner());
    }
}

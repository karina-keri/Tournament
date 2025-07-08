import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setup() {
        game = new Game();
        game.register(new Player(1, "Alice", 10));
        game.register(new Player(2, "Bob", 15));
        game.register(new Player(3, "Charlie", 10));
    }

    @Test
    public void testRoundPlayer1Wins() {
        int result = game.round("Bob", "Alice");
        assertEquals(1, result);
    }

    @Test
    public void testRoundPlayer2Wins() {
        int result = game.round("Alice", "Bob");
        assertEquals(2, result);
    }

    @Test
    public void testRoundDraw() {
        int result = game.round("Alice", "Charlie");
        assertEquals(0, result);
    }

    @Test
    public void testRoundPlayer1NotRegistered() {
        Exception exception = assertThrows(NotRegisteredException.class, () -> {
            game.round("Unknown", "Alice");
        });
        assertTrue(exception.getMessage().contains("Unknown"));
    }

    @Test
    public void testRoundPlayer2NotRegistered() {
        Exception exception = assertThrows(NotRegisteredException.class, () -> {
            game.round("Alice", "Unknown");
        });
        assertTrue(exception.getMessage().contains("Unknown"));
    }
}
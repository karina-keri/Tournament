import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void shouldRegisterAndFindPlayer() {
        Game game = new Game();
        Player player = new Player(1, "John", 10);
        game.register(player);

        Player found = game.findByName("John");
        assertNotNull(found);
        assertEquals(player, found);
    }

    @Test
    public void shouldThrowIfPlayerNotRegistered() {
        Game game = new Game();
        game.register(new Player(1, "Alice", 15));

        assertThrows(NotRegisteredException.class, () -> {
            game.round("Alice", "Bob");
        });
    }

    @Test
    public void shouldReturn1WhenFirstPlayerWins() {
        Game game = new Game();
        game.register(new Player(1, "Alice", 20));
        game.register(new Player(2, "Bob", 15));

        int result = game.round("Alice", "Bob");
        assertEquals(1, result);
    }

    @Test
    public void shouldReturn2WhenSecondPlayerWins() {
        Game game = new Game();
        game.register(new Player(1, "Alice", 10));
        game.register(new Player(2, "Bob", 15));

        int result = game.round("Alice", "Bob");
        assertEquals(2, result);
    }

    @Test
    public void shouldReturn0WhenDraw() {
        Game game = new Game();
        game.register(new Player(1, "Alice", 15));
        game.register(new Player(2, "Bob", 15));

        int result = game.round("Alice", "Bob");
        assertEquals(0, result);
    }

    @Test
    public void shouldOverwritePlayerOnDuplicateName() {
        Game game = new Game();
        Player p1 = new Player(1, "Charlie", 10);
        Player p2 = new Player(2, "Charlie", 20);

        game.register(p1);
        game.register(p2);

        Player found = game.findByName("Charlie");
        assertEquals(p2, found);
    }

        @Test
        public void testRegisterNullPlayer() {
            Game game = new Game();
            assertThrows(NullPointerException.class, () -> {
                game.register(null);
            });
        }

        @Test
        public void testFindByNameReturnsNullIfNotFound() {
            Game game = new Game();
            game.register(new Player(1, "Anna", 10));
            assertNull(game.findByName("NonExistent"));
        }

        @Test
        public void testRoundThrowsIfFirstPlayerNameIsNull() {
            Game game = new Game();
            game.register(new Player(1, "Anna", 10));
            assertThrows(NotRegisteredException.class, () -> {
                game.round(null, "Anna");
            });
        }

        @Test
        public void testRoundThrowsIfSecondPlayerNameIsNull() {
            Game game = new Game();
            game.register(new Player(1, "Anna", 10));
            assertThrows(NotRegisteredException.class, () -> {
                game.round("Anna", null);
            });
        }

        @Test
        public void testRegisterMultiplePlayers() {
            Game game = new Game();
            Player p1 = new Player(1, "A", 10);
            Player p2 = new Player(2, "B", 20);
            Player p3 = new Player(3, "C", 30);

            game.register(p1);
            game.register(p2);
            game.register(p3);

            assertEquals(p1, game.findByName("A"));
            assertEquals(p2, game.findByName("B"));
            assertEquals(p3, game.findByName("C"));
        }
    }
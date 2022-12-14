package EasyTasks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

/*
import org.junit.jupiter.api.BeforeEach;
@BeforeEach (англ. «перед каждым») — каждый раз перед началом каждого теста;
@BeforeAll (англ. «перед всеми») — один раз до запуска всех тестов;
@AfterEach (англ. «после каждого») — каждый раз после окончания каждого теста;
@AfterAll (англ. «после всех») — один раз после окончания всех тестов.
*/

public class TestjUnit {
    BankAccount account = new BankAccount("a", "b");

    @Test
    public void shouldNotBeBlockedWhenCreated() {
        BankAccount account = new BankAccount("a", "b");
        assertFalse(account.isBlocked());
    }

    @Test
    public void shouldReturnZeroAmountAfterActivation() {
        BankAccount account = new BankAccount("a", "b");
        account.activate("RUB");
        assertEquals(new Integer(0), account.getAmount());
        assertEquals("RUB", account.getCurrency());
    }

    @Test
    public void shouldBeBlockedAfterBlockIsCalled() {
        BankAccount account = new BankAccount("a", "b");
        account.activate("RUB");
        account.block();
        assertTrue(account.isBlocked());
    }

    @Test
    public void shouldReturnFirstNameThenSecondName() {
        BankAccount account = new BankAccount("a", "b");
        account.activate("RUB");

        String[] arrayOne = new String[]{"a", "b"};
        String[] arrayTwo = account.getFullName();

        assertArrayEquals(arrayTwo, arrayOne, "Массивы не равны!");
    }

    @Test
    public void shouldReturnNullAmountWhenNotActive() {
        BankAccount account = new BankAccount("a", "b");
        assertNull(account.getCurrency());

        final IllegalStateException exception = assertThrows(
                // класс ошибки
                IllegalStateException.class,

                // создание и переопределение экземпляра класса Executable
                new Executable() {
                    @Override
                    public void execute() {
                        // здесь блок кода, который хотим проверить
                        // при делении на 0 ожидаем IllegalStateException
                        account.getAmount();
                    }
                });
        assertEquals("Счёт не активирован.", exception.getMessage());
    }
}

class BankAccount {

    private boolean isBlocked = false;
    private Integer amount;
    private String currency;

    private final String firstName;
    private final String secondName;

    public BankAccount(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public void block() {
        this.isBlocked = true;
    }

    public void activate(String currency) {
        this.amount = 0;
        this.currency = currency;
    }

    public Integer getAmount() {
        if (amount == null) {
            throw new IllegalStateException("Счёт не активирован.");
        }
        return this.amount;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public String[] getFullName() {
        return new String[]{firstName, secondName};
    }
}

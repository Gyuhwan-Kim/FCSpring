import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DollarCalculatorTest {
    @Mock
    public MarketApi marketApi;

    @BeforeEach
    public void init(){
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void testHello(){
        System.out.println("test hello");
    }

    @Test
    public void dollarTest(){
        System.out.println("hello JUnit");

        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);
        System.out.println(calculator.sum(10, 10));

        Assertions.assertEquals(22000, calculator.sum(10, 10));
        Assertions.assertEquals(0, calculator.sub(10, 10));
    }

    @Test
    public void mockTest(){
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);
        System.out.println(calculator.sum(10, 10));

        Assertions.assertEquals(60000, calculator.sum(10, 10));
        Assertions.assertEquals(0, calculator.sub(10, 10));
    }
}
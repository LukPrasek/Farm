import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputDataFromConsoleForAnimalTest {
    private ScannerAsker scannerAsker;
    private InputDataFromConsoleForAnimal inputDataFromConsoleForAnimal = new InputDataFromConsoleForAnimal();

    @Before
    public void setup() {
        scannerAsker = mock(ScannerAsker.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnExceptionForWrongEnumName() {
        //given
        //when
        when(scannerAsker.askString("Select the species from the list by providing the name")).thenReturn("cata");
        //than
        inputDataFromConsoleForAnimal.getAnimalDataFromUserConsole(scannerAsker);
    }

    @Test
    public void shouldCreateAndReturnAnimalObject() {
        //given

        //when
        when(scannerAsker.askString("Select the species from the list by providing the name")).thenReturn("cat");
        when(scannerAsker.askString("Provide the age")).thenReturn("3");
        when(scannerAsker.askString("Is the animal vaccinated - true or false")).thenReturn("true");
       //than
        Assert.assertEquals(inputDataFromConsoleForAnimal.getAnimalDataFromUserConsole(scannerAsker).getAge(), 3);
        Assert.assertEquals(inputDataFromConsoleForAnimal.getAnimalDataFromUserConsole(scannerAsker).getAnimalSpecies().toString(), "CAT");

    }
}
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BarnManagerTest {
    BarnManager barnManager = new BarnManager();

    @Test
    public void shouldCreateNewBarnObject() throws FarmException {
        //given
        String barnName = "Stodola";
        ScannerAsker scannerAsker = mock(ScannerAsker.class);
        //when
        when(scannerAsker.askString("Give the name of the barn")).thenReturn("Stodola");
        //than
        Assert.assertEquals(barnManager.createNewBarn().getName(), barnName);

    }

}
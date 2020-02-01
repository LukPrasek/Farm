import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BarnManagerTest {
    private BarnManager barnManager;
    private ScannerAsker scannerAsker;
    private String pathToFolder;
    DatabaseReader databaseReader;

    @Before
    public void setUp() {
        scannerAsker = mock(ScannerAsker.class);
        barnManager = new BarnManager();
        pathToFolder = ".\\src\\test\\resourcesForTest\\";
        databaseReader = new DatabaseReader(pathToFolder);

    }

    @Test
    public void shouldCreateNewBarnObject() throws FarmException {
        //given
        String barnName = "Stodola";
        //when
        when(scannerAsker.askString("Give the name of the barn")).thenReturn("Stodola");
        //than
        Assert.assertEquals(barnManager.createNewBarn(scannerAsker).getName(), barnName);

    }

    @Test(expected = WrongIdException.class)
    public void shouldThrowWrongIdException() throws WrongIdException, FarmException {
        //given

        //when
        when(scannerAsker.askInt("Provide the barn ID to be deleted")).thenReturn(10);
        databaseReader.readFilesFromFolder(pathToFolder);
        //than
        barnManager.deleteSelectedBarn(scannerAsker, pathToFolder);
    }

    @Test
    public void shouldReturnBarn2WithMostAnimals() throws FarmException {
        //given
        String barnNameWithMostAnimals = "Stajnia";

        //when
        when(scannerAsker.askInt("Provide the barn ID to be deleted")).thenReturn(10);
        databaseReader.readFilesFromFolder(pathToFolder);
        //than
        ;
        Assert.assertEquals(barnManager.showBarnWithMostAnimals().getName(), barnNameWithMostAnimals);
    }
}
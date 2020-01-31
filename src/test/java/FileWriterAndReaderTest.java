import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


public class FileWriterAndReaderTest {
    FileWriterAndReader fwr;
    String pathToFolder;

    @Before
    public void setUp() {
        pathToFolder = ".\\src\\test\\resourcesForTest\\";
        fwr = new FileWriterAndReader(pathToFolder);
    }

    @Test
    public void shouldReturnStringTableWithFileNames() {//getAllFilesFromCatalog
        //given
        int expectedTableSize = 3;
        //when
        String[] actualTable = fwr.getAllFilesFromCatalog();
        //then
        Assert.assertEquals(expectedTableSize, actualTable.length);
    }

    @Test
    public void shouldReturnTrueWhileCheckingFileIsNotEmpty() {
        //given
        String pathToFile = pathToFolder+"1.txt";
        //when
        boolean actual = fwr.checkIfFileIsNotEmpty(pathToFile);
        //then
        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseWhileCheckingFileIsNotEmpty() {
        //given
        String pathToFile = pathToFolder+"0.txt";
        //when
        boolean actual = fwr.checkIfFileIsNotEmpty(pathToFile);
        //then
        Assert.assertFalse(actual);
    }

    @Test
    public void shouldWriteBarnObjectToFile() throws FarmException {
        //given
        String pathToTempFile = pathToFolder+"3.txt";
        Barn barnMock = Mockito.mock(Barn.class);
        FileWriterAndReader fwr = new FileWriterAndReader(pathToFolder);
        //when
        when(barnMock.getId()).thenReturn(3);
        fwr.writeObjectToFile(barnMock, pathToFolder);
        File file = new File(pathToTempFile);
        //then
        assertTrue(file.exists());
        fwr.deleteFile(pathToTempFile);

    }

    @Test(expected = FarmException.class)
    public void shouldNotReadFile() throws FarmException {
        //given
        String pathToTempFile = pathToFolder+"3.txt";
        File file = new File(pathToTempFile);
        fwr.readFile(file);
    }
}

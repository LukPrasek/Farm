import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class FileWriterAndReaderTest {
    FileWriterAndReader fwr;
    String pathToFolder;

    @Before
    public void setUp() {
        pathToFolder = ".\\src\\test\\resourcesForTest";
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
        String pathToFile =".\\src\\test\\resourcesForTest\\1.txt";
        //when
        boolean actual = fwr.checkIfFileIsNotEmpty(pathToFile);
        //then
        Assert.assertTrue(actual);
    }
    @Test
    public void shouldReturnFalseWhileCheckingFileIsNotEmpty() {
        //given
        String pathToFile =".\\src\\test\\resourcesForTest\\3.txt";
        //when
        boolean actual = fwr.checkIfFileIsNotEmpty(pathToFile);
        //then
        Assert.assertFalse(actual);
    }

}
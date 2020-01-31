import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseReaderTest {
    private FileWriterAndReader fwr;
    private String pathToFolder;
    private DatabaseReader databaseReader;

    @Before
    public void setup() {
        pathToFolder = ".\\src\\test\\resourcesForTest\\";
        fwr = new FileWriterAndReader(pathToFolder);
        databaseReader = new DatabaseReader(pathToFolder);
    }

    @Test
    public void shouldReturnTwoBarnsAsReadFromFolder() throws FarmException {
        //given
        int numberOfBarnsStoredInFiles = 2;
        //when
        databaseReader.readFilesFromFolder(pathToFolder);
        //than
        Assert.assertEquals(DatabaseReader.getBarnList().size(), numberOfBarnsStoredInFiles);
    }

    @Test
    public void shouldReturnFiveAnimalsAsStoredInLinkedList() throws FarmException {
        //given
        int numberOfAnimalsStoredInFiles = 7;
        //when
        databaseReader.readFilesFromFolder(pathToFolder);
        //than
        Assert.assertEquals(DatabaseReader.getAllAnimalList().size(), numberOfAnimalsStoredInFiles);
    }
}

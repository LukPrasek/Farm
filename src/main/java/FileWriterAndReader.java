import java.io.*;

public class FileWriterAndReader {

    private String path;
    private File fileToFolder;


    public FileWriterAndReader(String pathToFolder) {
        this.path = pathToFolder;
        fileToFolder = new File(pathToFolder);
    }

    public String[] getAllFilesFromCatalog() {
        File file = new File(path);
        return file.list();
    }

    public boolean checkIfFileIsNotEmpty(String pathToFile) {
        File file = new File(pathToFile);
        if (file.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void writeObjectToFile(Barn barn, String pathToFolder) throws FarmException {
        BufferedWriter bufferedWriter;
        File fileToFile = new File(pathToFolder + "\\" + barn.getId() + ".txt");
        try {
            FileWriter fileWriter = new FileWriter(fileToFile, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(barn.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("File saved");

        } catch (IOException e) {
            throw new FarmException("Wrong path");
        }
    }

    public String readFile(File file) throws FarmException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(file.getPath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            fileReader.close();
        } catch (IOException e) {
            throw new FarmException("Wrong path to file");
        }
        return stringBuilder.toString();
    }

    public void deleteFile(String pathToFile) {
        File file = new File(pathToFile);
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }
}

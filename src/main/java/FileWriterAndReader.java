import java.io.*;

public class FileWriterAndReader {

    private String path;
    private File fileToFolder;
    private File fileToFile;


    public FileWriterAndReader(String pathToFolder) {
        this.path = pathToFolder;
        fileToFolder = new File(pathToFolder);
    }

    public void createNewFile(String pathToFile) throws IOException {
        File file = new File(pathToFile);
        file.createNewFile();
    }

    public String[] getAllFilesFromCatalog() {
        File file = new File(path);
        return file.list();
    }

    public boolean checkIfFileIsNotEmpty() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileToFolder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int b = 0;
        try {
            b = fis.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (b == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void writeObjectToFile(Barn barn) {
        BufferedWriter bufferedWriter;
        fileToFile = new File(Menu.getPathToFolder() + "\\" + barn.getId() + ".txt");
        try {

            FileWriter fileWriter = new FileWriter(fileToFile, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(barn.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("File saved");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {

            FileReader fileReader = new FileReader(file.getPath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public void deleteFile(String pathToFile) throws IOException {
        File file = new File(pathToFile);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader fileInputStreamReader = new InputStreamReader(fileInputStream);
        fileInputStream.close();
        fileInputStreamReader.close();
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }
}

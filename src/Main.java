import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        List<File> directoryList = Arrays.asList(
                new File("X://Games1"),
                new File("X://Games1//temp"),
                new File("X://Games1//src"),
                new File("X://Games1//res"),
                new File("X://Games1//savegames"),
                new File("X://Games1//src//main"),
                new File("X://Games1//src//test"),
                new File("X://Games1//res//drawables"),
                new File("X://Games1//res//vectors"),
                new File("X://Games1//res//icons")
        );
        List<File> fileList = Arrays.asList(
                new File("X://Games1//src//main//Main.java"),
                new File("X://Games1//src//main//Utils.java"),
                new File("X://Games1//temp//temp.txt")
        );
        directoryList.stream().forEach(Main::createDirectory);
        fileList.stream().forEach(Main::createFile);

        try (FileWriter log = new FileWriter("X://Games1//temp//temp.txt", false)) {
            log.write(stringBuilder.toString());
            log.flush();
        } catch (IOException ex) {
            stringBuilder.append(ex.getMessage() + '\n');
        }
        try (BufferedReader br = new BufferedReader(new FileReader("X://Games1//temp//temp.txt"))) {
            String s;
            while ((s = br.readLine()) != null) System.out.println(s);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void createFile(File file) {
        try {
            file.createNewFile();
            stringBuilder.append("Файл " + file.getName() + " создан\n");
        } catch (IOException ex) {
            stringBuilder.append(ex.getMessage());
        }
    }

    public static void createDirectory(File dir) {
        if (dir.mkdir())
            stringBuilder.append("Каталог " + dir.getName() + " создан\n");
        else stringBuilder.append("Каталог " + dir.getName() + " не создан\n");
    }
}
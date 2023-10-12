import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        GameProgress game1 = new GameProgress(1, 1, 1, 1.5);
        GameProgress game2 = new GameProgress(100, 10, 2, 168.5);
        GameProgress game3 = new GameProgress(256, 32, 4, 100500.5);
//        for (int i = 1; i < 3; i++) {
//            saveGame("C://dir/Game/savegames/save\\i.dat", game[i]);
//        }
        //можно ли как-то эти 3 строки в цикл засунуть?
        saveGame("C://dir/Game/savegames/save1.dat", game1);
        saveGame("C://dir/Game/savegames/save2.dat", game2);
        saveGame("C://dir/Game/savegames/save3.dat", game3);

        ArrayList<String> list = new ArrayList<>(3);
        list.add("save1.dat");
        list.add("save2.dat");
        list.add("save3.dat");

        zipFile("C://dir/Game//savegames/zip.zip", list);

        deleteFile("C://dir/Game/savegames/save1.dat");
        deleteFile("C://dir/Game/savegames/save2.dat");
        deleteFile("C://dir/Game/savegames/save3.dat");

    }

    public static void saveGame(String address, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(address);
             ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(game);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFile(String zipAddress, List<String> listOfObjects) {

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipAddress));
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C://dir/Game/savegames/save3.dat"))
        ) {
            for (String fileName : listOfObjects) { //если замечания к циклу, то нужны конкретные подсказки, пожалуйста
                zos.putNextEntry(new ZipEntry("packed_" + fileName));
                byte[] buffer = new byte[bis.available()];
                bis.read(buffer);
                zos.write(buffer);
                zos.closeEntry();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteFile(String address) {
        File fileToDelete1 = new File(address);
        if (fileToDelete1.delete()) {
            int test = 1;
        }
    }

}
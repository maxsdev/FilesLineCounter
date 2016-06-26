package maxsdev.linecounter.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class Counter {

    HashMap<String, Integer> map;
    Path homePath;

    public Counter() {
        map = new HashMap<>();
    }

    private int getLinesCountInFile(File file) throws FileNotFoundException {
        int count = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(!line.equals("")) count++;
        }
        map.put(homePath.relativize(file.toPath()).toString(), count);
        return count;
    }

    private int fileBypass(File path) throws FileNotFoundException {
        if(path.isFile()){
            return getLinesCountInFile(path);
        }

        int count = 0;
        File[] files = path.listFiles();
        if(files == null) return 0;
        for(File f : files){
            count += fileBypass(f);
        }

        return count;
    }

    public int getLinesCount(String path) throws FileNotFoundException {
        File file = new File(path);
        if(!file.isFile() && !file.isDirectory()){
            throw new FileNotFoundException("No such file or directory.");
        }
        homePath = Paths.get(path);
        return fileBypass(file);
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }
}

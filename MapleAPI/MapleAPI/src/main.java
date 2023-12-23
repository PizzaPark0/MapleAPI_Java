import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("MapleAPI\\src\\config.txt"));
        String apikey = reader.readLine().split(":")[1].strip();

        MapleAPIViewer m = new MapleAPIViewer(apikey);
        System.out.println(m.getCharOcid("깜시랑"));
        System.out.println(m.getCharData(m.getCharOcid("깜시랑"), "2023-12-22", "propensity"));

        System.out.println(m.getGuildOguildid("생활방식", "루나"));
        System.out.println(m.getGuildData(m.getGuildOguildid("생활방식", "루나"), "2023-12-22", "basic"));

        System.out.println(m.getUnionData(m.getCharOcid("깜시랑"), "2023-12-22", "union-raider"));
    }
}
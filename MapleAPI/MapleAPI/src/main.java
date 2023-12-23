import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("MapleAPI\\src\\config.txt"));
        String apikey = reader.readLine().split(":")[1].strip();

        MapleAPI m = new MapleAPI(apikey);
        System.out.println(m.getCharOcid("이름"));
        System.out.println(m.getCharData(m.getCharOcid("이름"), "2023-12-22", "propensity"));

        System.out.println(m.getGuildOguildid("길드", "서버"));
        System.out.println(m.getGuildData(m.getGuildOguildid("길드", "서버"), "2023-12-22", "basic"));

        System.out.println(m.getUnionData(m.getCharOcid("이름"), "2023-12-22", "union-raider"));
    }
}

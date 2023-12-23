import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MapleAPIViewer{
    String apiKey;
    HttpURLConnection connection;

    MapleAPIViewer(String apiKey)
    {
        this.apiKey = apiKey;
    }

    private String activateBuffer(BufferedReader bufferedReader) //get json format string, from response
    {
        StringBuffer stringBuffer = new StringBuffer();
        String inputLine;
        try
        {
            while ((inputLine = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(inputLine);
            }
            bufferedReader.close();

            return stringBuffer.toString();
        }
        catch(Exception e)
        {
            return "0";
        }
    }

    private JSONObject activateParser(String jsonText) //parse string as json object
    {
        JSONParser parser = new JSONParser();
        String jText = jsonText;
        try
        {
            Object obj = parser.parse(jText);
            return (JSONObject) obj;
        }
        catch(Exception e)
        {
            return new JSONObject();
        }
    }

    private void setConnection(URL url) //set method, head url request (not send request)
    {
        try
        {
            this.connection = (HttpURLConnection) url.openConnection();
            this.connection.setRequestMethod("GET");
            this.connection.setRequestProperty("x-nxopen-api-key", this.apiKey);
        }
        catch(Exception e)
        {
        }
        
    }

    public String getCharOcid(String nickname) //get ocid from nickname
    {
        try
        {
            String characterName = URLEncoder.encode(nickname, StandardCharsets.UTF_8);
            URL url = new URL("https://open.api.nexon.com/maplestory/v1/id?character_name="+characterName);
            this.setConnection(url);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            JSONObject responseJson = this.activateParser(this.activateBuffer(bufferedReader));

            return responseJson.get("ocid").toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "0";
        }
    }

    public String getGuildOguildid(String guildname, String worldname) //get oguild id from guildname, world
    {
        try
        {
            String guild = URLEncoder.encode(guildname, StandardCharsets.UTF_8);
            String world = URLEncoder.encode(worldname, StandardCharsets.UTF_8);
            URL url = new URL("https://open.api.nexon.com/maplestory/v1/guild/id?guild_name="+guild + "&world_name="+world);
            this.setConnection(url);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            JSONObject responseJson = this.activateParser(this.activateBuffer(bufferedReader));

            return responseJson.get("oguild_id").toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "0";
        }
    }

    public JSONObject getCharData(String ocid, String date, String dataType)
    {
        /*
        [date]
        "YYYY-MM-DD" format string, after 2023-12-21, before a day ago
        
        [dataType]
        basic, popularity, stat, hyer-stat, propensity, ability, item-equiment,
        cashitem-equipment, symbol-equipment, set-effect, beauty-equipment, pet-equipment,
        skill, link-skill, vmatrix, hexametrix, hexametrix-stat, dojang
        */
        try
        {
            URL url = new URL("https://open.api.nexon.com/maplestory/v1/character/"+dataType+"?ocid="+ocid+"&date="+date);
            this.setConnection(url);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((this.connection.getInputStream())));
            JSONObject responseJson = this.activateParser(this.activateBuffer(bufferedReader));

            return responseJson;
        }
        catch(Exception e)
        {
            return new JSONObject();
        }
    }

    public JSONObject getGuildData(String oguildid, String date, String dataType)
    {
        /*
        [date]
        "YYYY-MM-DD" format string, after 2023-12-21, before a day ago

        [dataType]
        basic
        */
        try
        {
            URL url = new URL("https://open.api.nexon.com/maplestory/v1/guild/"+dataType+"?oguild_id="+oguildid+"&date="+date);
            this.setConnection(url);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((this.connection.getInputStream())));
            JSONObject responseJson = this.activateParser(this.activateBuffer(bufferedReader));

            return responseJson;
        }
        catch(Exception e)
        {
            return new JSONObject();
        }
    }

    public JSONObject getUnionData(String ocid, String date, String dataType)
    {
        /*
        [date]
        "YYYY-MM-DD" format string, after 2023-12-21, before a day ago

        [dataType]
        union, union-raider
        */
        try
        {
            URL url = new URL("https://open.api.nexon.com/maplestory/v1/user/"+dataType+"?ocid="+ocid+"&date="+date);
            this.setConnection(url);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((this.connection.getInputStream())));
            JSONObject responseJson = this.activateParser(this.activateBuffer(bufferedReader));

            return responseJson;
        }
        catch(Exception e)
        {
            return new JSONObject();
        }
    }

}
package CovidMonitorApp.CovidApp.covidAPI;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    public static String getJsonContent(String path){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            int hasRead = 0;
            byte[]buf = new byte[16384];
            while ((hasRead = is.read(buf))!=-1){
                baos.write(buf,0,hasRead);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toString();
    }
}

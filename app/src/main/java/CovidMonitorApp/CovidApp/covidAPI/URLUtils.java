package CovidMonitorApp.CovidApp.covidAPI;

public class URLUtils {

    public static final  String KEY = "913b4ea3a17c45baa5316871e4cd80de";
    public static String pre_url = "https://api.covidactnow.org/v2/state/";

    public static String getJson_url(String state){
        String url = pre_url + state + ".json?apiKey="+KEY;
        return url;
    }
}

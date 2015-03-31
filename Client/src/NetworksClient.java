import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class NetworksClient {

    private URL sendURL, receiveURL, sampleURL;

    public NetworksClient() throws MalformedURLException {
		sendURL = new URL("http://www.thekyzrproject.com/dbadd");
		receiveURL = new URL("http://www.thekyzrproject.com/dbreturn");
        sampleURL = new URL("http://www.thekyzrproject.com/info");
    }

    private String access(String query, URL requestURL) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();

        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", String.valueOf(query.length()));

        OutputStream os = connection.getOutputStream();
        os.write(query.getBytes());
        os.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line = "";
        String finalResponse = "";

        while((line = br.readLine()) != null) {
            finalResponse += line;
        }

        return finalResponse;

    }

    public String sampleSend(String message) {
        String encodedData = "";
        try {
            encodedData = "message=" + URLEncoder.encode(message, "UTF-8");
        } catch(Exception e) {
        	e.printStackTrace();
            return "Failed to encode data";
        }

        try {
            return access(encodedData, sampleURL);
        } catch (IOException e) {
        	e.printStackTrace();
            return "Failed to connect to server and send data";
        }
    }
    
    public String postData(String id1, String id2, double lat, double lng) {
        String[] data = {id1, id2, Double.toString(lat), Double.toString(lng)};
        String[] prefixes = {"id1=", "&id2=", "&lat=", "&lng="};
        String encodedData = "";
        try {
            for(int i = 0; i < 4; i++) {
            	String encode = URLEncoder.encode(data[i], "UTF-8");
            	encodedData += prefixes[i] + encode;
            }
        } catch(Exception e) {
        	e.printStackTrace();
            return "Failed to encode data";
        }

        try {
            return access(encodedData, sendURL);
        } catch (IOException e) {
        	e.printStackTrace();
            return "Failed to connect to server and send data";
        }
    }
    
    public String requestData(String id) {
    	String encodedData = "";
    	try {
    		encodedData = "old_id=" + URLEncoder.encode(id, "UTF-8");
    	} catch(Exception e) {
        	e.printStackTrace();
            return "Failed to encode data";
        }

        try {
            return access(encodedData, receiveURL);
        } catch (IOException e) {
        	e.printStackTrace();
            return "Failed to connect to server and send data";
        }
    }
}

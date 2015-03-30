import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


public class NetworksClient {
	
	private URI sendURI, receiveURI;
	
	public NetworksClient() throws URISyntaxException{
//		sendURI = new URI("http://www.thekyzrproject.com/dbadd");
//		receiveURI = new URI("http://www.thekyzrproject.com/dbreturn");
		sendURI = new URI("http://127.0.0.1:5000/dbadd");
		receiveURI = new URI("http://127.0.0.1:5000/dbreturn");
	}
	
	private String access(ArrayList<NameValuePair> params, URI inputURI) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(inputURI);
		
		post.setEntity(new UrlEncodedFormEntity(params));
		
		CloseableHttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		
		if (entity != null) {
		    InputStream instream = entity.getContent();
		    try {
		        BufferedReader br = new BufferedReader(new InputStreamReader(instream));
		        
		        String line = "";
		        String finalResponse = "";
		        
		        while((line = br.readLine()) != null) {
		        	finalResponse += line;
		        }
		        
		        instream.close();
		        
		        return finalResponse;
		    } finally {
		        instream.close();
		    }
		}
	
	return "Failure Connecting to Server";
	}
	
	public String sendData(String id1, String id2, double latitude, double longitude) {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id1", id1));
		params.add(new BasicNameValuePair("id2", id2));
		params.add(new BasicNameValuePair("lat", Double.toString(latitude)));
		params.add(new BasicNameValuePair("lng", Double.toString(longitude)));
			
		try {
			return access(params, sendURI);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "Failure Formatting Request";
		
	}
	
	public String requestData(String id) {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("old_id", id));
		
		try {
			return access(params, receiveURI);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "Failure Formatting Request";
	}
}
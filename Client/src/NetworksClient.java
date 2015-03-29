import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
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
	
	private URL url = null;
	
	public NetworksClient(URL u) {
		url = u;
	}
	
	public String sendMessage(String message) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(new URI(url.toString()));
			
			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("message", message));
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
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "Failure";
	}
}
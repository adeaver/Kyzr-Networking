import java.net.URL;

public class NetworksMain {
	public static void main(String[] args) {
		NetworksClient networksClient = null;
		
		try {
			networksClient = new NetworksClient();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(networksClient != null) {
			String response1 = networksClient.sampleSend("This is a test");
			System.out.println(response1);
		} else {
			System.out.println("Failed.");
		}
		
		System.out.println("End");
	}
}

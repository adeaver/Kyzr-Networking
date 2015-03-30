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
			String response1 = networksClient.sendData("abc", "123", 1.123, 54.342);
			String response2 = networksClient.requestData("abc");
		} else {
			System.out.println("Failed.");
		}
		
		System.out.println("End");
	}
}

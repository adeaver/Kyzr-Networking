import java.net.URL;

public class NetworksMain {
	public static void main(String[] args) {
		NetworksClient networksClient = null;
		
		try {
			networksClient = new NetworksClient(new URL("http://www.thekyzrproject.com/info"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(networksClient != null) {
			System.out.println("Entered");
			String response = networksClient.sendMessage(".skcus noryB");
			System.out.println(response);
		} else {
			System.out.println("Didn't work.");
		}
		
		System.out.println("End");
	}
}

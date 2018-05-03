package 练习;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IP {

	public static void main(String[] args) {
		
		try {
			InetAddress inet = InetAddress.getLocalHost();
			System.out.println(inet.getHostAddress());
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
		
	}
}

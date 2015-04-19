package timetable_mvc_client.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

public class ClientCredentials {
	public static HttpHeaders getRequestHeaderWithAuthorization(){
		String plainCreds = "user:p@ssw0rd";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		return headers;
	}
}

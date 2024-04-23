package cl.tcadv.urlshortener.exeption;

public class UrlNotFoundException extends Exception {

	private static final long serialVersionUID = 7688723471855180991L;

	public UrlNotFoundException(String message) {
		super(message);
	}
}

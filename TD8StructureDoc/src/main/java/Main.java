
public class Main {
	
	public static void main(String[] args) {
		APIContact a = new APIContact();
		
		System.out.println(a.RetrieveAlbum("Cher", "Believe"));
		System.out.println(a.RetrieveTitre("Cher", "Believe"));
	}
}

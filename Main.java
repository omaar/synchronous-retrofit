import retrofit.Callback;
import responses.*;
import java.util.Scanner;

public class Main{

	public static void main(String[] args) {

		GiphyService service = new GiphyService();
		Scanner readC = new Scanner(System.in);

		System.out.println("Busca un GIF: ");
		String term = readC.nextLine();
		System.out.println("Numero: ");
			service.searchGifs(term, readC.nextInt());
	}

}

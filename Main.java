import retrofit.Callback;
import responses.*;
import retrofit.RetrofitError;
import retrofit.client.Response;
import java.awt.Desktop;
import java.net.URI;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String happiness, term;
		int num;
		while(true){
			System.out.println("Bienvenido al Memerama!");
			System.out.println("Sobre que quieres buscar Gifs?: ");
			term = sc.nextLine();
			System.out.println("Cuantos quieres abrir?: ");
			num = sc.nextInt();
			System.out.println("Buscando tus Gifs feos...");
			Main.searchGifs(term, num);

			System.out.println("Contento? Bye [y/n]: ");
			sc.next();
			happiness = sc.nextLine();
			
			if(happiness.equalsIgnoreCase("y")){
				System.out.println("Vale, bye");
				break;
			}else{
				continue;
			}
			// System.out.println("Random o los primeros N? :");
		}
	}

	public static void searchGifs(String term, int numImages){
		GiphyService service = new GiphyService();
		final int num = numImages;

		GiphyResponse gifs = service.searchGifs(term);

		int i = 0;
		for (Gif gif : gifs.getData()) {
			i++;
			if(i <= num){
				Main.openInBrowser(gif.images.fixed_height.url);
			}
		}
	}

	public static void openInBrowser(String url){
		if(Desktop.isDesktopSupported()){
			try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
			
		}
	}
}
import java.awt.Desktop;
import java.net.URI;
import java.io.IOException;
import java.net.URISyntaxException;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.RestAdapter;
import retrofit.http.Header;
import retrofit.http.GET;
import retrofit.Callback;
import retrofit.http.Query;
import responses.*;

public class GiphyService {
	private RestAdapter adapter;
	private Service service;

	public RestAdapter getAdapter(){
		return this.adapter;
	}

	public void setAdapter(RestAdapter adapter){
		this.adapter = adapter;
	}

	public Service getService(){
		return this.service;
	}

	public void setService(Service service){
		this.service = service;
	}

	public interface Service{
		@GET("/v1/gifs/search")
		GiphyResponse search(
			@Query("api_key") String apiKey,
			@Query("q") String query
		);
	}

	public GiphyService(){
		adapter = new RestAdapter.Builder()
			.setEndpoint(Constants.GIPHY_ENDPOINT)
			.build();

		service = adapter
			.create(Service.class);
	}

	public GiphyResponse searchGifs(String term){
		return service.search(Constants.GIPHY_KEY, term);
	}

	public void searchGifs(String term, int numImages){

		GiphyResponse gifs = searchGifs(term);
		int i = 0;

		for (Gif gif : gifs.getData()) {
			i++;
			if(i <= numImages){
				openInBrowser(gif.images.fixed_height.url);
			}
		}
	}

	public void openInBrowser(String url){
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

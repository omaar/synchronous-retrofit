import retrofit.RestAdapter;
import retrofit.http.Header;
import retrofit.http.GET;
import retrofit.Callback;
import retrofit.http.Query;
import responses.*;

public class GiphyService {
	private RestAdapter adapter;
	private Service service;

	public interface Service{
		@GET("/v1/gifs/search")
		GiphyResponse search(
			@Query("api_key") String apiKey,
			@Query("q") String query
		);
			// @Query("q") String query, Callback<GiphyResponse> cb);
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
}
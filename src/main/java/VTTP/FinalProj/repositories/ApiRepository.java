package VTTP.FinalProj.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class ApiRepository {

    //TIH set apikey as X-API_KEY in headers, query dataset param and keyword param
    
    @Value("${TIH_API_KEY}")
    private String TIH_KEY;

    private String TIH_MULTI_SEARCH = "https://api.stb.gov.sg/content/common/v2/search";

    public String searchRestaurantByKeyword(String keyword){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);
        headers.set("X-API-KEY", TIH_KEY);
        String urlToSend = UriComponentsBuilder.fromHttpUrl(TIH_MULTI_SEARCH).queryParam("keyword", keyword).queryParam("dataset", "food_beverages,tours,shops,attractions,events").toUriString();
        System.out.println(urlToSend);
        ResponseEntity<String> resp = restTemplate.exchange(urlToSend, HttpMethod.GET, entity, String.class);
        return resp.getBody();
    }
}

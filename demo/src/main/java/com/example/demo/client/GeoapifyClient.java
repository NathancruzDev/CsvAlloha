package com.example.demo.client;

<<<<<<< HEAD


public class GeoapifyClient extends GeoapifyClientHttp{
    private final String API_KEY= "83987d49b8d9472a96d632314ee496cc";
    private final String BASE_URL="https://api.geoapify.com/v1/routing";


    public String distance(Double longitude1 , Double latitude1, Double  longitude2, Double latitude2)throws Exception{
        String url=String.format("\"%s?waypoints=%f,%f|%f,%f&mode=drive&apiKey=%s\"",BASE_URL, latitude1, longitude1, latitude2, longitude2, API_KEY);
        return get(url);
    }
=======
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;

/*
* https://myprojects.geoapify.com/projects
 */
public class GeoapifyClient {
    private final String API_KEY="83987d49b8d9472a96d632314ee496cc";

>>>>>>> 10289ec (conflicts merge)
}

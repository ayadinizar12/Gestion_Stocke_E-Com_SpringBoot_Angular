package com.ayed.gestionstock.config;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Configuration
public class FlickrConfig {

    @Value("${flickr.apiKey}")
    private String apiKey;
    @Value("${flickr.apiSecret}")
    private String apiSecret;
    @Value("${flickr.appKey}")
    private String appKey;
    @Value("${flickr.appSecret}")
    private String appSecret;
    @Autowired
    private Flickr flickr;

    /**@Bean
    public Flickr getFlickr() throws IOException, ExecutionException, InterruptedException, FlickrException {
        Flickr flickr=new Flickr(apiKey,apiSecret,new REST());

        OAuth10aService  service= new ServiceBuilder(apiKey)
             .apiSecret(apiSecret)
             .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));


        final Scanner scanner=new Scanner(System.in);
        final OAuth1RequestToken RequestToken=service.getRequestToken();
        final String s=service.getAuthorizationUrl(RequestToken);


        System.out.println(s);
        System.out.println("Paste -->> ");

        final String verif=scanner.nextLine();

        OAuth1AccessToken Token=service.getAccessToken(RequestToken,verif);

        System.out.println(Token.getToken());
        System.out.println(Token.getTokenSecret());

        Auth auth=flickr.getAuthInterface().checkToken(Token);

        System.out.println("=========================================");
        System.out.println(auth.getToken());
        System.out.println(auth.getTokenSecret());

        return flickr;
 }*/
    @Bean
    public Flickr getFlickr(){
        flickr=new Flickr(apiKey,apiSecret,new REST());
        Auth auth= new Auth();

        auth.setPermission(Permission.DELETE);
        auth.setToken(appKey);
        auth.setTokenSecret(appSecret);

        RequestContext requestContext= RequestContext.getRequestContext();
        requestContext.setAuth(auth);

        flickr.setAuth(auth);
        return flickr;
    }

}

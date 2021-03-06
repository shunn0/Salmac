package com.salmac.cleint.engine;

import com.salmac.cleint.engine.service.ServerService;
import com.salmac.cleint.engine.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Configuration
@EnableSwagger2
@EnableScheduling
public class Config {
    @Autowired
    ServerService serverService;

    @Value("${salmac.host}")
    private String salmacHost;

    @Value("${salmac.host.self}")
    private String selfAddress;

    @Value("${server.port}")
    private String serverPort;

    @Scheduled(fixedRate = Constants.SERVER_STATUS_CHECK_INTERVAL)
    public void scheduleFixedRateTask() throws UnknownHostException {
        final String URI = salmacHost+"/server/heartbeat";
        //String ipAddr = Inet4Address.getLocalHost().getHostAddress()+":"+serverPort;
        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("targetEngineAddress", selfAddress);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        //ResponseEntity<String> response = restTemplate.pu( URI, request , String.class );
        // HttpEntity<String> request = new HttpEntity<>(ipAddr+":"+serverPort);

        ResponseEntity<String> response = restTemplate.postForEntity(URI, request, String.class);
                //.exchange(URI, HttpMethod.POST, request,String.class);
        System.out.println(LocalDateTime.now()+" : "+ response.getStatusCode());
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

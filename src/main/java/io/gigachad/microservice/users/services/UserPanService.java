package io.gigachad.microservice.users.services;

import io.gigachad.microservice.users.contracts.PanInfo;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class UserPanService {
    private static final Logger logger = LoggerFactory.getLogger("UserPanServiceLogger");
    private final String panURI = "http://localhost:8080";
    private final WebClient client = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .responseTimeout(Duration.ofMillis(5000))
                    .doOnConnected(conn ->
                            conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                    .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)))
            ))
            .build();

    public PanInfo getPanInfo(int id) {
        logger.info("[getPanInfo()] Fetching pan info for id: {}", id);

        return client.get().uri(panURI + "/user/" + id)
                .retrieve().bodyToMono(PanInfo.class).block();
    }

    public PanInfo addPanInfo(PanInfo info) {
        logger.info("[addPanInfo()] Inserting the given PanInfo: {}", info);

        return client.post().uri(panURI + "/add").bodyValue(info).retrieve().bodyToMono(PanInfo.class).block();
    }
}

package org.yugh.coral.client.rsocket.api.rest;

import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.yugh.coral.client.rsocket.model.MusicData;
import org.yugh.coral.client.rsocket.model.MusicDataRequest;
import org.yugh.coral.client.rsocket.service.MusicService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author yugenhai
 */
@Controller
public class MusicController {


    private final MusicService musicService;
    private final Mono<RSocketRequester> requester;

    public MusicController(MusicService musicService, Mono<RSocketRequester> requester) {
        this.musicService = musicService;
        this.requester = requester;
    }

    @GetMapping(value = "/music/{author}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MusicData> getByAuthor(@PathVariable String author) {
        return musicService.getByAuthor(author);
    }

    @GetMapping(value = "/all/{author}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MusicData> getAllMusicData(@PathVariable String author){
        return musicService.getAllMusicData(author);
    }


    @GetMapping(value = "/rsocket/{author}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MusicData> getByAuthorRsocket(@PathVariable String author) {
        return requester
                .flatMapMany(
                        r -> r.route("music.author")
                                .data(new MusicDataRequest(author))
                                .retrieveFlux(MusicData.class));
    }


}

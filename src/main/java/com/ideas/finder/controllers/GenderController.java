package com.ideas.finder.controllers;

import java.util.List;
import java.util.function.Function;

import com.ideas.finder.nodes.gender.Gender;
import com.ideas.finder.nodes.subGender.SubGender;
import com.ideas.finder.repositories.GenderRepository;
import com.ideas.finder.repositories.SubGenderRepository;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/genders")
public class GenderController {

    private final GenderRepository genderRepository;
    private final SubGenderRepository subGenderRepository;

	public GenderController(GenderRepository genderRepository, SubGenderRepository subGenderRepository) {
		this.genderRepository = genderRepository;
        this.subGenderRepository = subGenderRepository;
	}

    // public Mono<SubGender> createSubGender(Gender gender) {
    //     SubGender subGender = new SubGender("Reptile", "Reptile sub gender");
    //     subGender.addGender(gender);
    //     gender.addSubGender(subGender);
    //     return this.subGenderRepository.save(subGender);
    // }

    // @GetMapping(value = { "", "/" }, produces = MediaType.APPLICATION_JSON_VALUE)
    // Flux<Gender> getGenders() {
    //     Gender gender1 = new Gender("Vertebrate", "Vertebrate family");
    //     SubGender subGender = new SubGender("Reptile", "Reptile sub gender");
    //     gender1.addSubGender(subGender);
        

    //     this.genderRepository.findAllByDisplayName("Vertebrate").log().flatMap( genderIsValid -> {
    //         System.out.println(genderIsValid);
    //         return Flux.fromIterable(List.of("ok","ok"));
    //     });

    //     // this.genderRepository
    //     // .findAllByDisplayName("Vertebrate")
    //     // .switchIfEmpty(this.genderRepository.findAll())
        
               

    //     // return this.genderRepository.save(gender1)
    //     //     // .then(createSubGender(gender1))
    //     //     .thenMany(this.genderRepository.findAll());
    // }

    //https://nikeshshetty.medium.com/5-common-mistakes-of-webflux-novices-f8eda0cd6291
    
    @GetMapping(value = { "", "/" }, produces = MediaType.APPLICATION_JSON_VALUE)
    Flux<?> getGenders() {
        //this.genderRepository.findAll().defaultIfEmpty()
        return this.genderRepository
            .findAll()
            .filter( gender -> gender.getDisplayName() != "Vertebrate" && gender.getDisplayName() != "Invertebrate")
            .switchIfEmpty(this.genderRepository.saveAll(List.of(new Gender("Vertebrate", "Vertebrate family"), new Gender("Invertebrate", "Invertebrate family"))));
            //.flatMap( gender -> this.genderRepository.findById(gender.getId()) );
    }

    // for empty mono 
    // customerFlux().collectListOrEmpty()
    //                  .switchIfEmpty(notFound().build())
    //                  .flatMap(c -> ok().body(BodyInserters.fromObject(c)))

    @GetMapping(value = {"/test" }, produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<String> test() {
        return Mono.just("salut");
    }
}

package com.ideas.finder.controllers;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import com.ideas.finder.constant.Constants;
import com.ideas.finder.nodes.gender.Gender;
import com.ideas.finder.nodes.subGender.SubGender;
import com.ideas.finder.repositories.GenderRepository;
import com.ideas.finder.repositories.SubGenderRepository;

import org.reactivestreams.Publisher;
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

    
    /**
     * http://localhost:8080/genders/init/genders
     * http://localhost:8080/genders/init/subgenders/vertebrate
     * 
MATCH (n) RETURN n LIMIT 25
     * 
     * MATCH (n) DETACH DELETE n
     * @return
     */


    @GetMapping(value = { "", "/" }, produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<String> n(){
        return Mono.just("Hello");
    }


    //https://nikeshshetty.medium.com/5-common-mistakes-of-webflux-novices-f8eda0cd6291
    @GetMapping(value = "/init/genders", produces = MediaType.APPLICATION_JSON_VALUE)
    Flux<Gender> initGenders() {
            return this.genderRepository
                .findAll()
                .filter( gender -> gender.getDisplayName() != Constants.VERTEBRATE && gender.getDisplayName() != Constants.INVERTEBRATE )
                .switchIfEmpty(this.genderRepository.saveAll(List.of(new Gender("Vertebrate", "Vertebrate family"), new Gender("Invertebrate", "Invertebrate family"))));
    }


    @GetMapping(value = "/init/subgenders/vertebrate", produces = MediaType.APPLICATION_JSON_VALUE)
    Flux<?> initSubGenders() {
        Set<SubGender> subGendersVertebrate = Set.of(
            new SubGender("Reptile", "Reptile are cool"),
            new SubGender("Bird", "Birds are not cool"));
            
            return this.subGenderRepository
                .findAll()
                .switchIfEmpty(this.subGenderRepository.saveAll(subGendersVertebrate))
                .thenMany(this.genderRepository.findAll())
                .filter( gender1 -> gender1.getDisplayName().equals(Constants.VERTEBRATE))
                .log()
                .flatMap( gender2 -> {
                    // System.out.println(gender2.getDisplayName());
                    gender2.setSubGenders(subGendersVertebrate);
                    return this.genderRepository.save(gender2);
                })
                .flatMap(ge -> this.genderRepository.findAll());
                // .flatMap( c -> {
                //     return Mono.just("yikes");
                // })
                // .flatMap( gender3 -> gender3.setSubGenderFlux(subGendersVertebrate)); // send back Itterable flux of Set subGenders if we chain with flatMap
            

                
                //.flatMap( gender -> this.genderRepository.findById(gender.getId()))
                //.switchIfEmpty(this.subGenderRepository.saveAll(List.of(new Gender("Vertebrate", "Vertebrate family"), new Gender("Invertebrate", "Invertebrate family"))));
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


}

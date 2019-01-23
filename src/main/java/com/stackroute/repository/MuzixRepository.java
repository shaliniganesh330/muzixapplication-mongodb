package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MuzixRepository extends MongoRepository<Track,Integer> {
    public List<Track> findByTrackName(String trackName);
}



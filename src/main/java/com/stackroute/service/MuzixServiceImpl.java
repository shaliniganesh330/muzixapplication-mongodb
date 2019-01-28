package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//implementation of  MuzixService//
public class MuzixServiceImpl implements MuzixService {
    private MuzixRepository muzixRepository;

    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    //Overrided method for saveTrack//
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (muzixRepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        Track savedTrack = muzixRepository.save(track);
        if (savedTrack == null) {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        return savedTrack;
    }

    //Overrided method for getAllTracks//
    @Override
    public List<Track> getAllTracks() {
        return muzixRepository.findAll();
    }

    //Overrided method for deleteById//
    @Override
    public List<Track> removeById(int trackId) throws TrackNotFoundException {
        if (!muzixRepository.existsById(trackId)) {
            throw new TrackNotFoundException("Track not found");
        }
        muzixRepository.deleteById(trackId);
        return muzixRepository.findAll();
    }

    //Overrided method for updateTrack //
    @Override
    public Track updateTrack(int trackId, String comment) throws TrackNotFoundException {

        if (!muzixRepository.existsById(trackId)) {
            throw new TrackNotFoundException("Track not found to update");
        }
        Optional<Track> muzix = muzixRepository.findById(trackId);
        Track muzix1 = muzix.get();
        muzix1.setTrackComments(comment);
        Track savedMuzix = muzixRepository.save(muzix1);
        return savedMuzix;
    }

    //Overrided method for findByTrackId//
    @Override
    public Track trackByTrackId(int trackId) throws TrackNotFoundException {
        if (!muzixRepository.existsById(trackId)) {
            throw new TrackNotFoundException("Track not found to update");
        }
        Optional<Track> muzix1 = muzixRepository.findById(trackId);
        Track track = muzix1.get();
        return track;
    }

    //Overrided method for findByTrackName//
    public List<Track> trackByTrackName(String trackName) throws TrackNotFoundException {
        if (muzixRepository.findByTrackName(trackName).isEmpty()) {
            throw new TrackNotFoundException("Track with given name is not found");
        }
        return muzixRepository.findByTrackName(trackName);
    }
}

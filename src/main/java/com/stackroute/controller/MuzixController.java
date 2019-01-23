package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.MuzixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class MuzixController {
    private MuzixService muzixService;

    public MuzixController(MuzixService muzixService) {
        this.muzixService = muzixService;
    }

    //Saving track information such as trackId,trackcomments,trackname//
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            muzixService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Displaying saved track.//
    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks() {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Track>>(muzixService.getAllTracks(), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Removing track.//
    @DeleteMapping("track/{trackId}")
    public ResponseEntity<?> deleteById(@PathVariable String trackId) {
        ResponseEntity responseEntity;
        try {
            muzixService.deleteById(trackId);
            responseEntity = new ResponseEntity<List<Track>>(muzixService.getAllTracks(), HttpStatus.FOUND);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    //Update track.//
    @PutMapping("track/{trackId}")
    public ResponseEntity<?> updateTrack(@PathVariable int trackId,@RequestBody Track track) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        try {
            muzixService.updateTrack(trackId, track.getTrackComments());
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Method to track by id//
    @GetMapping("track/{trackId}")
    public ResponseEntity<?> getById(@PathVariable int trackId) throws TrackNotFoundException {
        return new ResponseEntity (muzixService.trackByTrackId(trackId), HttpStatus.OK);
    }

    //Method to track by name//
    @GetMapping("tracks/{trackName}")
    public ResponseEntity<?> getByName(@PathVariable String trackName) throws TrackNotFoundException {
        return new ResponseEntity<List<Track>>(muzixService.trackByTrackName(trackName), HttpStatus.OK);
    }
}


package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

//JUnit will invoke the class it references to run the tests in that class instead of the runner built into JUnit.
@RunWith(SpringRunner.class)

// Used when a test focuses only on MongoDB components.
@DataMongoTest
public class MuzixRepositoryTestIT {

    @Autowired
    private MuzixRepository muzixRepository;
    private Track track;
    private List<Track> list;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setTrackId(10);
        track.setTrackName("jayanthi");
        track.setTrackComments("moms love");
        list=new ArrayList<>();
        list.add(track);

    }
    @After
    public void tearDown(){

        muzixRepository.deleteAll();
    }
    @Test
    public void testSaveTrack(){
        muzixRepository.save(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(10,fetchTrack.getTrackId());
    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(10,"jayanthi","moms love");
        muzixRepository.save(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void testGetAllTrack(){
        Track u = new Track(10,"jayanthi","moms love");
        Track u1 = new Track(20,"daddy","dads love");
        muzixRepository.save(u);
        muzixRepository.save(u1);

        List<Track> list =muzixRepository.findAll();
        Assert.assertEquals("jayanthi",list.get(0).getTrackName());
    }

    @Test
    public void testGetAllTrackFailure(){
        Track u = new Track(10,"jayanthi","moms love");
        Track u1 = new Track(20,"daddy","dads love");
        muzixRepository.save(u);
        muzixRepository.save(u1);

        List<Track> list =muzixRepository.findAll();
        Assert.assertNotEquals("john",list.get(0).getTrackName());
    }

    @Test
    public void testDeleteTrackSuccess(){
        Track  u = new Track(10,"jayanthi","moms love");
        muzixRepository.save(u);
        muzixRepository.delete(u);
        Assert.assertEquals(Optional.empty(),muzixRepository.findById(10));
    }
    @Test
    public void testDeleteTrackFailure(){
        Track  u = new Track(10,"jayanth","moms love");
        muzixRepository.save(u);
        muzixRepository.delete(u);
        Assert.assertNotEquals(u,muzixRepository.findById(10));
    }

    @Test
    public void testUpdateTrackSuccess(){
        track.setTrackComments("novel");
        muzixRepository.save(track);
        Assert.assertEquals("novel",muzixRepository.findById(10).get().getTrackComments());
    }

    @Test
    public void testUpdateTrackFailure(){
        track.setTrackComments("novel");
        muzixRepository.save(track);
        Assert.assertNotEquals("novelist",muzixRepository.findById(10).get().getTrackComments());
    }
    @Test
    public void testFindByTrackIdSuccess(){
        muzixRepository.save(track);
        Track output=muzixRepository.findById(10).get();
        System.out.println(output);
        Assert.assertEquals(track,output);
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindByTrackIdFailure(){
        Track output=muzixRepository.findById(101).get();
        System.out.println(output);
        Assert.assertEquals(null,output);
    }

    @Test
    public void testFindByTrackNameSuccess(){
        List<Track> expectedOutput = new ArrayList<>();
        muzixRepository.save(track);
        expectedOutput.add(track);
        List<Track> output=muzixRepository.findByTrackName("jayanthi");
        System.out.println(output);
        System.out.println(expectedOutput);
        Assert.assertEquals(expectedOutput,output);
}
    @Test
    public void testFindByTrackNameFailure(){
        List<Track> expectedOutput = new ArrayList<>();
        expectedOutput.add(track);
        List<Track> output=muzixRepository.findByTrackName("John");
        Assert.assertNotEquals(expectedOutput,output);
    }
}
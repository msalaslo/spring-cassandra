package com.msl.data.cassandra.service.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import com.msl.data.cassandra.domain.Hotel;
import com.msl.data.cassandra.service.HotelService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceImplTest {
	
    @Autowired
    HotelService hotelService;

    @Before
    public void setUp() {
        
    }
    
    @Test
    public void save() {
    	int threads = 1000;
    	int start = 150000;
    	int numHotels = 10000;
    	for (int i = 0; i < threads; i++) {
			loadHotels(i*start, numHotels);
			System.out.println(i*start + "thread.");
		}

    }
    
    @Async
    private void loadHotels(int start, int numHotels) {
    	Hotel hotel = null;
    	int maxIndexHotels = start + numHotels;
    	for (int i = start; i < maxIndexHotels; i++) {
    		hotel = new Hotel();
        	hotel.setAddress("address"+i);
        	hotel.setId(UUID.randomUUID());
        	hotel.setName("name"+i);
        	hotel.setState("state"+i);
        	hotel.setZip("zip"+i);
        	this.hotelService.save(hotel);
		}
    	System.out.println(numHotels + "salvados.");
    }

}

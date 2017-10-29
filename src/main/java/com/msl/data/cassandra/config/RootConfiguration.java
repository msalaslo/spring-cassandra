package com.msl.data.cassandra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.msl.data.cassandra.repository.HotelByLetterRepository;
import com.msl.data.cassandra.repository.HotelRepository;
import com.msl.data.cassandra.service.HotelService;
import com.msl.data.cassandra.service.HotelServiceImpl;

@Configuration
public class RootConfiguration {

    @Bean
    public HotelService hotelService(HotelRepository hotelRepository,
                                     HotelByLetterRepository hotelByLetterRepository) {
        return new HotelServiceImpl(hotelRepository, hotelByLetterRepository);
    }

}

package com.msl.data.cassandra.repository;

import java.util.List;

import com.msl.data.cassandra.domain.HotelByLetter;
import com.msl.data.cassandra.domain.HotelByLetterKey;

public interface HotelByLetterRepository {
    List<HotelByLetter> findByFirstLetter(String letter);
    HotelByLetter save(HotelByLetter hotelByLetter);
    void delete(HotelByLetterKey hotelByLetterKey);
}

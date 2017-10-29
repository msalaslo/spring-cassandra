package com.msl.data.cassandra.service;

import java.util.List;
import java.util.UUID;

import com.msl.data.cassandra.domain.Hotel;
import com.msl.data.cassandra.domain.HotelByLetter;

public interface HotelService {
	Hotel save(Hotel hotel);

	Hotel update(Hotel hotel);

	Hotel findOne(UUID uuid);

	void delete(UUID uuid);

	List<HotelByLetter> findHotelsStartingWith(String letter);

	List<Hotel> findHotelsInState(String state);

	List<Hotel> findHotels();
}

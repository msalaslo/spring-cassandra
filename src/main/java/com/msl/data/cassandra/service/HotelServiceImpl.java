package com.msl.data.cassandra.service;

import java.util.List;
import java.util.UUID;

import com.msl.data.cassandra.domain.Hotel;
import com.msl.data.cassandra.domain.HotelByLetter;
import com.msl.data.cassandra.repository.HotelByLetterRepository;
import com.msl.data.cassandra.repository.HotelRepository;

public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    private final HotelByLetterRepository hotelByLetterRepository;


    public HotelServiceImpl(HotelRepository hotelRepository,
                            HotelByLetterRepository hotelByLetterRepository) {
        this.hotelRepository = hotelRepository;
        this.hotelByLetterRepository = hotelByLetterRepository;
    }

    @Override
    public Hotel save(Hotel hotel) {
        if (hotel.getId() == null) {
            hotel.setId(UUID.randomUUID());
        }
        this.hotelRepository.save(hotel);
        this.hotelByLetterRepository.save(new HotelByLetter(hotel));
        return hotel;
    }

    @Override
    public Hotel update(Hotel hotel) {
        Hotel existingHotel = this.hotelRepository.findOne(hotel.getId());
        if (existingHotel != null) {
            this.hotelByLetterRepository.delete(new HotelByLetter(existingHotel).getHotelByLetterKey());
            this.hotelRepository.update(hotel);
            this.hotelByLetterRepository.save(new HotelByLetter(hotel));
        }
        return hotel;
    }

    @Override
    public Hotel findOne(UUID uuid) {
        return this.hotelRepository.findOne(uuid);
    }

    @Override
    public void delete(UUID uuid) {
        Hotel hotel = this.hotelRepository.findOne(uuid);
        if (hotel != null) {
            this.hotelRepository.delete(uuid);
            this.hotelByLetterRepository.delete(new HotelByLetter(hotel).getHotelByLetterKey());
        }
    }

    @Override
    public List<HotelByLetter> findHotelsStartingWith(String letter) {
        return this.hotelByLetterRepository.findByFirstLetter(letter);
    }

    @Override
    public List<Hotel> findHotelsInState(String state) {
        return this.hotelRepository.findByState(state);
    }
    
    @Override
    public List<Hotel> findHotels() {
        return this.hotelRepository.findAll();
    }
}

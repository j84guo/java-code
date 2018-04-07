package com.jackson.landon.business.service;

import com.jackson.landon.business.domain.RoomReservation;

import com.jackson.landon.data.entity.Guest;
import com.jackson.landon.data.entity.Reservation;
import com.jackson.landon.data.entity.Room;

import com.jackson.landon.data.repository.GuestRepository;
import com.jackson.landon.data.repository.ReservationRepository;
import com.jackson.landon.data.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {

        // get room iterator
        Iterable<Room> rooms = this.roomRepository.findAll();

        // populate room reservation (business object) for each room
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());

            roomReservationMap.put(room.getId(), roomReservation);
        });

        // find reservations by date
        Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));

        // find guest for each reservation on the date
        if(reservations != null){
            reservations.forEach(reservation -> {
                Guest guest = this.guestRepository.findOne(reservation.getGuestId());

                if(null != guest){
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
                    roomReservation.setDate(date);
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setGuestId(guest.getId());
                }
            });
        }

        // return list of room reservations
        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long roomId : roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(roomId));
        }

        return roomReservations;
    }


}

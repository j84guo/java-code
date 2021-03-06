package com.jackson.landon.web.application;

import com.jackson.landon.business.domain.RoomReservation;
import com.jackson.landon.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/reservations")
public class ReservationController {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method= RequestMethod.GET)
    public String getReservations(@RequestParam(value="date", required=false) String dateString, Model model){

        // date to search for
        Date date = null;
        if(dateString != null){
            try{
                date = DATE_FORMAT.parse(dateString);
            }catch(Exception e){
                date = new Date();
            }
        }else{
            date = new Date();
        }

        // get room reservations
        List<RoomReservation> roomReservationList = this.reservationService.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations", roomReservationList);

        // view will be populated based on Model by Thymeleaf
        return "reservations";
    }
}

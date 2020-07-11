package com.ahoubouby.brs.controller;
/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */

import com.ahoubouby.brs.dto.model.UserDto;
import com.ahoubouby.brs.dto.response.Response;
import com.ahoubouby.brs.model.request.UserSignupRequest;
import com.ahoubouby.brs.service.BusReservationService;
import com.ahoubouby.brs.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@Api(value = "brs-application")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BusReservationService busReservationService;

    /**
     * Handles the incoming POST API "/v1/user/signup"
     *
     * @param userSignupRequest
     * @return
     */
    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        return Response.ok().setPayload(registerUser(userSignupRequest, false));
    }


    @GetMapping("/getAllTrip")
    public Response getALlTrip(String tripId) {
        return Response.ok().setPayload(busReservationService.getTripById(tripId));
    }

    /**
     * Register a new user in the database
     *
     * @param userSignupRequest
     * @return
     */
    private UserDto registerUser(UserSignupRequest userSignupRequest, boolean isAdmin) {
        UserDto userDto = new UserDto()
                .setEmail(userSignupRequest.getEmail())
                .setPassword(userSignupRequest.getPassword())
                .setFirstName(userSignupRequest.getFirstName())
                .setLastName(userSignupRequest.getLastName())
                .setMobileNumber(userSignupRequest.getMobileNumber())
                .setAdmin(isAdmin);

        return userService.signup(userDto);
    }

}

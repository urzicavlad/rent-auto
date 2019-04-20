package ro.ubbcluj.rentauto.controller;

import ro.ubbcluj.rentauto.service.RentService;

public class RentController {

    private RentService rentService;

    public void setServices(RentService rentService) {
        this.rentService = rentService;
    }
}

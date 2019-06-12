package com.example.rafproject2.repository.web.api;


import com.example.rafproject2.repository.web.model.ScheduleApiModel;
import com.example.rafproject2.repository.web.service.ScheduleService;
import com.example.rafproject2.repository.web.service.ServiceGenerator;

import java.util.List;

import retrofit2.Call;

public class ScheduleApi {

    private ScheduleService scheduleService;

    public ScheduleApi(){
        scheduleService= ServiceGenerator.createService(ScheduleService.class);
    }

    public Call<List<ScheduleApiModel>> getSchedule(){
        return scheduleService.getSchedule();
    }

}

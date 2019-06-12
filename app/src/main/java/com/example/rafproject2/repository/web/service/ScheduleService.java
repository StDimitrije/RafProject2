package com.example.rafproject2.repository.web.service;


import com.example.rafproject2.repository.web.model.Schedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ScheduleService {

    @GET("raspored")
    public Call<List<Schedule>> getSchedule();



}

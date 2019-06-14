package com.example.rafproject2.repository;

import android.app.Application;
import android.util.Log;

import com.example.rafproject2.model.ScheduleFilter;
import com.example.rafproject2.repository.db.ScheduleDatabase;
import com.example.rafproject2.repository.db.dao.ScheduleDao;
import com.example.rafproject2.repository.db.entity.ScheduleEntity;
import com.example.rafproject2.repository.web.api.ScheduleApi;
import com.example.rafproject2.repository.web.model.Resource;
import com.example.rafproject2.repository.web.model.ScheduleApiModel;
import com.example.rafproject2.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleRepository {

    private static final String TAG = "ScheduleRepository";

    private ScheduleApi mScheduleApi;
    private ScheduleDao mScheduleDao;
    private MutableLiveData<Resource<Void>> mResourceLiveData;
    private ExecutorService mExecutorService;

    public ScheduleRepository(Application application){
        mScheduleApi = new ScheduleApi();
        mScheduleDao = ScheduleDatabase.getDb(application).getScheduleDao();
        mResourceLiveData = new MutableLiveData<>();
        mExecutorService = Executors.newCachedThreadPool();
    }

    public LiveData<Resource<Void>> getSchedule(){
        mScheduleApi.getSchedule().enqueue(new Callback<List<ScheduleApiModel>>() {
            @Override
            public void onResponse(Call<List<ScheduleApiModel>> call, Response<List<ScheduleApiModel>> response) {

                notifyResult(true);
                insertScheduleToDb(response.body());
            }

            @Override
            public void onFailure(Call<List<ScheduleApiModel>> call, Throwable t) {

                notifyResult(false);
                Log.e(TAG, "onFailure: " + t.toString());

            }
        });
        return mResourceLiveData;
    }

    public LiveData<List<ScheduleEntity>> getFilteredSchedule(ScheduleFilter filter) {
        return mScheduleDao.getFilteredSchedule(filter.getSubject(),filter.getTeacher(), filter.getDay(), filter.getGroup());
    }

    public LiveData<List<ScheduleEntity>> getCompleteSchedule(){
        return mScheduleDao.getCompleteSchedule();
    }



    private void notifyResult(boolean isSuccessful){
        Resource<Void> resource = new Resource<>(null, isSuccessful);
        mResourceLiveData.setValue(resource);
    }

    private void insertScheduleToDb(List<ScheduleApiModel> scheduleApiModelList){

        List<ScheduleEntity> scheduleEntityList = transformApiModelToEntity(scheduleApiModelList);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                mScheduleDao.insertScheduleToDb(scheduleEntityList);
            }
        });
    }

    private List<ScheduleEntity> transformApiModelToEntity(List<ScheduleApiModel> scheduleApiModelList) {

        List<ScheduleEntity> scheduleEntityList = new ArrayList<>();

        for (ScheduleApiModel scheduleApiModel: scheduleApiModelList){
            String subject = scheduleApiModel.getSubject();
            String type = scheduleApiModel.getType();
            String teacher = scheduleApiModel.getTeacher();
            String classroom = scheduleApiModel.getClassRoom();
            String day = scheduleApiModel.getDay();
            String groups = scheduleApiModel.getGroups();
            String time = scheduleApiModel.getTime();

            ScheduleEntity scheduleEntity = new ScheduleEntity(Util.generateId(),subject,type,teacher,groups,day,time,classroom);
            scheduleEntityList.add(scheduleEntity);
        }
        return scheduleEntityList;
    }

}

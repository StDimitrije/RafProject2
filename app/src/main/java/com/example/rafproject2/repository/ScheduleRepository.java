package com.example.rafproject2.repository;

import android.app.Application;

import com.example.rafproject2.model.ScheduleFilter;
import com.example.rafproject2.repository.db.ScheduleDatabase;
import com.example.rafproject2.repository.db.dao.ScheduleDao;
import com.example.rafproject2.repository.db.entity.ScheduleEntity;
import com.example.rafproject2.repository.web.api.ScheduleApi;
import com.example.rafproject2.repository.web.model.Resource;
import com.example.rafproject2.repository.web.model.ScheduleApiModel;

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

    public MutableLiveData<Resource<Void>> getSchedule(){
        mScheduleApi.getSchedule().enqueue(new Callback<List<ScheduleApiModel>>() {
            @Override
            public void onResponse(Call<List<ScheduleApiModel>> call, Response<List<ScheduleApiModel>> response) {

                notifyResult(true);
            }

            @Override
            public void onFailure(Call<List<ScheduleApiModel>> call, Throwable t) {

            }
        });
        return mResourceLiveData;
    }

    public LiveData<List<ScheduleEntity>> getFilteredSchedule(ScheduleFilter filter) {
        return mScheduleDao.getFilteredSchedule(filter.getmSubject(), filter.getmTeacher(), filter.getmDay(), filter.getmGroup());
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
            String id = scheduleApiModel.getmId();
            String subject = scheduleApiModel.getmSubject();
            String type = scheduleApiModel.getmType();
            String teacher = scheduleApiModel.getmTeacher();
            String classroom = scheduleApiModel.getmClassRoom();
            String day = scheduleApiModel.getmDay();
            String groups = scheduleApiModel.getmGroups();
            String time = scheduleApiModel.getmTime();

            ScheduleEntity scheduleEntity = new ScheduleEntity(id,subject,type,teacher,groups,day,time,classroom);
            scheduleEntityList.add(scheduleEntity);
        }
        return scheduleEntityList;
    }

}

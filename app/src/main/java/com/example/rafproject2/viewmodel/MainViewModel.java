package com.example.rafproject2.viewmodel;

import android.app.Application;

import com.example.rafproject2.model.ScheduleFilter;
import com.example.rafproject2.repository.ScheduleRepository;
import com.example.rafproject2.repository.db.ScheduleDatabase;
import com.example.rafproject2.repository.db.entity.ScheduleEntity;
import com.example.rafproject2.repository.web.model.Resource;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class MainViewModel extends AndroidViewModel {

    private ScheduleRepository mScheduleRepository;
    private MutableLiveData<ScheduleFilter> mFilteredLiveData;
    private LiveData<List<ScheduleEntity>> mFilteredScheduleLiveData;


    public MainViewModel(@NonNull Application application) {
        super(application);

        mScheduleRepository = new ScheduleRepository(application);
        mFilteredLiveData = new MutableLiveData<>();
        mFilteredScheduleLiveData = Transformations.switchMap(mFilteredLiveData,
                new Function<ScheduleFilter, LiveData<List<ScheduleEntity>>>() {
            @Override
            public LiveData<List<ScheduleEntity>> apply(ScheduleFilter input) {
                return mScheduleRepository.getFilteredSchedule(input);
            }
        });

    }

    public LiveData<Resource<Void>> getSchedule(){
        return mScheduleRepository.getSchedule();
    }

    public LiveData<List<ScheduleEntity>> getFilteredSchedule(){
        return mFilteredScheduleLiveData;
    }

    public LiveData<List<ScheduleEntity>> getCompleteSchedule(){
        return mScheduleRepository.getCompleteSchedule();
    }

    public void setFilter(ScheduleFilter filter){
        mFilteredLiveData.setValue(filter);
    }

}

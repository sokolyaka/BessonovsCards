package com.sokolov.bessonovscards.data.reposiroty.inmemory;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.data.reposiroty.IScheduleRepository;
import com.sokolov.bessonovscards.entity.ISchedule;
import com.sokolov.bessonovscards.entity.Schedule;

import java.util.ArrayList;
import java.util.List;

public class InMemoryScheduleRepository implements IScheduleRepository{

    private final static List<ISchedule> SCHEDULES = new ArrayList<>();
    static {
        SCHEDULES.add(new Schedule("EMPTY", "Empty", 0));
        SCHEDULES.add(new Schedule("TODAY", "Today", 0));
        SCHEDULES.add(new Schedule("TOMORROW", "Tomorrow", 1));
        SCHEDULES.add(new Schedule("ONCE_PER_WEEK", "Next week", 7));
        SCHEDULES.add(new Schedule("ONCE_PER_MONTH", "Next month", 30));
    }
    @Override
    public ISchedule getByUuid(String uuid) {
        for (ISchedule schedule : SCHEDULES) {
            if (schedule.uuid().equals(uuid)) {
                return schedule;
            }
        }
        throw new NotFoundException();
    }
}

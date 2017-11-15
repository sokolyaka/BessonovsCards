package com.sokolov.bessonovscards.data.reposiroty;

import com.sokolov.bessonovscards.entity.ISchedule;

public interface IScheduleRepository {
    ISchedule getByUuid(String uuid);
}

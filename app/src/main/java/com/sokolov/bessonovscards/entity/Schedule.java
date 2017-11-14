package com.sokolov.bessonovscards.entity;

public class Schedule implements ISchedule {
    private final String uuid;
    private final String name;
    private final int durationInDays;

    public Schedule(String uuid, String name, int durationInDays) {
        this.uuid = uuid;
        this.name = name;
        this.durationInDays = durationInDays;
    }

    @Override
    public String uuid() {
        return uuid;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int durationInDays() {
        return durationInDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (durationInDays != schedule.durationInDays) return false;
        if (uuid != null ? !uuid.equals(schedule.uuid) : schedule.uuid != null) return false;
        return name != null ? name.equals(schedule.name) : schedule.name == null;

    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + durationInDays;
        return result;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", durationInDays=" + durationInDays +
                '}';
    }
}

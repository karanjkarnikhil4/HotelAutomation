package com.hotel.automation.watch;

import java.time.LocalDateTime;
import java.util.TimerTask;

public interface IWatch {
  boolean isNightTime();

  LocalDateTime getStartTime();

  void setStartTime(LocalDateTime startTime);

  LocalDateTime getEndTime();

  void setEndTime(LocalDateTime endTime);

  void schedule(LocalDateTime nextRun, Runnable runnable);

  void schedule(TimerTask timerTask, long delay);

  void cancel();
}

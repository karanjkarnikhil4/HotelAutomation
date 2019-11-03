package com.hotel.automation.watch;

import com.hotel.automation.constants.NightTime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Watch implements IWatch {

  private LocalDateTime startTime = LocalDate.now().atTime(NightTime.START_INT, 0);
  private LocalDateTime endTime =
      LocalDate.now().plus(1, ChronoUnit.DAYS).atTime(NightTime.END_INT, 0);
  private Timer timer;

  public Watch() {
    timer = new Timer();
  }

  @Override
  public boolean isNightTime() {
    LocalDateTime localDateTime = LocalDateTime.now();

    return localDateTime.isAfter(startTime) && localDateTime.isBefore(endTime);
  }

  @Override
  public LocalDateTime getStartTime() {
    return startTime;
  }

  @Override
  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  @Override
  public LocalDateTime getEndTime() {
    return endTime;
  }

  @Override
  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  @Override
  public void schedule(LocalDateTime nextRun, Runnable runnable) {
    LocalDateTime now = LocalDateTime.now();
    if (now.compareTo(nextRun) > 0) nextRun = nextRun.plusDays(1);

    Duration duration = Duration.between(now, nextRun);
    long initalDelay = duration.getSeconds();

    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(15);
    scheduler.scheduleAtFixedRate(
        runnable, initalDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
  }

  @Override
  public void schedule(TimerTask timerTask, long delay) {
    timer.schedule(timerTask, delay);
  }

  @Override
  public void cancel() {
    this.timer.cancel();
  }
}

ViperScanSchedule flow:

1. Trigger – Spring runs executeJob() every 2 minutes (@Scheduled(fixedRate = 120000)), measured from the start of the last run.

2. Check flag – Skip if viper.schedule.config.flag is false.

3. Load jobs – Read batch_record table via scanDatabaseInfo() → update jobInfoMap in memory.

4. Filter jobs – For each job, executeJobCheck(...) decides if it should run based on:

  Frequency (DAILY, WEEKLY, MONTHLY)
  
  Parent job status/timing
  
  Prepared vs completed time

5. Create tasks – Use job type to build the right Callable (e.g., AbstractLoadingDataCallable, DailyLoadingDataCallable).

6. Run tasks – Add to tasks list → submit with executorService.invokeAll(tasks).

7. Update DB – After execution, set status (RUNNING → SUCCESS/FAILED), update execCompleteTime, and persist via batchRecordDaoService.update(...).

even though the scheduler is firing every 2 minutes, most jobs won’t run each time — the inner checks make sure they only run when scheduled.

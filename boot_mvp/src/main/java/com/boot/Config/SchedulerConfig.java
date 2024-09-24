package com.boot.Config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private final JobLauncher jobLauncher;
    private final Job movieJob;

    @Autowired
    public SchedulerConfig(JobLauncher jobLauncher, Job movieJob) {
        this.jobLauncher = jobLauncher;
        this.movieJob = movieJob;
    }

    @Scheduled(cron = "0 05 09 * * ?")  // 매일 15시 52분에 실행
    public void runMovieJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
            jobLauncher.run(movieJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


package edu.mum.batch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProductBatch {

	@Autowired
	Job importProducts;
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	SendEmail mailService;

	@Scheduled(cron="0 0 0 * * * * *")
	public void startjob() throws  Exception {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println("Starting batch " + dateFormat.format(new Date()));  
		
 	    JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
	    jobParametersBuilder.addDate("date", new Date());
	    JobParameters jobParameters = jobParametersBuilder.toJobParameters();
	    JobExecution jobExecution = jobLauncher.run(importProducts, jobParameters);

	    System.out.println("Exit status: " + jobExecution.getExitStatus().getExitCode());
	    if(!jobExecution.getExitStatus().getExitCode().equals(ExitStatus.COMPLETED)) {
	    	System.out.println( jobExecution.getAllFailureExceptions().toString());	
	    }

	    System.out.println("End of processing");
	}
	
}

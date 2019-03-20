package edu.mum.batch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProductBatch {

	// Configured Job
	@Autowired
	Job saveProducts;
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	MailService mailService;

	@Scheduled(cron="0 0 0 * * * * *")
	public void startjob() throws  Exception {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println("Starting batch " + dateFormat.format(new Date()));  
		
 	    JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
	    jobParametersBuilder.addDate("date", new Date());
	    JobParameters jobParameters = jobParametersBuilder.toJobParameters();
	    JobExecution jobExecution = jobLauncher.run(saveProducts, jobParameters);
	    
	    //jobExecution.getAllFailureExceptions().stream();
	    
	    BatchStatus batchStatus = jobExecution.getStatus();
	    
	    while (batchStatus.isRunning()) {
	        System.out.println("Still running...");
	        Thread.sleep(1000);
	    }
	    System.out.println("Exit status: " + jobExecution.getExitStatus().getExitCode());

	    // send result to admin...
	    //mailService.sendEmail();
	    System.out.println("End of processing");

	}
	
}

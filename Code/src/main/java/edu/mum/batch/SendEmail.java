package edu.mum.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import edu.mum.service.impl.EmailService;

public class SendEmail implements Tasklet {
	@Autowired
	EmailService emailService;
	
	@Value("${email.receiver.name}")
	private String name;
	@Value("${email.receiver.username}")
	private String username;
	
	@Override
 	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
 		// TODO Auto-generated method stub
		emailService.sendResultNotificationMail(name,username , chunkContext);
 		return RepeatStatus.FINISHED;
 	}
}

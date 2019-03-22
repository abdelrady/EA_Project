package edu.mum.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import edu.mum.service.impl.EmailService;

public class SendEmail implements Tasklet {
	@Autowired
	EmailService emailService;
	@Override
 	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
 		// TODO Auto-generated method stub
		emailService.sendResultNotificationMail("Thanh Tran", "hoaithanhht3@gmail.com", chunkContext);
 		return RepeatStatus.FINISHED;
 	}
}

Integration flow
	Console Application
		1- End-user will browse products catalog		
		2- User add items to cart		
		3- User checkout items		
		4- New order is generated and persisted		
		5- AMQP producer send order summary to direct exchange 
	
	SpringArrivalConsumer Application		
		6- Listener receive order summary, print order summary and send confirmation email
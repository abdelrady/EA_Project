Integration flow
	Console Application
		1- End-user will browse products catalog
		   Login and authentication	
		2- User add items to cart
		3- User Removes Item from Cart
		4- User Show cart 		
		5- User checkout items		
		6- New order is generated and persisted		
		7- AMQP producer send order summary to direct exchange 
                8- Admin add users
		9- Admin adds products
		10- Admin add categories
	        11- Admin list orders
		12- Admin update order status
		13- Batch process to integrate products data from csv files and send notifications
	
	SpringArrivalConsumer Application		
		14- Listener receive order summary, print order summary and send confirmation email

	Rest Service
		15-Lists order and update order operations for admin
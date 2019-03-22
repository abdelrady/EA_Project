DB credential:root/root
DB schema:springarrival

Rabbit credential guest/guest


Start with SpringArrivalMain Application - this app will create-drop DB, so it always should be the first application to start.
Whenever this application exits, we need to stop RestService because running RestService is keeping the connection to DB's schema then 
SpringArrivalMain couldn't drop and create tables for that schema.

	1. Run as Java application
Start SpringArrival_RestService App.
	1. Run As - Run on Server

Begin to test: 
	2. Login with user: admin account or customer account
		2.1 admin account: admin/admin
		2.2 customer account:Asaid/password		
	3. Depend on user that logins, corresponding allowed commands are shown and user can proceed with them.

Integration flow
	Console Application
		1- End-user will browse products catalog		
		2- User add items to cart		
		3- User checkout items		
		4- New order is generated and persisted		
		5- AMQP producer send order summary to direct exchange 
	
	SpringArrivalConsumer Application		
		6- Listener receive order summary, print order summary and send confirmation email
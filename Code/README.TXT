DB credential:root/root
DB schema:springarrival

Rabbit credential guest/guest


Start with SpringArrivalMain Application - this app will create-drop DB, so it always should be the first application to start.
Whenever this application exits, we need to stop RestService because running RestService is keeping the connection to DB's schema then 
SpringArrivalMain couldn't drop and create tables for that schema.

	1. Run as Java application
Start SpringArrival_RestService App.
	1. Run As - Run on Server

Scenarios: 
Console Application
	1. Login and AUTHENTICATION	scenario
	   Valid Credential to Login: admin account:admin/admin or customer account: Asaid/password
		
	2. Use cases for admin account: admin/admin and can input one of these options below: 
			[1] List All items - End-user will browse products catalog
			[2] List All Users
			[3] List All Categories
			[4] Add user: to verify VALIDATION
			[5] Add Category: to verify VALIDATION 
			[6] Update Category Name
			[7] Add Item: to verify VALIDATION 
			[8] Update Item Price
			[9] Update Item Name
			[10] Import Product from CSV file: BATCH process to integrate products data from csv files and send notification EMAIL
			[11] List orders: call REST service to retrieve data
	3. Use cases for admin account: Asaid/password and can input one of these options below:
			[1] List all products
			[2] Show cart
			[3] Add product to cart
			[4] Remove product from cart
			[5] Checkout order
				- New order is generated and persisted		
				- AMQP producer send order summary to direct exchange 
				- Check SpringArrivalConsumer application to verify received order summary
	4. When exception happens: example when data is failed in VALIDATION case, 
		stacktrace will be logged to SpringArrival.log located in root folder of project SpringArrivalMain.

SpringArrivalConsumer Application		
	- Listener receive order summary, print order summary and send confirmation EMAIL

Rest Service
		-Lists order from DB
# CSE-682: Software Engineering
# Syracuse University
Project Name: Stay on Track
Group Members:
	Steven Brunjes
	Maha Hassan
	Ravjot Sachdev
	Jackson Taber
	Winnie Wong

Project Description: The Stay on Track application offers users a means to track and budget their finances, while offering statistics to help them analyse their own spending trends.

Installation process:
1. Install PostgreSQL 13 from https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
	1a. When prompted for a master password, enter 'postgres' as this is what is used by the application.
	1b. When prompted for a port, enter '5432'.
	1c. For everything else, use the default values.
	1d. Deselect the 'Stack Builder' option on the final step as this is not needed.
2. After installing PostgreSQL 13, run the PGAdmin application (under PostgreSQL 13 in the Start Menu) and follow the following steps:
	2a. When prompted to enter a master password, enter 'postgres' from step 1A above.
	2b. Expand the 'Servers' and 'PostgreSQL 13' tabs on the left-side menu.
	2c. Right-click 'Databases' in the left-side menu and select 'Create' --> 'Database'...
	2d. Enter the name 'CSE-682' next to 'Database' and select 'Save'.
3. Next, run the JAR file in this folder.
	3a. If you double-click the JAR file, it will run the application as a background process. You can terminate the application in Task Manager by right-clicking the 'Java(TM) Platform SE Binary' task and clicking 'End Task'.
	3b. Alternatively, you can run the application in a command prompt window with 'java -jar CSE-682-0.0.1-SNAPSHOT.jar'
4. Finally, go to http://localhost:8080 in your browser to use the application.
5. To register a user, click "Registration Page" and enter credentials.
6. After registration is complete, proceed to the login page and login with the newly created user.
7. You will be redirected to the Expenses page on a valid login.
8. The following is a brief description of each page. You can navigate between pages using the left-hand menu:
	8a. Expenses - Add expenses
	8b. Categories - Add new categories
	8c. Incomes - Add new incomes
	8d. Stats - View available statistics
9. To logout or change password, click the User Profile button in the top-right.
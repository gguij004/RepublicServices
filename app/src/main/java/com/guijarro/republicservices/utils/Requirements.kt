package com.guijarro.republicservices.utils

/**
 * Republic Services Take Home Challenge:
Your task is to build an Android application taking advantage of the clean architecture principles
and best practices. You can use any of the Jetpack libraries but use other third-party libraries
only if necessary.
REST API endpoint: https://d49c3a78-a4f2-437d-bf72-569334dea17c.mock.pstmn.io/data
General Requirements:
1. Call the remote API endpoint mentioned above.
2. Save driver and routes data in the local database.
3. Package name should include your last name (ie com.smith)
Main Screen:
1. Display the list of drivers from the local database.
2. Have a button in the top right corner which sorts the drivers based on their last name.
3. When the user selects one driver, the Route screen is presented, and her/his
route/routes is displayed based on the following algorithm (business logic):
a. If the driver id is the same as the route id then display the route.
b. If the driver id is divisible by 2 then show the first R type route.
c. If the driver id is divisible by 5 then display the second C type route.
d. If the driver id doesn’t meet any of the conditions above, then show the last I type
route.
Routes Screen:
1. Displays the route information (type and name of route) for the selected driver based
on the business logic above.
Send us:
- Full source code (GitHub or similar repository)
- Instructions to build the app if necessary.
Evaluation:
- SOLID architecture principles
- Code structure and readability
- Thinking pattern and best approach to solve the problem
 */


# Daily Mail Subscription Service
### POC Implemented using Java Spring-boot and Quartz Scheduler
### [Working Application](https://mail-subscription-quartz-java.herokuapp.com/mercury/swagger-ui.html)

#### Exposing 3 endpoints
* **/subscription/create**
  * Takes an email-address in the request body and subscribes the provided email-address to the daily mail subscription service
  * If subscribed at 11:47pm, the user will recieve a mail every day at the same time i.e 11:47pm via dynamic cron expressions
  * If Already subscribed, EmailAlreadyRegisteredException is thrown
  * A Quartz Trigger is created with the email-address being the identity (so that same email-address can not be subscribed twice) and added to a Quartz job responsible for sending the daily mail when the trigger is fired.
* **/subscription/status/{emailId}**
  * Tells if the provided emailId is registered to the daily mail subsription service i.e a trigger with identity same as the email address is scheduled with the created Quartz job
* **/subscription/cancel/{emailId}**
  * Unsubscribes the provided emailId from daily mail subscription service if it's registered i.e unschedules the trigger with identity same as the email address from the created Quartz Job
---
## Main classes and working flow
* [DailySubscriptionMailSenderJob.java](https://github.com/hardikSinghBehl/quartz-scheduler-daily-mail-subscription-spring-boot/blob/main/src/main/java/com/hardik/mercury/quartz/job/DailySubscriptionMailSenderJob.java)
  * Implements the Quartz Job interface and overrides execute(JobExecutionContext context) method
  * execute() method will be executed whenever a trigger registered to the job is fired
  * The method extracts the emailId from the JobExecutionContext and uses it to send mail to the user
* [DailyMailSubscriptionTriggerFactory.java](https://github.com/hardikSinghBehl/quartz-scheduler-daily-mail-subscription-spring-boot/blob/main/src/main/java/com/hardik/mercury/quartz/trigger/DailyMailSubscriptionTriggerFactory.java)
  * has a single generateTrigger() that takes in an emailAddress and returns a trigger for the above created job with identity as the same email-address
  * the created trigger is assigned a dynamic cron expression that will make the trigger fire exactly 24 hours to the current time
* [DailyMailSubscriptionScheduler.java](https://github.com/hardikSinghBehl/quartz-scheduler-daily-mail-subscription-spring-boot/blob/main/src/main/java/com/hardik/mercury/quartz/DailyMailSubscriptionScheduler.java)
  * Exposes methods to
    * Start the scheduler and assign the above created Job (Called during application startup by [DailyMailSchedulerInitializer.java](https://github.com/hardikSinghBehl/quartz-scheduler-daily-mail-subscription-spring-boot/blob/main/src/main/java/com/hardik/mercury/bootstrap/DailyMailSchedulerInitializer.java))
    * Add a new trigger to the scheduler
    * Remove a trigger with the scheduler
    * method returning boolean corresponding to whether trigger with identity same as provided emailId is registered with the scheduler.
---
## Setup

* Install Java 16
* Install Maven

Recommended way is to use [sdkman](https://sdkman.io/) for installing both maven and java

Run the below commands in the core

```
mvn clean
```

```
mvn install
```

Execute any of the two commands below to run the application

```
java -jar target/daily-mail-quartz-scheduler-spring-boot-0.0.1-SNAPSHOT.jar
```

```
mvn spring-boot:run
```

The Default port is 8080 and base-url is set to /mercury (both can be changed in application.properties)

Go to the below URI to view Swagger-UI (API-docs)

```
http://localhost:8080/mercury/swagger-ui.html
```

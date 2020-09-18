# SpringSecurityThymeleafJPAMockito

About The Application
=====================

This is a rich client web application, with a Java web API exposing data to an HTML+CSS+JS browser client. The webapp is
built with Spring Boot, allows users to add,modify,delete product offers.
 
Once you edit the Products, Offer Status will be calculated using Cron Schedules and page will be refreshed automatically every 10 seconds

It uses Spring Security, JPA , Thymeleaf for UI and Tests with Mockito 

See Screenshots.docx file for testscreens

Generate As Deployable
-------------------

mvn clean install

Go to target folder and rename the war TestServiceUI-0.0.1-SNAPSHOT to TestServiceUI.war

Take the war file and deploy in tomcat.

http://<servername>:<port>/TestServiceUI

REST API
========= 
The REST API is described below.

1. GET ​/ 
MethodName : getProducts

Home Page Url 

2. POST ​/addOffer
MethodName : addProduct

Add Product Offers to the Repo

3. GET ​/delete​/{id}
MethodName : deleteProduct

Delete the Product based on path parameter

4. GET ​/edit​/{id}
MethodName : showUpdateForm

Open up the Product in Form

5. GET ​/index
MethodName : listOffers

Home Page Url , Lists all offers

6. GET ​/offer
MethodName : showOfferForm

Open up the ADD Product  Form

7. POST ​/update​/{id}
MethodName : updateProduct

Update the Product based on path parameter


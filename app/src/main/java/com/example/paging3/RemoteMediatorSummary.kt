package com.example.paging3

/*
Remote Mediator is used for providing offline pagination in your application...

firstly remote mediator fetch the data from the api
and then stored into local database then data comes for use:

          Example :

Suppose user started scrolling and then your page goes to finished so remote Mediator
fetch the data from the api then stored into database and after it'll be show you...


       Benefits:
Provides Offline support for application
Remote Mediator automatically handles the data and error



            REMOTE KEY'S
 they contains page's information like page's item id their previous key, next key
without remote keys mediator don't find the next or previous key for page and it will show the same data again
also the whole dataset

So Remote Keys is necessary to create...
* when you're creating the entity for api also create the table for remote key


3 works you have to do in the Remote Mediator class:
* Fetch the data from the Api
* save the data and the remote keys into DB
* Create logic for Append , Prepend, Refresh

 */

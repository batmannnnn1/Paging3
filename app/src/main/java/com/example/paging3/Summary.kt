package com.example.paging3

/*
when we use recyclerview to show the api data...so it'll be fetch the all the object
from the source and then recycled the view....
but this is effect our app performance...

so we'll be use Paging3 for this... what will Paging3 do ??

paging3 load the large datasets into the chunks(Page)
when the use started scrolling the data will be fetch from the source it'll be great for you app performance...
unlike the recycler view who fetch all the datasets and then show them...

        Three implementation you have to do when you use Paging3:

 1-> Paging Source [You have to connect your datasource to Paging Source]
 2-> Pager [You have tell what's your Page size and how many pages you show at a time]
 3-> Paging Adapter [Paging Adapter connect your data with Recycler view...]


 */
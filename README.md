** Version 0.0.1**

# UserList Application
Motivation: A demo application created for customers to create a list, say, WishList, ShoppingList, RegistryList....you name it !


## Technology stack
SpringBoot, Embeded Mongo, Junit5/AssertJ, Docker

## Methodologies
DDD, TDD, Containerized, 


## How to run locally?

If you have docker installed, 

run >  $ docker run -p 8080:8080 jamesalumkal/listapp

OR 

Download the project from github and run as a springboot application.

Following are the service details

Get UserList: GET: http://localhost:8080/userList/profileId/<ProfileId>

Create Userlist: POST: http://localhost:8080/userList
```
{
   "listName":"MyUserListName",
   "profileId":"ProfileId100",
   "listType":"REGULAR",
   "listAccessType":"PUBLIC",
   "userListProducts":[
      {
         "productId":"PRD100",
         "productTitle":"PRDTitle100"
      },
      {
         "productId":"PRD101",
         "productTitle":"PRDTitle101"
      }
   ]
} 
```

Add Products to Userlist: PUT: http://localhost:8080/userList/userListProduct
```
{
   "listId": "LISTID100",
   "profileId":"ProfileId100",
   "userListProducts":[
      {
         "productId":"PRD102",
         "productTitle":"PRDTitle102"
      }
   ]
}
```


Remove Products from UserList: DEL: 
```
{
   "listId": "LISTID100",
   "profileId":"ProfileId100",
   "userListProducts":[
      {
         "productId":"PRD102"
      }
   ]
}
```







---
###### Licence & copyright
© James Alumkal

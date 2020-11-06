** Version 0.0.1**

# UserList Application
Motivation: A demo application created for customers to create a list, say, WishList, ShoppingList, RegistryList....you name it !


## Technology stack
SpringBoot, Embeded Mongo, Junit5/AssertJ

## Methodologies
DDD, TDD, Containerized, 

## Deployment
Docker, GCP Kubernetis engine 

## How to run locally?

If you have docker installed, 

run >  $ docker run -p 8080:8080 jamesalumkal/listapp

OR 

Download the project from github and run as a springboot application.

## How to run K8s engine?
Log into your gcloud container clusters

Upload the k8s-listapp.yaml to your cluster through gcloud shell

run > $ kubectl apply -f k8s-listapp.yaml

Access the application services on the exposed IP:port


## Service details

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

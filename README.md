** Version 0.0.1**

# UserList Application
Motivation: A demo application created for customers to create a list, say, WishList, ShoppingList, RegistryList....you name it !


## Technology stack
SpringBoot, Embeded Mongo, Junit5/AssertJ

## Methodologies
DDD, TDD, Containerized, 

## Deployment
Docker, GCP Kubernetis engine 

## Development Steps
1. Develop an executable spring boot Project

	a. make sure parent pom has the <finalName> inside <build>

2. Create a docker file
		Refer: Dockerfile
		
3. Build a local image of the docker

	a. Open terminal, cd to the project source dir where the Dockerfile (mostly project root)
	
	b. docker login, enter userid, password of the dockerhub
	
	c. run > $ docker build -t <application name> .
	
		ex: docker build -t list-app .	
		
	d. view the created image, run > $ docker images
	
	 
4. Tag and Push image to docker hub

	a. run > $ docker tag <local docker Image> <docker-user>/<name>
	
			ex: docker tag list-app <docker-user>/listapp
	
	
	b. run > $ docker push <docker-user>/listapp
	

5. Clean local, Pull and Run Image from docker hub


	a. remove the local images of the app
	
		run > $ docker rmi list-app <docker-user>/listapp
	
	b. docker run -p 8080:8080 jamesalumkal/listapp
	

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
###### Licence & Copyright
© James Alumkal

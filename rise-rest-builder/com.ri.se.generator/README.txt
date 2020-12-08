RISE REST API Creator
Introduction:
REST API and micro services are the most common part of the modern software development techniques. It facilitates software developers to build the backend modules and add required business logic at the appropriate place. In current scenario, use's module (web site, mobile application, terminals, IoT devices) communicated with the REST endpoints which processes the data and stored in the database. This software provides a source code of generating REST API templates based on the defined object in the JSON. This JSON Object has following fields. 


  
mavenGroupId: If you are familiar with Maven then it is the Group ID, This is a String filed and must wualify the rules used for defining a package in java. Default values are ri.se
mavenArtifact":null,
   "className":"DAPRequest",
   "out":"/home/blockchain/Desktop/tvsp/dap/",
   "port":0,
   "column":{
      "metaData":"String",
      "role":"String",
      "sha256":"String",
      "reqRecDate":"Date",
      "reqProcessedBy":"Date",
      "reqProcessedDate":"Date",
      "ecdsa":"String",
      "publicKeyEC":"String ",
      "others":"String"
   },
   "idColumn":"sha256"
}


 


Most of applications communicate with REST API and then store objects or data into the database. 
The software is developed in Java and executed as an application. In order to execute, you need java (1.8 or higher version) and maven for building generated REST API.
  

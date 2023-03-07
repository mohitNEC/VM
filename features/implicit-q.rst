************************
Implicit Attribute by q
************************

According to the new specifications we can use "ngsi-ld/v1/entities?q=attrX" for querying the data which we called as Implicit Attribute by q.

 - For the GET API to add the **/ngsi-ld/v1/entities?q=attrX** and based on q to identify this request and get response data.
 - The NGSI-LD query language allows filtering entities according to the existence of attributes.
 - For example:  GET **"/entities?q=attrX"** will return only those entities that have an attribute called attrX. Regardless of the value of said attribute, if you instead want to filter on a value of an attribute: GET **"/entities?q=attrX==12"**

Example for Implicit Attribute by q
------------------------------------

1. Create Operation
======================

In order to create an entity, we can hit the endpoint **http://<IP Address>:<port>/ngsi-ld/v1/entities/**  with the given payload.

.. code-block:: JSON

 {
     "id": "urn:ngsi-ld:Vehicle:A106",
     "type": "Vehicle",
     "brandName": {
         "type": "Property",
         "value": "Swift"
     },
     "isParked": {
         "type": "Relationship",
         "providedBy": {
             "type": "Relationship",
             "object": "urn:ngsi-ld:Person:Bob"
         },
         "object": "urn:ngsi-ld:OffStreetParking:Downtown1"
     },
     "speed": {
         "type": "Property",
         "value": 120,
         "observedAt": "2020-08-01T12:00:00Z"
     },
     "temperature": {
         "type": "Property",
         "value": 25,
         "observedAt": "2022-03-14T01:59:26.535Z",
         "unitCode": "CEL"
     },
     "location": {
         "type": "GeoProperty",
         "value": {
             "type": "Point",
             "coordinates": [
                 -8.6,
                 41.6
             ]
         }
     }
 }


**EXAMPLE**: Give back the Entity where brand name is equals to "Swift"

To retrieve entity where brandName is equals to "Swift" you can send an HTTP GET to - **http://<IP Address>:<port>/ngsi-ld/v1/entities?q=brandName=="Swift"** and we will get all the entities having brand name equals to Swift.
	
Response:

.. code-block:: JSON

 [
     {
         "id": "urn:ngsi-ld:Vehicle:A106",
         "type": "Vehicle",
         "brandName": {
             "type": "Property",
             "value": "Swift"
         },
         "isParked": {
             "type": "Relationship",
             "providedBy": {
                 "type": "Relationship",
                 "object": "urn:ngsi-ld:Person:Bob"
             },
             "object": "urn:ngsi-ld:OffStreetParking:Downtown1"
         },
         "speed": {
             "type": "Property",
             "value": 120,
             "observedAt": "2020-08-01T12:00:00Z"
         },
         "temperature": {
             "type": "Property",
             "value": 25,
             "observedAt": "2022-03-14T01:59:26.535Z",
             "unitCode": "CEL"
         },
         "location": {
             "type": "GeoProperty",
             "value": {
                 "type": "Point",
                 "coordinates": [
                     -8.6,
                     41.6
                 ]
             }
         }
     }
 ]

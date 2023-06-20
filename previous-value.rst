*********************************************
Previous Value Support (Notification)
*********************************************

We have added new configurable trigger scenarios in notification. The notification data need to be improved in order to share useful data to subscriber. For that, we are introducing new property **previousValue** to be send in the notification if subscriber enable **showChanges** property.

**"previousValue":** only provided in case of notifications and if the showChanges option is explicitly requested. It represents the previous Property Value, before the triggering change

+-------------+-------------+------------------+-------------+--------------------------------------------------------------+
| Name        | DataType    | Restrictions     | Cardinality | Description                                                  |
+=============+=============+==================+=============+==============================================================+
| showChanges | boolean     | false by default | 0..1        | If true, the previous value (previousValue) of Properties    |
|             |             |                  |             | or languageMap (previousLanguageMap) of Language Properties  |
|             |             |                  |             | or object (previousObject) of Relationships is provided in   |
|             |             |                  |             | addition to the current one. This requires that it exists,   |
|             |             |                  |             | i.e. in case of modifications and deletions, but not in the  |
|             |             |                  |             | case of creations. showChanges cannot be true in case format |
|             |             |                  |             | is "keyValues"                                               |
+-------------+-------------+------------------+-------------+--------------------------------------------------------------+


Example for Previous Value Support
------------------------------------

1. Create Subscription
========================

In order to create an subscription, we can hit the endpoint POST **http://<IP Address>:<port>/ngsi-ld/v1/subscriptions** with the given payload.

Payload:

.. code-block:: JSON

 {
     "id": "urn:ngsi-ld:Subscription:001",
     "type": "Subscription",
     "entities": [
         {
             "type": "Vehicle"
         }
     ],
     "notification": {
         "showChanges": true,
         "endpoint": {
             "uri": "mqtt://localhost:1883/notify",
             "accept": "application/json"
         }
     }
 }


2. Create Entity
===================

In order to create an entity, we can hit the endpoint POST **http://<IP Address>:<port>/ngsi-ld/v1/entities** with the given payload.

Payload:

.. code-block:: JSON

 {
     "id": "urn:test:testentity01",
     "type": "Vehicle",
     "brandName": {
         "type": "Property",
         "value": "Mercedes"
     },
     "speed": {
         "type": "Property",
         "value": 91
     }
 }


3. Entity Creation Notification
=================================

After creating the entity we will get the notification for Entity creation. NGSI-LD supports two types of endpoints for subscriptions. HTTP(S) and MQTT(S). As you can see currently we are using an MQTT endpoint in subscription.

MQTT Notification:

.. code-block:: JSON

 {
     "body": {
         "id": "notification:-5854452942666568672",
         "type": "Notification",
         "subscriptionId": "urn:ngsi-ld:Subscription:001",
         "notifiedAt": "2023-06-16T02:01:00.335000Z",
         "data": [
             {
                 "id": "urn:test:testentity01",
                 "type": "Vehicle",
                 "brandName": {
                     "type": "Property",
                     "value": "Mercedes"
                 },
                 "speed": {
                     "type": "Property",
                     "value": 91
                 }
             }
         ]
     }
 }


4. Partial Update Attribute
============================

In order to see how Previous Value Support feature works we simply update an entity and for that we can hit the endpoint PATCH **http://<IP Address>:<port>/ngsi-ld/v1/entities/{entityId}/attrs/{attrName}**

For this tutorial we can hit the endpoint - **http://localhost:9090/ngsi-ld/v1/entities/urn:test:testentity01/attrs/brandName** with the given payload.

.. code-block:: JSON

 {
     "type": "Property",
     "value": "BMW"
 }


5. Entity Update Notification
===============================
 
After creating the entity we will get the notification for Entity update as follows:
 
.. code-block:: JSON

 {
     "body": {
         "id": "notification:-5497055590466985753",
         "type": "Notification",
         "subscriptionId": "urn:ngsi-ld:Subscription:001",
         "notifiedAt": "2023-06-16T02:16:27.278000Z",
         "data": [
             {
                 "id": "urn:test:testentity01",
                 "type": "Vehicle",
                 "brandName": {
                     "type": "Property",
                     "previousValue": "Mercedes",
                     "value": "BMW"
                 },
                 "speed": {
                     "type": "Property",
                     "value": 91
                 }
             }
         ]
     }
 }
 
So, here in the notification we can see that we are getting an extra parameter **previousValue** which shows us the previous value of brandName attribute as we have enabled "showChanges" while creating subscription.
 
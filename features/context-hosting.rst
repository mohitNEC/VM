****************
Context Hosting
****************

NGSI-LD Brokers optionally offer the capability to store and serve @contexts to clients. The stored @contexts may be managed by clients directly, via the APIs specified. Clients can store custom user @contexts at the Broker, effectively using the Broker as a @context server.
Moreover, in order to optimize performance, NGSI-LD Brokers may automatically store and use the stored copies of common @contexts as a local cache, downloading them just once, thus avoiding fetching them over and over again at each NGSI-LD request. In order for the Broker to understand if a needed @context is already in the local storage or not, the Broker uses the URL, where the @context is originally hosted, as an identifier for it in the local storage.

There are following cases handles to manage @context server:

- **Cached** : To be auto cached when broker request from external url, cached context will not be served by the broker on demand, and cached context should be invalidate after a fixed time.

- **Hosted** : When user post @context, it need to be stored in database with a unique id.

- **ImplicitlyCreated** : When a subscription body contains an array of contexts and notification is to be given in the form of application/json then these context should be hosted in broker and marked as implicitly created. 

We can store @context to database and also download from other resource and store in the in-memory like cache memory. 
 
**POST API**

	Resource URI  : /jsonldContexts

	Request body: JSON Object 
	
	Payload body in the request contains a JSON object that has a root node named @context, which represents a JSON-LD "local context".
	
	Add new table in existing database for store the context:
	
	Table name: context
	
	Column Name :: id , body , kind , timestamp

	Response Body: 
	 - 201 Created: The HTTP response shall include a local URI to the added @context


**GET API**

•	Resource URI : **/jsonldContexts** 

	Response Body: 200 Ok URL[] (show the list of @contexts)

•	Resource URI : **/jsonldContexts?details=true**

	Response Body: 200 OK  {URL,id, more details} [(show the list of @contexts) with context details]
	
	Details: Boolean
	
	Whether a list of URLs or a more detailed list of JSON Objects is requested
	
	Kind: String
	
	Can be either *"Cached"*, *"Hosted"*, or *"ImplicitlyCreated"*.

**DELETE API**

•	/jsonldContexts/{contextId}

	204 No content
	
•	/jsonldContexts/{contextId} ?reload=true

	204 No context 


Example for Context Hosting
############################

1. **POST API**
::
	curl --location --request POST 'http://localhost:9090/ngsi-ld/v1/jsonldcontext ' \
	--header 'Content-Type: application/json' \
	--data-raw '{
		"@context": {
			"stringproperty": "http://testdom.com/stringproperty",
			"intproperty": "http://testdom.com/intproperty",
			"floatproperty": "http://testdom.com/floatproperty",
			"complexproperty": "http://testdom.com/complexproperty",
			"testrelationship": "http://testdom.com/testrelationship",
			"TestType": "http://testdom.com/TestType"
		}
	}'
	
2. **GET API**
::
	•	curl --location --request GET 'http://localhost:9090/ngsi-ld/v1/jsonldcontext ' \
		--header 'Content-Type: application/json' \
		--data-raw ''
		
	Response:

		[
			"http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:9155d599-0db4-4fb0-91ba-4f478090b0fc",
			"http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:a507987f-14ed-42c9-8beb-dfe971afdb3f",
			"http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:66edf2ba-61df-4710-8249-77de034d3a80",
			"http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:ff7d8473-d08b-4bde-9e2b-a37e5179d40e"
		]
		
	•	curl --location --request GET 'http://localhost:9090/ngsi-ld/v1/jsonldcontext?details=true' \
		--header 'Content-Type: application/json' \
		--data-raw ''

	Response:

		[
			{
				"id": "urn:9155d599-0db4-4fb0-91ba-4f478090b0fc",
				"body": {
					"@context": {
						"TestType": "http://testdom.com/TestType",
						"intproperty": "http://testdom.com/intproperty",
						"floatproperty": "http://testdom.com/floatproperty",
						"stringproperty": "http://testdom.com/stringproperty",
						"complexproperty": "http://testdom.com/complexproperty",
						"testrelationship": "http://testdom.com/testrelationship"
					}
				},
				"kind": "hosted",
				"timestmp": "2023-02-09T11:10:07.707324",
				"url": "http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:9155d599-0db4-4fb0-91ba-4f478090b0fc"
			},
			{
				"id": "urn:a507987f-14ed-42c9-8beb-dfe971afdb3f",
				"body": {
					"@context": {
						"TestType": "http://testdom.com/TestType",
						"intproperty": "http://testdom.com/intproperty",
						"floatproperty": "http://testdom.com/floatproperty",
						"stringproperty": "http://testdom.com/stringproperty",
						"complexproperty": "http://testdom.com/complexproperty",
						"testrelationship": "http://testdom.com/testrelationship"
					}
				},
				"kind": "hosted",
				"timestmp": "2023-02-09T11:10:21.586499",
				"url": "http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:a507987f-14ed-42c9-8beb-dfe971afdb3f"
			},
			{
				"id": "urn:66edf2ba-61df-4710-8249-77de034d3a80",
				"body": {
					"@context": {
						"TestType": "http://testdom.com/TestType",
						"intproperty": "http://testdom.com/intproperty",
						"floatproperty": "http://testdom.com/floatproperty",
						"stringproperty": "http://testdom.com/stringproperty",
						"complexproperty": "http://testdom.com/complexproperty",
						"testrelationship": "http://testdom.com/testrelationship"
					}
				},
				"kind": "hosted",
				"timestmp": "2023-02-09T11:10:22.573511",
				"url": "http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:66edf2ba-61df-4710-8249-77de034d3a80"
			},
			{
				"id": "urn:ff7d8473-d08b-4bde-9e2b-a37e5179d40e",
				"body": {
					"@context": {
						"TestType": "http://testdom.com/TestType",
						"intproperty": "http://testdom.com/intproperty",
						"floatproperty": "http://testdom.com/floatproperty",
						"stringproperty": "http://testdom.com/stringproperty",
						"complexproperty": "http://testdom.com/complexproperty",
						"testrelationship": "http://testdom.com/testrelationship"
					}
				},
				"kind": "hosted",
				"timestmp": "2023-02-09T11:10:24.128558",
				"url": "http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:ff7d8473-d08b-4bde-9e2b-a37e5179d40e"
			}
		]

	•	curl --location --request GET 'http://localhost:9090/ngsi-ld/v1/jsonldcontext?kind=hosted' \
		--header 'Content-Type: application/json' \
		--data-raw ''

	Response:

		[
			"http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:9155d599-0db4-4fb0-91ba-4f478090b0fc",
			"http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:a507987f-14ed-42c9-8beb-dfe971afdb3f",
			"http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:66edf2ba-61df-4710-8249-77de034d3a80",
			"http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:ff7d8473-d08b-4bde-9e2b-a37e5179d40e"
		]

	•	curl --location --request GET 'http://localhost:9090/ngsi-ld/v1/jsonldcontexts/urn:9155d599-0db4-4fb0-91ba-4f478090b0fc' \
		--header 'Content-Type: application/json' \
		--data-raw ''

	Response:

		{
			"@context": {
				"TestType": "http://testdom.com/TestType",
				"intproperty": "http://testdom.com/intproperty",
				"floatproperty": "http://testdom.com/floatproperty",
				"stringproperty": "http://testdom.com/stringproperty",
				"complexproperty": "http://testdom.com/complexproperty",
				"testrelationship": "http://testdom.com/testrelationship"
			}
		}

	•	curl --location --request GET 'http://localhost:9090/ngsi-ld/v1/jsonldcontexts/urn:9155d599-0db4-4fb0-91ba-4f478090b0fc?details=true' \
		--header 'Content-Type: application/json' \
		--data-raw ''

	Response:

		{
			"id": "urn:9155d599-0db4-4fb0-91ba-4f478090b0fc",
			"body": {
				"@context": {
					"TestType": "http://testdom.com/TestType",
					"intproperty": "http://testdom.com/intproperty",
					"floatproperty": "http://testdom.com/floatproperty",
					"stringproperty": "http://testdom.com/stringproperty",
					"complexproperty": "http://testdom.com/complexproperty",
					"testrelationship": "http://testdom.com/testrelationship"
				}
			},
			"kind": "hosted",
			"timestmp": "2023-02-09T11:10:07.707324",
			"url": "http://localhost:9090/ngsi-ld/v1/jsonldContexts/urn:9155d599-0db4-4fb0-91ba-4f478090b0fc"
		}
	
3. **DELETE API**
::
	•	curl --location --request DELETE 'http://localhost:9090/ngsi-ld/v1/jsonldcontexts/urn:9155d599-0db4-4fb0-91ba-4f478090b0fc' \
		--header 'Content-Type: application/json' \
		--data-raw ''

	Response : 204 No content

	•	curl --location --request DELETE 'http://localhost:9090/ngsi-ld/v1/jsonldcontexts/urn:9155d599-0db4-4fb0-91ba-4f478090b0fc?reload=true' \
		--header 'Content-Type: application/json' \
		--data-raw ''
		
	Response : 204 No content

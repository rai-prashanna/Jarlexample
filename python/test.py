import requests
serverIP= "localhost"
serverPort= "8181"
coarsegrainedendpoint = "http://"+serverIP+":"+serverPort+"/v1/data/authz/redfish/v1/policy";
response = requests.get(coarsegrainedendpoint+"?metrics")
print(response)
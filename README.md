main module here AccesslimitingApplication
User0..9 is populated before start automatically.
you can run next request 5 times only. on 6th time it will be blocked with the error.

curl -X GET "http://localhost:8080/api/rateLimit/consumeQuota/User2" \
-H "Content-Type: application/json" \
-H "Accept: application/json"

mySql db connection details are the following( check application.properties)
spring.datasource.url=jdbc:mysql://localhost:3306/access_limiting
spring.datasource.username=root
spring.datasource.password=root

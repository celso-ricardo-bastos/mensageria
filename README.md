# Kafka
![alt text](image.png)

## Up the app
docker compose up -d

## Access the queen in the localhost pot 8282
http://localhost:8282/

## App fluxo of the Order and Payment
![alt text](image-1.png)
![alt text](image-2.png)


## CURL to register topic
curl --location 'http://localhost:8081/api/orders' \
--header 'Content-Type: application/json' \
--data '{
  "customerId": "user_celso_1001",
  "totalAmount": 152458.000
}'

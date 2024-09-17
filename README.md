# Read Me

The original idea is from  
https://bohutskyi.com/security-mtls-in-spring-boot-aef44316dd4b

# Read Me

The original idea is from  
https://bohutskyi.com/security-mtls-in-spring-boot-aef44316dd4b

# Generate the CA Certificate

Create the CA’s Private Key:

```
openssl genrsa -out ca.key 2048
```

Self-Sign and Create the CA Certificate. This creates a self-signed CA certificate valid for 365 days.

```
openssl req -x509 -new -nodes -key ca.key -sha256 -days 365 -out ca.pem
```

# Generate Server and Client Certificates

Generate Private Keys

```
For the server: openssl genrsa -out server.key 2048

For the client: openssl genrsa -out client.key 2048
```

Generate CSRs:

```
For the server: openssl req -new -key server.key -out server.csr

For the client: openssl req -new -key client.key -out client.csr
```

Sign CSRs with the CA Key:

```
For the server: openssl x509 -req -in server.csr -CA ca.pem -CAkey ca.key -CAcreateserial -out server.crt -days 365

For the client: openssl x509 -req -in client.csr -CA ca.pem -CAkey ca.key -CAcreateserial -out client.crt -days 365
```

# Create PKCS#12 Keystores

Convert Certificates and Keys to PKCS#12 Format:

```
For the server: openssl pkcs12 -export -out server.p12 -name "server" -inkey server.key -in server.crt -certfile ca.pem

For the client: openssl pkcs12 -export -out client.p12 -name "client" -inkey client.key -in client.crt -certfile ca.pem
```

# Create the Truststore

Import the CA Certificate into a PKCS#12 Truststore:

```
keytool -import -file ca.pem -alias "ca" -keystore truststore.p12 -storetype PKCS12
```



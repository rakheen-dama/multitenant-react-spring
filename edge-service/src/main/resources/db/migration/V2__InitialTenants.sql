INSERT INTO tenant_config(identifier, client_id, client_secret, issuer)
VALUES ('fumbles', 'edge-service', 'secret', 'http://localhost:8080/realms/fumbles'),
       ('dropzone', 'edge-service', 'secret', 'http://localhost:8080/realms/dropzone');
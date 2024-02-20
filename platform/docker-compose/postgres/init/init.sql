CREATE USER keycloak with PASSWORD 'keycloak' CREATEDB;
CREATE DATABASE keycloak
    WITH
    OWNER  = keycloak
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE USER grafana with PASSWORD 'grafana' CREATEDB;
CREATE DATABASE grafana
    WITH
    OWNER  = grafana
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE USER tenant with PASSWORD 'tenant' CREATEDB;
CREATE DATABASE tenant
    WITH
    OWNER  = tenant
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE USER saastemplate with PASSWORD 'saastemplate' CREATEDB;
CREATE DATABASE "saas-template"
    WITH
    OWNER  = saastemplate
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

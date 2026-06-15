# Bantui WebApp

The application targets Java 25, Jakarta EE 10 and TomEE 10.1.5. TomEE runs Hibernate
ORM 6.6 as the Jakarta Persistence provider and uses HSQLDB by default:

```bash
mvn tomee:run
```

The `postgresql` profile switches the datasource to PostgreSQL and validates the existing
schema without creating or modifying tables:

```bash
mvn -Ppostgresql tomee:run
```

Data access intentionally uses Jakarta Persistence 3.1 repositories. Jakarta Data 1.0
belongs to Jakarta EE 11, so adopting it is deferred until the application moves beyond
TomEE 10 and Jakarta EE 10.

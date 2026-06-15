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

## Executable JAR

The `executable` profile packages the WAR and TomEE Plus into a self-contained runnable
JAR:

```bash
mvn clean package -Pexecutable
cd target
java -jar bantui-exec.jar
```

The first run extracts TomEE under `target/.distribution`; subsequent runs reuse that
directory. Build the PostgreSQL variant by combining both profiles:

```bash
mvn clean package -Pexecutable,postgresql
```

Data access intentionally uses Jakarta Persistence 3.1 repositories. Jakarta Data 1.0
belongs to Jakarta EE 11, so adopting it is deferred until the application moves beyond
TomEE 10 and Jakarta EE 10.

## Debugging in VS Code

This project is a Jakarta EE WAR, so TomEE is its application entry point instead of a
Java `main` method. Open **Run and Debug** in VS Code and select
`Debug Bantui on TomEE`. The configured task builds the WAR, starts TomEE with JDWP on
port `5005`, and attaches the Java debugger.

You can also start the server manually and then use `Attach to running TomEE`:

```bash
mvn clean package tomee:debug -Dtomee-plugin.debugPort=5005
```

export HOST=localhost
export USER=postgres
export PASSWORD=admin
export PORT=5433
export PORT_API=8592
export PATH=$PATH:$JAVA_HOME/bin
export KEYCLOAK_URL=http://localhost:9980/realms/JETHRO
export KEYCLOAK_JETHRO_API_CLIENT=jethro-apis
export KEYCLOAK_JETHRO_API_SECRET=jM7Zzras5tG72bFDEKc1H39U4DGLI9yL
echo $JAVA_HOME
./mvnw quarkus:dev -Dquarkus.http.host=0.0.0.0
build:
	mvn clean install

run: build
	java -jar target/finplan-calc*.jar
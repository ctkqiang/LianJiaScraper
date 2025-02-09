run:
	- mvn spring-boot:run

clean:
	- mvn clean compile

debug:
	- mvn spring-boot:run -Dspring-boot.run.arguments=--debug
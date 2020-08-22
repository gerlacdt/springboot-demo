.PHONY: build
build:
	gradle build

.PHONY: test
test:
	gradle test

.PHONY: test-int
test-int:
	gradle test-int

.PHONY: test-unit
test-unit:
	gradle test-unit

.PHONY: clean
clean:
	gradle clean

.PHONY: run
run:
	gradle bootRun

docker-build:
	docker build -t springboot-demo .

.PHONY: docker-run
docker-run:
	docker run -d -p 8080:8080 --name demo springboot-demo

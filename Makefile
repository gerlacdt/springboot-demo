.PHONY: build
build:
	gradle build

.PHONY: test
test:
	gradle test

.PHONY: run
run:
	gradle bootRun

docker-build: build
	docker build -t springboot-demo .

.PHONY: docker-run
docker-run:
	docker run -d -p 8080:8080 springboot-demo

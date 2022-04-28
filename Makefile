Maven_clean_install:
	mvn clean install -Dmaven.test.skip=true

Pull_latest_maria_database:
	docker pull mariadb

Pull_latest_maria_database_(M1):
	docker pull --platform linux/amd64 mariadb

Create_volume:
	docker create -v /tmp/mysql --name yeczanedata mariadb

Create_volume_(M1):
	docker create --platform linux/amd64 -v /tmp/mariadb --name yeczanedata mariadb

Run_database_with_volume:
	docker run --name yeczanedb --volumes-from yeczanedata -e MARIADB_ROOT_PASSWORD=123456 -p 3307:3306 mariadb

Run_database_with_volume_(M1):
	docker run --platform linux/amd64 --name yeczanedb --volumes-from yeczanedata -e MARIADB_ROOT_PASSWORD=123456 -p 3307:3306 mariadb

Create_database:
	docker exec -it yeczanedb mariadb --user=root --password=123456 sys -e "CREATE DATABASE test_db;"

Docker_compose:
	docker-compose up

Heroku_container_login:
	heroku container:login

Heroku_push:
	heroku container:push web -a yeczane

Heroku_release:
	heroku container:release web -a yeczane

Heroku_spring_profile:
	heroku config:set SPRING_PROFILES_ACTIVE=docker

Build_for_Heroku:
	docker buildx build --platform linux/amd64 -t yeczane .

Tag_for_heroku:
	docker tag yeczane registry.heroku.com/yeczane/web

Push_to_heroku:
	docker push registry.heroku.com/yeczane/web

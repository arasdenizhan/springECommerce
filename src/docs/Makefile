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


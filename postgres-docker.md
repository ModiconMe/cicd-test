* `docker run --name db -p 5432:5432 --network=db -v "PWD:/var/lib/postgresql/data" -e POSTGRES_PASSWORD=password -d postgres:alpine`

* `docker run -it --rm --network=db postgres:alpine psql -h db -U postgres` - подсоединение к контейнеру db по имени (находятся в одной сети db)
* `docker run --name db -p 5432:5432 --network=db -d -v //e/stud/spring-boot-fullstack/db_data:/var/lib/postgresql/data postgres:alpine`
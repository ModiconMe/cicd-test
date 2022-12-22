### Jib


#### Команды
`mvnw clean install jib:dockerBuild -D jib.to.image=fullstack:v1` - build контейнера с помощью
jib maven plugin

`./mvnw jib:build -D jib.to.image=modiconme/spring-react-fullstack:v1` -
пуш контейнера в docker hub, требуется docker login

`./mvnw jib:build -D jib.to.image=modiconme/spring-react-fullstack:v1
-D jib.to.auth.username=username -D jib.to.auth.password=password` - без docker login

`./mvnw clean install -P build-frontend -P jib-push-to-dockerhub -D app.image.tag=1` - пуш в docker hub с настроенными
профилями maven (-P - профиль, -D app.image.tag=1 - пропертис в maven файле)
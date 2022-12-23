ssh -i C:\Users\rebee\.ssh\yandex_cloud_compute_ssh1 saeed@158.160.41.205

sudo systemctl enable docker.service
sudo systemctl enable containerd.service

### CICD

1. В папке .github/workflows создаем файл [deploy.yml](../.github/workflows/deploy.yml) в нем прописывается основная конфигурация.
   Используется action https://github.com/yc-actions/yc-coi-deploy/blob/main/action.yml
2. Конфиги
* yc-sa-json-credentials - https://cloud.yandex.ru/docs/container-registry/operations/authentication#sa-json
* folder-id - id папки с виртуальными машинами
* VM-name - имя виртуальной машины
* vm-service-account-id - id сервисного аккаунта
* vm-subnet-id - id подсети
* [user-data](../yandexcloud/user-data.yml) - сведения о создаваемом юзере
* [docker-compose](../yandexcloud/docker-compose.yml) - файл из которого будет создаваться машина

3. Требуется создать пользователя (который будет создавать машину) с правами resource-manager.admin и compute.admin

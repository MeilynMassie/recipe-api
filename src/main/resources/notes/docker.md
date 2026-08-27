### DOCKER

---

1. TO REMOVE ONE CONTAINER
    `sudo docker rm -f <CONTAINER_NAME>`
2. TO REMOVE ALL CONTAINERS:
    `sudo docker rm -f $(sudo docker ps -aq)`
3. IF RUNNING ONLY ONE CONTAINER AND DON'T HAVE A COMPOSE.YAML
    ```
    sudo docker run -d \
    --name <CONTAINER_NAME> \
    -p 8080:8080 \
    -e DB_URL="jdbc:<DB_SOURCE>://<DB_CONTAINER_NAME>/<DB_NAME>" \
    -e DB_USERNAME="<DB_USERNAME>" \
    -e DB_PASSWORD="<DB_PASSWORD>" \
    <CONTAINER_NAME>
    ```
4. TO TEST FROM SCRATCH (-v deletes volume data for postgres too):

   a. `sudo docker compose down -v --remove-orphans`

   b. `sudo docker ps -a`

   c. `sudo docker compose up -d --build`
6. CONNECT TO PSQL
    `sudo docker exec -it postgres psql -U <username> -d <database_name>`
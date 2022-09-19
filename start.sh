name=web-domain
docker stop $name
docker rm $name
docker build -t $name:prd .
docker run -d -p 9000:9000 -e "SPRING_PROFILES_ACTIVE=prd" -v /root/docker/web-domain/logs:/web-domai/logs:rw -v /root/docker/web-domain/upload:/web-domai/upload:rw --name $name $name:prd

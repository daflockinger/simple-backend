
echo "Building application"
./mvnw clean install
echo "Creating docker image for app"
docker build -t daflockinger/simple-backend .
echo "Starting app and DB"
docker-compose up
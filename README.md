# HOW TO RUN



### RUN DEV 
1. Pastikan maven sudah terinstall dan menggunakan jdk 8 / 1.8
2. Jika menggunakan intellij, inisialisasi project lalu run fungsi `main`

### BUILD & RUN
1. Bersihkan folder target, dengan perintah,

        mvn clean

2. Build file distribusi `.jar`, dengan perintah,

        mvn clean package assembly:single

3. Lalu jalankan file distribusi dengan perintah,

        java -jar .\target\if3110-moi-soap-service-1.0-SNAPSHOT-jar-with-dependencies.jar
    
4. Buka url yang telah dipublish


### RUN DOCKER
1. Build image docker

        docker build -t if3110-soap-service-1:latest .

2. Jalankan docker compose
        
        docker compose up 
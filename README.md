# Deskripsi Singkat Web Service

# Skema Basis Data
<img width="608" alt="image" src="https://github.com/IF3110-2023-02-17/moi-soap-service/assets/73476678/7b12b58a-a300-41a5-86cc-cba0187d345e">

# Endpoint API
- subscribe
- getSubscriptionByStatusSubscriber
- getSubscirptionByStatusStudio
- checkStatus

# Pembagian Tugas
- Fungsi Subscribe : 13521109
- Fungsi Accept Subscribe : 13521171
- Fungsi Reject Subscribe : 13521109
- Read By Subscriber or Studio With Status : 13521109
- Read By Subscriber or Studio : 13521067
- Revalidate Status Subscribe : 13521067
- Callback : 13521109

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

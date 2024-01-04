# GreenGame

## Pierwsze uruchomienie

Przy pierwszym uruchomieniu należy wykonać polecenie `docker-compose up -d` oraz uruchomić aplikację zmieniając profil na `setup`:
![image](https://gist.github.com/assets/77862767/339e60f3-4407-40ee-9ed6-6f8b07223742)

Po pomyślnej zmianie profilu na `setup`, podczas uruchomienia aplikacji w logach powinien być widoczny wpis potwierdzający pomyślną zmianę profilu: 
```
2024-01-04T23:25:49.727+01:00  INFO 2816 --- [  restartedMain] com.example.demoio.DemoIoApplication     : The following 1 profile is active: "setup"
```

Po uruchomieniu aplikacja będzie dostępna pod adresem: http://localhost:8080/

> Po pierwszym pomyślnym uruchomieniu aplikacji należy wyłączyć profil `setup`.

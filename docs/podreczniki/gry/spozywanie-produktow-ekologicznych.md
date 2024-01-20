# Spożywanie produktów ekologicznych
## Opis gry:
Nasza gra ma na celu przekonanie dzieci i młodzieży do sięgania po ekologiczne produkty i dbania o swoje zdrowie. Gra jest zrobiona w stylu podobnym do wielu gier zręcznościowych, gdzie po określonym czasie ekran zmienia kolor i trzeba go jak najszybciej kliknąć. W naszej grze zaimplementowane jest w trochę inny sposób. Na początku, użytkownik musi wybrać jeden z trzech dostępnych poziomów (żeby odblokować poziom 2 i 3 trzeba przejść wszystkie poprzednie poziomy). W zależności od poziomu po upływie od 2 do (10, 7, 4) sekund nasz ekran z napisem „Wait for the background to split, then choose the healthy product by clicking LMB!” (cała instrukcja jak grać w grę) zamienia się na ekran podzielony na (3, 4, 8) prostokątów. Tylko w jednym z wyświetlonych prostokątów znajduje się obrazek ze zdrowym produktem. Użytkownik za kliknięcie go bazowo otrzymuje 100 punktów, a w zależności od tego jak szybko go kliknął (od 0 do 2000 milisekund) dostaje więcej punktów ((1500, 1000, 500) spadając stopniowo o (150, 100, 50) co 200 milisekund w zależności od poziomu). W przypadku kliknięcia na produkt niezdrowy, użytkownik nie otrzymuje żadnych punktów. Po rozegraniu siedmiu takich rund wyświetla się komunikat z wynikami użytkownika (najlepszy, najgorszy, średni i całkowity wynik). Na tym komunikacie znajduje się przycisk „ok”, po którego kliknięciu uzyskany wynik jest zapisywany w bazie danych, a okno gry zostaje przeładowane. Kolejny poziom zostaje wtedy odblokowany i użytkownik może jeszcze raz rozpocząć rozgrywkę.
## Przebieg rozgrywki:
### Wybór poziomu:
![Wybór poziomu](https://i.imgur.com/sMdDY7T.png){ width="900" }
### Poziom odblokowany (czarny tekst):
![Poziom odblokowany](https://i.imgur.com/NnhmX14.png){ width="900" }
### Poziom zablokowany (szary tekst):
![Poziom zablokowany](https://i.imgur.com/vYSRX4X.png){ width="900" }
### Wybranie zablokowanego poziomu:
![Wybranie zablokowanego](https://i.imgur.com/AYaMdQR.png){ width="600" }
### Ekran oczekiwania na pojawienie się produktów wraz z prostokątami punktów (po prawej):
![Ekran oczekiwania](https://i.imgur.com/VP0fVqZ.png){ width="900" }
### Ekran po pojawieniu się produktów (poziom 1):
![Ekran po pojawieniu się produktów 1](https://i.imgur.com/khwoguW.png){ width="900" }
### Ekran po pojawieniu się produktów (poziom 2):
![Ekran po pojawieniu się produktów 2](https://i.imgur.com/jB1ByIf.png){ width="900" }
### Ekran po pojawieniu się produktów (poziom 3):
![Ekran po pojawieniu się produktów 3](https://i.imgur.com/wtGJyU6.png){ width="900" }
### Wybranie poprawnego produktu:
![Poprawny](https://i.imgur.com/1kuiG1N.png){ width="900" }
### Wybranie niepoprawnego produktu:
![Niepoprawny](https://i.imgur.com/r3RH3Jt.png){ width="900" }
### Koniec gry:
![Koniec](https://i.imgur.com/1yHKh3P.png){ width="600" }

# Oszczędzanie wody
Autorzy: Michał Korblit, Szymon Pietrzak
## Cel
 Gra ma na celu uświadomienie młodych odbiorców na temat znaczenia oszczędzania wody i zachęcenie ich do praktykowania tego nawyku w codziennym życiu.

### Założenia
- Dostęp do gry realizowany jest dzięki stronie internetowej (tylko dla zalogowanych użytkowników).
- Dostęp do gry mają tylko użytkownicy zalogowani, dla których ta gra została odblokowana.
- Postęp użytkownika jest wysyłany do modułu rankingu po pozytywnym zakończeniu gry.
- Użytkownik musi przejść 3 plansze, na których jego zadaniem będzie zapobieganie wylewającej się wodzie z rur poprzez zakręcanie zaworów w taki sposób aby jak najefektywniej pokonać każdy z poziomów.
- Efektywność liczona jest za pomocą czasu, który ma wpływ na punkty końcowe.
- Punkty są liczone w następujący sposób:
Jeśli gracz przejdzie grę w więcej niż 500 sekund otrzymuje 500 punktów czyli minimalną ilość punktów jaką można zdobyć poprzez przejście gry.
Jeśli gracz przejdzie grę w mniej niż 60 sekund (co powinno być niewykonalne) otrzymuje 2000 punktów czyli maksymalną ilość punktów jaką można zdobyć w naszej grze.
Jeśli gracz przejdzie grę w więcej niż 60 sekund ale mniej niż 500 sekund jego punkty naliczane są wzorem: punkty = max_punktów - czas_rozgrywki + 60, gdzie max_punktów = 2000, a czas_rozgrywki liczony jest w sekundach
- Gra kończy się porażką w przypadku wpadnięcia do dziury na którymkolwiek z trzech możliwych poziomów.

## Wymagania
### Funkcjonalne
- Gra ma interfejs graficzny użytkownika, na którym możemy wybrać przycisk "Play" - rozpoczęcie gry lub "Controls" - wyświetlenie informacji o sterowaniu w grze.
- Po rozpoczęciu gry najpierw wyświetlany jest pierwszy poziom pozwalający graczowi zapoznać się z mechaniką gry, poprzez ułatwienia w postaci strzałek wskazujących na to co ma zrobić. 
- Podczas rozgrywki mierzony jest czas.
- Czas mierzony jest nieustannie podczas przechodzenia poziomów. 
- Czas zatrzymywany jest dopiero po ukończeniu wszystkich poziomów lub po przegraniu.
- Im szybciej użytkownik pokona wszystkie poziomy tym więcej punktów zdobędzie.
- Podczas przemieszczania się po planszy gracz może wpaść w dziurę w ziemi, przez co automatycznie przegrywa.
- Na każdej z plansz znajdują się rury, rury z zaworami, strumienie wody, kałuże, wejście, wyjście oraz dziury.
- Gracz nie może przechodzić przez rury oraz rury z zaworami. 
- Gracz może przemieszczać się po podłodze, strumieniach wody, kałużach, wejściu oraz wyjściu.
- Po załadowaniu poziomu gracz pojawia się w miejscu oznaczonym wejściem.
- Przejść do następnego poziomu można tylko wtedy gdy na danym poziomie nie będzie wylewać się woda - ikona wyjścia zostanie podświetlona.
- Aby zakręcić zawór należy podejść do niego i wcisnąć przycisk kierujący gracza w stronę zaworu.
- Po przegraniu wyświetlany jest ekran informujący o przegranej.
- Po wygranej wyświetlany jest ekran informujący o wygranej oraz czas w jakim gracz przeszedł grę.
- Gracz może bez strat rozpocząć grę od początku po wyświetleniu ekranu wygranej lub przegranej.
- Po zakończeniu gry zwraca czas rozgrywki danego użytkownika oraz ilość zdobytych punktów
### Niefunkcjonalne
- Gra ma 3 poziomy
- Drugi oraz trzeci poziom są bez podpowiedzi i są trudniejsze od pierwszego.
- Każda z planszy przedstawia kolejny poziom pomieszczeń, w których znajdują się połączone ze sobą rury wodociągowe. 
- Z rur w niektórych miejscach wylewa się z woda. 
- Aby zapobiec wylewającej się wodzie należy znaleźć odpowiednie zawory na odpowiednich rurach i je zakręcić. 
- Należy zwracać uwagę na to, które zawory trzeba zakręcać, a które nie aby zaoszczędzić czas i wodę dzięki czemu gracz może otrzymać więcej punktów.
- Przejście do następnego poziomu oznaczone jest ikoną schodów. 

## Zmiany wymagań
- Po wygraniu lub przegraniu użytkownik może bez straty swoich monet zacząć grę od początku.

## Diagramy
## Diagram przypadków użycia
Informacja o uruchomieniu gry:
![Przypadki użycia1](https://i.imgur.com/43hJvee.png){ width="900" }
Informacja o grze:
![Przypadki użycia2](https://i.imgur.com/xlnEayC.png){ width="900" }
## Powiązania z innymi modułami

![Inne moduły1](https://i.imgur.com/NAeD1Ss.png){ width="900" }
## Diagram klas
![Klasy](https://i.imgur.com/MAwaVcY.png){ width="900" }
## Scenariusz przypadku użycia: Załadowanie kolejnego poziomu
![Scenariusz następny poziom](https://i.imgur.com/xp9MFqc.png){ width="900" }
## Diagram sekwencji: Załadowanie kolejnego poziomu
![Sekwencji następny poziom](https://i.imgur.com/J7PsgO2.png){ width="900" }
## Scenariusz przypadku użycia: Wpadnięcie do dziury
![Scenariusz Wpadnięcie do dziury](https://i.imgur.com/GBuuBDg.png){ width="900" }
## Diagram sekwencji: Wpadnięcie do dziury
![Sekwencji Wpadnięcie do dziury](https://i.imgur.com/yqtcw6X.png){ width="900" }

## Diagram maszyny stanowej
![Maszyna stanowa](https://i.imgur.com/PQumPGj.png){ width="900" }
## Diagram czynności
![Czynności](https://i.imgur.com/JkxrIIz.png){ width="900" }
## Diagram pakietów
![Pakiety](https://i.imgur.com/sfobDYn.png){ width="900" }
## Diagram przeglądu interakcji
![Przegląd interakcji](https://i.imgur.com/pdX5u9a.png){ width="900" }
## Diagram strukturalny
![Struktura](https://i.imgur.com/3XPNurN.png){ width="900" }
## Diagram harmonogramowania
![Harmonogramowanie](https://i.imgur.com/9Eb2QbT.png){ width="900" }

## Realizacja założeń i wymagań
Zaprojektowana przez nasz zespół gra spełnia postawione przez architektów założenia i wymagania, co można zaobserwować na utworzonych wyżej diagramach. Dostęp do gry jest realizowany przez stronę internetową, dane użytkowników będą przechowywane w bazie danych, po zakończeniu gry wynik zostanie przesłany do bazy danych. Gracz jest nagradzany za skończenie gry ilością punktów adekwatną do czasu ukończenia wszystkich trzech plansz, których przelicznik został podany powyżej. W grę można ponownie zagrać w celu poprawy wyniku. Gra posiada 3 poziomy, z których każdy kolejny jest coraz trudniejszy oraz są ładowane jeden po drugim. Na końcu gry gracz jest informowany o swoim wyniku.

## Realizacja powiązań z innymi modułami
Projekt jest powiązany z innymi modułami. W module rankingu przechowywane są wyniki danego gracza, a jego najlepszy wynik wyświetlany jest w module rankingu. W przypadku poprawienia wyniku i przesłania go do bazy danych, poprawiony wynik jest wyświetlany w module rankingu. Dzięki modułowi aplikacji gracz ma dostęp do danej gry, a z jej poziomu może wyjść do interfejsu aplikacji. Postęp gracza jest zapisywany w module przechowywania danych.


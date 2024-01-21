# Prawidłowe pozbywanie się odpadów

## Opis gry
Celem gry jest zebranie jak największej liczby [odpadów](#odpad), rozmieszczonych na [mapie](#mapa) i zaniesienie ich do odpowiednich [kontenerów](#kontener), używając możliwie jak najmniejszej liczby [ruchów](#tury). Końcowy wynik rozgrywki, oceniany jest na podstawie uzyskanych [punktów](#punktacja).

*Wygląd gry*

![cała_gra](https://gist.github.com/assets/78324970/b0d107e0-ea33-481d-8d2e-d85fee0d5b07)

## Mapa
Mapa rozgrywki zbudowana jest z pól. Jest ona rozmiaru 51x51 pól. Na każdym polu mogą się znajdować poniższe elementy:

*Mapa rozgrywki*

![mapa](https://gist.github.com/assets/78324970/46356d40-6fdc-422b-a695-2e9df7a94f8c)


### Gracz
- może wejść zarówno na pole z kontenerem, odpadem jak i na puste pole
- oznaczony jest kolorem białym

*Wygląd gracza*

![gracz](https://gist.github.com/assets/78324970/138775e5-e629-47d2-8282-9e9c79fe6474)
 
 *Wygląd pustego pola*
 
![puste](https://gist.github.com/assets/78324970/6f49bb8b-baf4-46fb-ba00-9db1c10ef1b5)
 
### Odpad
- generowane są przy rozpoczęciu rozgrywki na różnych, losowych polach, na których nie ma kontenera ani gracza
- ich pozycja może ulec zmianie, tylko pod wpływem akcji gracza
- nie może być kilka odpadów na tym samym polu, zarówno po wygenerowaniu jak i przez akcję gracza
- są różne [rodzaje odpadów](#rodzaje-odpadów)

### Kontener
- generowane są przy rozpoczęciu rozgrywki na różnych, określonych polach - przy każdej rozgrywce ich pozycja się nie zmieni i znajdować się będą w obrębie 9 centralnych pól
- ich pozycja jest stała, a więc nie może ulec zmianie po wygenerowaniu
- nie wygeneruje się kilka kontenerów na jednym polu
- są różne rodzaje kontenerów, odpowiadające odpowiednim [rodzajom odpadów](#rodzaje-odpadów)

Na jednym polu mogą się więc znaleźć w tym samym czasie wyłącznie gracz z odpadem lub kontenerem.

## Rodzaje odpadów
Istnieją różne rodzaje odpadów, które przypisane są do odpowiednich kontenerów. Aby [uzyskać punkty](#punktacja-upuszczenia-odpadu) należy [upuścić](#upuszczanie-odpadu) odpad danego rodzaju, będąc na polu na którym znajduje się kontener tego samego rodzaju.

Rodzaje odpadów i ich kolory:
| Rodzaj odpadów | Kolor |
|-|-|
| Papier | Niebieski |
| Plastik | Żółty |
| Szkło| Zielony|
| Pozostałości po owocach | Brązowy |
| Zabrudzony papier | Ciemno-szary|
| Zużyty sprzęt medyczny | Ciemno-szary |
| Puszki sprayu | Ciemno-szary |
| Odchody zwierząt | Ciemno-szary |
| Odpady elektroniczne | Fioletowy |

*Wygląd odpowiednich odpadów*

![odpady](https://gist.github.com/assets/78324970/733921cf-7c15-4631-9d22-554dc0adea03)

Kolory kontenerów odpowiadają kolorom odpadów, ale mają one jaśniejszy odcień:

*Wygląd obszaru kontenerów*

![kontenery](https://gist.github.com/assets/78324970/ac2ef550-92aa-481f-9451-c09ab2c877fe)

## Sterowanie
Gracz posiada kilka możliwych akcji do podjęcia podczas rozgrywki:

*Obszar sterowania - oznaczony niebieskim polem*

![oznaczenie_obszaru_sterowania](https://gist.github.com/assets/78324970/32805845-e7f8-45fc-8e33-5631943d0cfc)

### Poruszanie się
Możliwość poruszania się w 9 kierunkach na planszy. Na poruszanie pozwala 9 przycisków widocznych w interfejsie użytkownika, które odpowiadają za poszczególny kierunek.

*Obszar przycisków poruszania się - oznaczone niebieskim polem*

![oznaczenie_sterowania](https://gist.github.com/assets/78324970/704c301f-ad5f-4c80-b4b6-4d17a7906285)

Po przeprowadzonym ruchu, odejmowany jest punkt ruchu z pozostałych, dostępnych w danej [turze](#tury). Aby zwiększyć liczbę punktów ruchów, gracz może tylko [zakończyć aktualną turę](#zakończenie-tury), co przywróci mu maksymalną liczbę punktów.

Jeżeli gracz wejdzie na pole, na którym znajduje się odpad i ma wolne miejsce w ekwipunku, odpad z danego pola zostanie automatycznie przeniesiony na jedno z wolnych miejsc ekwipunku. Jeżeli ekwipunek jest zapełniony, to odpad pozostanie na polu.

*Obszar ekwipunku - oznaczony niebieskim polem*

![oznaczenie_ekwipunku](https://gist.github.com/assets/78324970/73e3c924-8784-4ce0-b037-67de1ec8b9f4)

### Upuszczanie odpadu
Gracz ma możliwość upuszczenia wybranego odpadu z ekwipunku, na akutalnie znajdujące się pole, za pomocą przycisku znajdującego się pod aktualnie przechowywanym odpadem.

*Przyciski upuszczania odpadu - oznaczone niebieskim polem*

![oznaczenie_upuszczenia](https://gist.github.com/assets/78324970/bb2eaf5a-08f5-4e31-aa8d-fbf893a50cac)

Gracz może upuścić odpad, pod warunkiem że na polu nie znajduje się już inny odpad.
Po upuszczeniu odpadu następuje sprawdzanie [wyniku upuszczenia](#punktacja-upuszczenia-odpadu), na podstawie którego są przyznawane lub odbierane punkty.

## Tury
Na początku rozgrywki gracz ma do wykorzystania 40 tur, z czego na każdą turę przypada 300 punktów ruchu.

### Zakończenie tury
Gracz może zakończyć turę, co zmniejszy mu liczbę pozostałych tur o 1 i przywróci mu liczbę punktów ruchu do 300.

*Przycisk kończenia tury - oznaczony niebieskim polem*

![oznaczenie_następnejTury](https://gist.github.com/assets/78324970/308b5aa1-55c2-4bca-9db0-ce6700ce38d4)

### Zakończenie rozgrywki
Po wykorzystaniu wszystkich tur gra nie zostanie całkowicie zakończona. Aby zakończyć całkowicie rozgrywkę, należy ręcznie użyć przycisku zakończenia rozgrywki. Gracz może jednak zdecydować się na wcześniejsze zakończenie rozgrywki, na skutek czego nastąpi [przyznanie mu punktów](#punktacja-zakończenia-gry-z-dodatnią-liczbą-pozostałych-tur), zgodnie z liczbą pozostałych tur i punktów ruchów w danej turze.

*Przycisk kończenia rozgrywki- oznaczony niebieskim polem*

![oznaczenie_końcaGry](https://gist.github.com/assets/78324970/22d5cb96-a588-4f48-b6c2-e4b8d9a184a1)

Po użyciu przycisku zakończenia tury, niemożliwe jest przeprowadzanie jakichkolwiek akcji.

## Punktacja
Istnieją dwie możliwości zmiany liczby punktów przez gracza:

### Punktacja upuszczenia odpadu
W zależności od miejsca upuszczenia odpadu, gracz może uzyskać lub utracić punkty:

- uzyskanie punktów 
>- upuszczenie odpadu na pole z odpowiednim kontenerem = +10pkt
- utracenie punktów
>- upuszczenie odpadu na puste pole = -2pkt
>- upuszczenie odpadu na pole z nieodpowiednim kontenerem = -5pkt

### Punktacja zakończenia gry z dodatnią liczbą pozostałych tur
Po wciśnięciu przycisku **zakończenia gry**, zostaną dodane dodatkowe punkty graczowi, obliczone według wzoru:

$((T * 300) + P)/100$

T - liczba pozostałych tur
P - liczba pozostałych punktów ruchu w turze
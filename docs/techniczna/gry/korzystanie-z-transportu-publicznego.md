# Korzystanie z transportu publicznego
Autorzy: Robert Łaski, Kacper Pietrzak
## Cel i założenia
### Cel
Inspirowanie ludzi do korzystania z transportu publicznego poprzez ciekawą rozgrywkę, jednocześnie podnosząc świadomość ekologiczną i wspierając bardziej zrównoważone środki transportu.
### Założenia
- Gra skupia się na przewożeniu pasażerów tramwajem oraz unikaniu przeszkód torze.
- Gra składa się z 3 poziomów trudności, z czego każdy kolejny będzie posiadał jeden tor więcej, a więc będzie trudniejszy.
- Będzie to gra zręcznościowa, w której będziemy sterować strzałkami.
- Dostęp do gry jest realizowany przez stronę internetową.
- System śledzi postęp w grze.
- Gracz uzyskuje punkty za każdym razem, ale żeby odblokować nowy poziom musi zdobyć odpowiednią liczbę punktów w jednym podejściu.
#### Co zostało zmienione?
- Usunięto wymaganie zmieszczenia się w określonym czasie.
## Wymagania
### Funkcjonalne
- Można wybrać poziom gry.
- Gracz może sterować tramwajem, przeskakując między torami z użyciem WSAD.
- Na przystankach gracz może wysadzać lub zbierać pasażerów.
- Gracz musi unikać przeszkód i zbierać pasażerów, zwiększając swoją punktację.
- Tramwaj ma określoną pojemność, po jej osiągnięciu gracz musi dostarczyć pasażerów na przystanek, aby zwolnić miejsce na nowych.
- Gracz ma możliwość poprawiania swojego wyniku na danym poziomie.
- Po przegranej gracz ma możliwość rozpoczęcia danego poziomu od nowa.
- Wyniki gracza są zapisywane w bazie danych po ukończeniu poziomu.
- Dostępny jest krótki poradnik do gry, wyjaśniający zasady, sterowanie i cel gry.
- Gracz musi zdobyć odpowiednią liczbę punktów na torze, aby odblokować nowy poziom.
#### Zmiany wymagań
- Zmieniono sterowanie ze strzałek na WSAD.
- Usunięto rozróżnienie przystanków, aktualnie istnieje jeden rodzaj przystanku, na którym można wysadzać i zbierać pasażerów.
### Niefunkcjonalne
- Czytelny interfejs użytkownika.
- Gra powinna być dostępna i intuicyjna dla graczy z różnym stopniem doświadczenia.
- Dostęp do gry ma tylko użytkownik zalogowany.
- Istnieją trzy poziomy gry, z każdym kolejnym wprowadzającym nowe wyzwania.
#### Zmiany wymagań
- Usunięto wymaganie posiadania odpowiedniej liczby monet, aby rozpocząć poziom od nowa.
## Diagramy przypadków użycia
Diagram przedstawiający akcje, które są możliwe do wykonania.  


![Diagram przypadków użycia](https://i.imgur.com/6UfwHXp.png)
## Diagramy klas
Diagram przedstawiający klasy, funckje i zmienne użyte do stworzenia modułu.


![Diagram klas](https://i.imgur.com/xJWrhZm.png)  
## Diagramy interakcji i scenariusze
### Przypadek użycia - `dodanie pasażerów do tramwaju`
Diagram przedstawiający metody użyte do dodania pasażerów do tramwaju.   


![Diagram interakcji](https://i.imgur.com/LXptvTd.png)  
![Tabela z przypadkiem użycia dodaj pasażerów do tramwaju](https://i.imgur.com/nmzkp4g.png)  
### Przypadek użycia - `rozpoczęcie rozgrywki`
Diagram przedstawiający metody użyte do rozpoczęcia rozgrywki.    


![Diagram interakcji](https://i.imgur.com/43erGEM.png)  
![Tabela z przypadkiem użycia rozpoczęcie rozgrywki](https://i.imgur.com/QMyuRXC.png)  
## Diagram czynności
Diagram w uproszczony sposób pokazuje jakie czynności może podjąć gracz.    


![Diagram czynności](https://i.imgur.com/7wb530u.png)  
## Diagram maszyny stanowej
Diagram w uproszczony sposób pokazuje jakie stany może przyjąć tramwaj.    


![Diagram maszyny stanowej](https://i.imgur.com/YHGZxuN.png)  
## Diagram komponentów
Diagram opisuje relacje między komponentami systemu.    


![Diagram komponentów](https://i.imgur.com/PVHiei7.png)  
## Diagram pakietów
Diagram opisuje uproszczone zgrupowanie funkcji i klas w pakiety, oraz które z powstałych w ten sposób grup ze sobą współpracują.    


![Diagram pakietów](https://i.imgur.com/Qlh0qn4.png)  
## Diagram przeglądu interakcji
Diagram opisuje interakcję podczas dodwania pasażerów do tramwaju.    


![Diagram przeglądu interakcji](https://i.imgur.com/UoWYOcp.png)  
## Diagram strukturalny
Diagram opisuje relacje klas. Klasą centralną jest klasa "Game". Jest ona bezpośrednio powiązana z klasami: 'Tram', 'TramStop', 'Obstacle'.    


![Diagram strukturalny](https://i.imgur.com/dFdYIwZ.png)  
## Diagram harmonogramowania
Diagram harmonogramowania przedstawia stany gry w czasie.    


![Diagram harmonogramowania](https://i.imgur.com/1jWAauQ.png)  
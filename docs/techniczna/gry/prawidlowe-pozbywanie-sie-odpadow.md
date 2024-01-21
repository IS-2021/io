# Prawidłowe pozbywanie się odpadów

Autorzy: Wojciech Tomicki, Paweł Saturczak

## Diagram przypadków użycia
![Diagram_przypadkow_uzycia](https://gist.github.com/assets/78324970/d46e763b-ec8b-46fc-8266-08d59c4a8a70)

Główną rolę użytkową modułu sprawuje użytkownik, do którego przypisana jest większość przypadków użycia. Przypadek użycia związany z jedynym pozostałem aktorem - modułem rankingu, jest dodatkowo związany bezpośrednio z akcją **Zakończenia rozgrywki**, przeprowadzanej przez użytkownika.
### Zmiany w porównaniu z poprzednią wersją
Przypadki użycia związane z użytkownikiem zostały zmniejszone do 5 widocznych, które reprezentują wcześniej obecny przypadek użycia **Działanie rozgrywki**. Pozostałe przypadki ostatecznie nie są obecne w projekcie.
Nieobecny jest aktor **Modułu przechowywania danych**.

## Diagram klas

![Klas](https://gist.github.com/assets/78324970/7dd159e5-ce5d-4be4-982a-f986823019e1)

Widok klas przede wszystkim odzwierciedla elementy modułu, tworzące grę takie jak **Gra**, będącą klasą wokół której zorganizowane są elementy składowe gry, między innymi - **Mapa**, **Bin** (kontener), **Rubbish** (odpad), **Player** (gracz).
### Zmiany w porównaniu z poprzednią wersją
Nastąpiło znaczne zwiększenie liczby metod i atrybutów do obecnych wcześniej klas, na skutek napotkanych potrzeb programistycznych.
Utworzono struktury, potrzebne do przechowywania danych na poziomie modułu, oraz przekazywania informacji pozycyjnych z całego systemu do funkcji **Game.print()**, co umożliwia ukazywanie aktualnych informacji dotyczących liczby tur, punktów ruchu oraz zdobytych punktów graczowi. Strukturami tymi są - **PositionInfo**, **Data**, **PlayerData**, **BackpackData**.
Usunięto moduł przechowania danych.

## Diagramy sekwencji i scenariusze
*Podnoszenie odpadu*

![Interakcji_Podnoszenie_odpadu](https://gist.github.com/assets/78324970/f1d8fc2d-96e7-4469-abbf-0fe1990a6268)

Przedstawiona została mechanika automatycznego podnoszenia odpadu z pola, na którym aktualnie znajduje się użytkownik.
### Zmiany w porównaniu z poprzednią wersją
Diagram został wydzielony od ogólnego diagramu interakcji **Przebiegu rozgrywki**.
Zmniejszona została liczba obiektów, biorących udział w interakcji.
## Scenariusz
| Nazwa | Podnoszenie odpadu |
|-|-|
| Twórcy | Paweł Saturczak, Wojciech Tomicki |
| Poziom ważności: | Średni |
| Typ przypadku użycia: | Ogólny Istotny |
| Aktorzy: | Użytkownik |
| Krótki opis: | Podnoszenie odpadu z mapy do plecaka użytkownika |
| Waunki wstępne: | Gracz wszedł na pole na którym znajduje się odpad |
| Warunki końcowe: | Brak |
| Główny przepływ zdarzeń:  | <ol><li>Usunięcie odpadu z mapy</li><li>Utworzenie odpadu w plecaku</li></ol> |
| Alternatywny przebieg zdarzeń: | <ul><li>1a. Brak akcji</li><li>2a. Brak akcji</li></ul> |

<br />
<br />

*Poruszanie*

![Interakcji_Poruszanie](https://gist.github.com/assets/78324970/94479138-20f3-46fa-9676-d8bde8eea8a8)

Przedstawiona została mechanika poruszania się gracza, wraz z przedstawianiem graczowi wyników akcji.
### Zmiany w porównaniu z poprzednią wersją
Diagram został wydzielony od ogólnego diagramu interakcji **Przebiegu rozgrywki**, gdzie wcześniej był uwzględniony wraz z machaniką zbierania odpadu.
Zmniejszona została liczba obiektów, biorących udział w interakcji.
## Scenariusz
| Nazwa | Poruszanie |
|-|-|
| Twórcy | Paweł Saturczak, Wojciech Tomicki |
| Poziom ważności: | Średni |
| Typ przypadku użycia: | Ogólny Istotny |
| Aktorzy: | Użytkownik |
| Krótki opis: | Poruszenie postacią na mapie |
| Waunki wstępne: | Gracz nacisnął jeden z przycisków odpowiedzialnych za poruszenie |
| Warunki końcowe: | Brak |
| Główny przepływ zdarzeń:  | <ol><li>Zostaje ustalony kierunek w którym gracz się poruszy</li><li>Zostaje zmieniona pozycja gracza</li><li>Statystyki gry i mapa zostają zaktualizowane</li></ol> |
| Alternatywny przebieg zdarzeń: | <ul><li>2a. Pozycja gracza nie zostaje zmieniona, bo gracz nie ma dostępnych punktów rchu / próbuje wyjść poza obszar mapy</ul></li> |


## Diagramy czynności

### Podniesienie odpadu

![Czynnosci_-_Podniesienie_odpadu](https://gist.github.com/assets/78324970/24bfbbae-e86b-4c22-a319-aea8e2ab90f0)

Przedstawione zostały działania przeprowadzane przy automatycznym podnoszeniu odpadu. Do działań przypisane zostały funkcje z [diagramu klas](#diagram-klas).
#### Zmiany w porównaniu z poprzednią wersją
Diagram został wydzielony od ogólnego diagramu interakcji **Działania rozgrywki**, gdzie wcześniej był zwiększony o precyzyjniejszy opis warunków, który został zastąpiony przez ogólniejszy warunek, uwzględniający dwa elementy - obecność odpadu i miejsce w ekwipunku.

<br />
<br />

### Poruszanie

![Czynnosci_-_Poruszanie](https://gist.github.com/assets/78324970/a5e64706-16d8-43d3-9125-a11c70ca11e7)

Przedstawione zostały działania przeprowadzane przy poruszaniu się gracza.
#### Zmiany w porównaniu z poprzednią wersją
Diagram został wydzielony od ogólnego diagramu interakcji **Działania rozgrywki**. Dodatkowo została zmniejszona postać tej części diagramu, przez dodanie warunku sprawdzania kierunku poruszania się i zmiany postaci sprawdzania liczby pozostałych punktów ruchu z pojedynczej czynności do bloku decyzyjnego.

<br />
<br />

### Upuszczenie odpadu

![Czynnosci_-_Upuszczenie_odpadu](https://gist.github.com/assets/78324970/a3ab9346-4e00-4cbb-8ab6-8d80b7e7216d)

Przedstawione zostały działania przeprowadzane przy upuszczaniu odpadu przez gracza. Do większości działań przypisane zostały funkcje z [diagramu klas](#diagram-klas).
#### Zmiany w porównaniu z poprzednią wersją
Diagram został wydzielony częściowo od ogólnego diagramu interakcji **Działania rozgrywki**.
Analogiczne warunki obecne w poprzedniej wersji, zostały przedstawione przez bloki warunkowe, zamiast czynności, po których następowało sprawdzanie warunków.
Dodane zostały precyzyjne opisy wartości zmienianych punktów przy odpowiednich warunkach.

<br />
<br />

### Zakończenie rozgrywki

![Czynnosci_-_Zakonczenie_rozgrywki](https://gist.github.com/assets/78324970/1dc7a222-940b-4341-9b5f-b3d5d6336f6e)

Przedstawione zostały działania przeprowadzane przy zakończeniu rozgrywki przez gracza. Do jednego z działań przypisana została funkcja z [diagramu klas](#diagram-klas).
#### Zmiany w porównaniu z poprzednią wersją
Element ten nie był opisany w poprzednich wersjach, a więc jest elementem dodanym.

<br />
<br />

### Zakończenie tury

![Czynnosci_-_Zakonczenie_tury](https://gist.github.com/assets/78324970/1fa97fc0-0191-48b1-b0c3-dfe3568df462)

Przedstawione zostały działania przeprowadzane przy zakończeniu tury przez gracza. Do jednego z działań przypisana została funkcja z [diagramu klas](#diagram-klas).
### Zmiany w porównaniu z poprzednią wersją
Diagram został wydzielony częściowo od ogólnego diagramu interakcji **Działania rozgrywki**.
Opis działania jest precyzyjniejszy, przez dodatkowy opis odjęcia tury od pozostałej liczby tur.
Analogiczne warunki obecne w poprzedniej wersji, zostały przedstawione przez bloki warunkowe, zamiast czynności.

## Diagram maszyny stanowej

![Maszyny_stanowej](https://gist.github.com/assets/78324970/b66df270-83d8-4814-af48-93de55c1e633)

Widoczne są stany, opisujące ogólne procesy mające miejsce podczas rozgrywki. Zaobserwować można podział modułu na 3 główne etapy - przygotowania, wykonywania poleceń gracza i zakończenia.
### Zmiany w porównaniu z poprzednią wersją
Liczba stanów została znacznie zmniejszona, przez zmniejszenie liczby procesów jakie zachodzą w module. Jest to także skutek uogólnienia stanów modułu.
Aktualizacja wyników zachodzi bezpośrednio przy zakończeniu rozgrywki.

## Diagram komponentów

![Modulow](https://gist.github.com/assets/78324970/3f30b22d-000d-4882-ba30-a84d8c844b9b)

Widoczne są moduły z którymi opisywany moduł gry jest połączony, aby był spójnym elementem całego projektu.
### Zmiany w porównaniu z poprzednią wersją
Usunięty został moduł **Przechowywania danych**.

## Diagram pakietów

![Pakietów](https://gist.github.com/assets/78324970/c59f8757-5159-43bf-a709-0a0fe20797ca)

Całość modułu zamyka się w zakresie pojedynczego pakietu, którego elementami są klasy określone w [diagramie klas](#diagram-klas).
### Zmiany w porównaniu z poprzednią wersją
Diagram ten nie był obecny w poprzednich wersjach, a więc jest nowym, dodanym diagramem.

## Diagram strukturalny

![Strukturalny](https://gist.github.com/assets/78324970/bfdb514b-326b-49a1-9ea0-606312ae28bb)

Przedstawione zostały ogólniejszy widok [diagramu klas](#diagram-klas), pozwalający wyraźniej zaobserwować strukturę projektu.
### Zmiany w porównaniu z poprzednią wersją
Diagram ten nie był obecny w poprzednich wersjach, a więc jest nowym, dodanym diagramem.

## Diagram harmonogramowania

![Harmonogamowania_-_poruszanie](https://gist.github.com/assets/78324970/998e9f0f-a18e-4b18-a82f-151df7fcb998)


Widoczny jest czas potrzebny do realizacji sekwencji **Poruszania** się, która odbywa się w stanie **Przetwarzania akcji**.
### Zmiany w porównaniu z poprzednią wersją
Diagram ten nie był obecny w poprzednich wersjach, a więc jest nowym, dodanym diagramem.
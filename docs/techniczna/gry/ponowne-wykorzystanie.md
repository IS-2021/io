# Ponowne wykorzystanie

Autorzy: Dominik Kwiecień, Hubert Pietras

## Diagramy przypadków użycia
![image](https://gist.github.com/assets/126475707/dfa78445-4e51-4930-a1c9-dc010fcfee7e)
![image](https://gist.github.com/assets/126475707/a7913f69-248a-452b-a1c5-36af5bc569d5)
![image](https://gist.github.com/assets/126475707/5d9fb9ac-e930-4280-bc37-ae956506e2de)
![image](https://gist.github.com/assets/126475707/27c0e1db-cd08-41a1-93a1-efdac456f6df)

Diagramy przdstawiają przypadki użycia modułu z podziałem na różnych aktorów
## Diagram klas
![image](https://gist.github.com/assets/126475707/6fd393da-9c6b-40ba-8054-14b9d43cb427)

Diagram przedstawia klasy wchodzące w skład modułu
## Diagramy interakcji i scenariusze
### Przypadek użycia - Zakup przedmiotu

| Nazwa                         | Zakup przedmiotu                                                                                                                                                                                                                                                                                                                             |
|-------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Autor                         | Gracz                                                                                                                                                                                                                                                                                                                                        |
| Twórcy                        | Dominik Kwiecień i Hubert Pietras - projektanci                                                                                                                                                                                                                                                                                              |
| Poziom ważności               | Wysoki                                                                                                                                                                                                                                                                                                                                       |
| Typ przypadku użycia          | Szczegółowy                                                                                                                                                                                                                                                                                                                                  |
| Aktorzy                       | Gracz                                                                                                                                                                                                                                                                                                                                        |
| Krótki opis                   | Gracz wybiera i stawia rośliny na planszy                                                                                                                                                                                                                                                                                                    |
| Warunki wstępne               | Gracz posiada posiada wystarczającą ilość kompostu                                                                                                                                                                                                                                                                                           |
| Warunki końcowe               | Przedmiot jest ustawiony na planszy                                                                                                                                                                                                                                                                                                          |
| Główny przepływ zdarzeń       | 1. Gracz wybiera roślinę ze sklepu<br>2. Aktualizacja event listenerów na polu rozgrywki<br>3. Wybranie pola przez gracza<br>4. Sprawdzane jest czy miejsce na planszy jest zajęte<br>5. Usunięcie event listenerów<br>6. Odejmowana jest odpowiednia ilość kompostu<br>7. Dodanie kupionej rośliny do repozytorium<br>8. Utworzenie obrazku |
| Alternatywny przepływ zdarzeń | 3b. Anulowanie zakupu<br>4b. Usunięcie event listenerów                                                                                                                                                                                                                                                                                      |
| Specjalne wymagania           | brak                                                                                                                                                                                                                                                                                                                                         |
| Notatki i kwestie             | Na przykład rośliny                                                                                                                                                                                                                                                                                                                          |

![image](https://gist.github.com/assets/126475707/2815601e-c2ea-4252-a8a1-fa233295d6f2)

### Przypadek użycia - Zbierz śmieć

| Nazwa                         | Zbierz śmieć                                                                                                        |
|-------------------------------|---------------------------------------------------------------------------------------------------------------------|
| Autor                         | Gracz                                                                                                               |
| Twórcy                        | Dominik Kwiecień i Hubert Pietras - projektanci                                                                     |
| Poziom ważności               | Wysoki                                                                                                              |
| Typ przypadku użycia          | Szczegółowy                                                                                                         |
| Aktorzy                       | Gracz                                                                                                               |
| Krótki opis                   | Gracz zbiera śmieć z planszy                                                                                        |
| Warunki wstępne               | Istnieje śmieć w objectRepository                                                                                   |
| Warunki końcowe               | Zaktualizowanie wartości w ItemRepository                                                                           |
| Główny przepływ zdarzeń       | 1. Zebranie śmiecia przez gracza<br>2. Dodanie pudełka do ItemRepository<br>3. Usunięcie śmiecia z ObjectRepository |
| Alternatywny przepływ zdarzeń | brak                                                                                                                |
| Specjalne wymagania           | brak                                                                                                                |
| Notatki i kwestie             | Na przykład rośliny                                                                                                 |

![image](https://gist.github.com/assets/126475707/022d3559-dabf-451a-b951-9e78b2be9ef9)
## Diagram czynności
![image](https://gist.github.com/assets/126475707/009a051f-9f4d-4e09-aa40-c4e5aca8441f)

Digram przedstawia cykl życia przeciwnika
## Diagram maszyny stanowej
![image](https://gist.github.com/assets/126475707/05a4db66-bcfc-4897-a1fc-3a28982c08ff)

Diagram przedstawia stany które może przyjąć gra
## Diagram pakietów
![image](https://gist.github.com/assets/126475707/a244c2e8-bcfa-4177-a8b2-7ab4b07a07a3)

Diagram przedstawia zależność głównego pakietu gry od pakietu umożliwiającego komunikację z resztą systemu
## Diagram przeglądu interakcji
![image](https://gist.github.com/assets/126475707/f61db615-9bdc-4fd7-9099-58921e9e952a)

Diagram przedstawia przepływ sterowania podczas dodawania śmiecia do repozytorium
## Diagram strukturalny
![image](https://gist.github.com/assets/126475707/e7749ccb-4409-4f0b-9177-b54977f755ab)

Diagram przedstawia klasy biorące udział w dodawaniu obiektu do planszy
## Diagram harmonogramowania
![image](https://gist.github.com/assets/126475707/4f894074-dc53-4067-a60c-7315404001a5)

Diagram przedstawia pojawianie się śmieci z uwzględnieniem czasu
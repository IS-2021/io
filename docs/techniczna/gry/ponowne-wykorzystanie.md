# Ponowne wykorzystanie
<!-- Szablon dokumentacji technicznej jest obligatoryjny -->
<!-- należy zostawić i uzupełnić wszystkie poniższe sekcje oraz zmienić tytuł -->
<!-- Sporządzone wg. https://ftims.edu.p.lodz.pl/mod/page/view.php?id=121103 -->

<!-- Parę pomocniczych linków, które mogą się przydać -->
<!-- (dokumentacja narzędzia do dokumentacji) -->
<!-- Tabelki: https://squidfunk.github.io/mkdocs-material/reference/data-tables/#data-tables -->
<!-- Diagramy (jeśli ktoś bardzo by chciał): https://squidfunk.github.io/mkdocs-material/reference/diagrams/ -->
<!-- Obrazki: https://squidfunk.github.io/mkdocs-material/reference/images/ -->

<!-- Pamiętajcie by usunać komentarze -->
# Moduł aplikacji

## Diagramy przypadków użycia


## Diagramy klas
![image](https://gist.github.com/assets/126475707/6fd393da-9c6b-40ba-8054-14b9d43cb427)

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

## Diagram czynności


## Diagram maszyny stanowej


## Diagram komponentów


<!-- Tylko grupa architektów -->
## Diagram wdrożeń


## Diagram pakietów


## Diagram przeglądu interakcji


## Diagram strukturalny


## Diagram harmonogramowania
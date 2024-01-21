# Moduł codziennych zadań

Autorzy: Jakub Kazimierczak, Marcin Szymajda

Moduł odpowiedzialny za generację oraz przypisywanie codziennych zadań do użytkowników.

## Diagram klas

Warto zwrócić uwagę na klasę DailyTaskState, która informuje w jakim stanie znajduje się codzienne zadanie.

![class](https://gist.github.com/assets/126806633/52f6a11f-725e-4109-91ce-b03ae91b6b46)

## Diagram przypadków użycia

Diagram obrazuje możliwe przypadki użycia dla modułu codziennych zadań.

![useCase](https://gist.github.com/assets/126806633/a76b2acb-4b14-45a6-92e7-cce66fbff3da)

## Scenariusz - Wyświetl obecne zadania

| Nazwa                          | Wyświetl obecne zadania                                                                                              |
| ------------------------------ | -------------------------------------------------------------------------------------------------------------------- |
| Autorzy                        | Marcin Szymajda, Jakub Kazimierczak                                                                                  |
| Priorytet                      | Średni                                                                                                               |
| Typ                            | Ogólny                                                                                                               |
| Aktorzy                        | Klient API                                                                                                           |
| Opis                           | Przypadek dotyczy pobrania codziennych zadań dla danego gracza.                                                      |
| Warunek początkowy             | Użytkownik zalogowany, który przeszedł do zakładki dotyczącej wyboru codziennego zadania.                            |
| Główny przepływ zdarzeń        | 1. Użytkownik wybiera opcję podglądu codziennych zadań. </br> 2. Użytkownik wybiera jedno z codziennych zadań. </br> |
| Alternatywne przepływy zdarzeń | -----------------                                                                                                    |
| Zakończenie                    | Użytkownik ma podgląd codziennych zadań.                                                                             |
| Warunek końcowy                | -----------------                                                                                                    |

## Diagram sekwencji - Wyświetl obecne zadania

Diagram obrazujący wywołania metod, które wyświetlą użytkownikowi codzienne zadania. Aktorem jest Klient API.

![seq1](https://gist.github.com/assets/126806633/bf49f28c-dc5b-44fa-ad07-1781f2b24c56)

## Scenariusz - Zatwierdź wybrane zadanie

| Nazwa                          | Zatwierdź wybrane zadanie                                                                                 |
| ------------------------------ | --------------------------------------------------------------------------------------------------------- |
| Autorzy                        | Marcin Szymajda, Jakub Kazimierczak                                                                       |
| Priorytet                      | Średni                                                                                                    |
| Typ                            | Ogólny                                                                                                    |
| Aktorzy                        | Klient API                                                                                                |
| Opis                           | Przypadek dotyczy wybrania codziennego zadania przez gracza.                                              |
| Warunek początkowy             | Użytkownik zalogowany, który przeszedł do zakładki dotyczącej wyboru codziennego zadania.                 |
| Główny przepływ zdarzeń        | 1. Użytkownik wybiera jedno z codziennych zadań. </br> 2. Wybrane zadanie zostaje zapisane do bazy. </br> |
| Alternatywne przepływy zdarzeń | -----------------                                                                                         |
| Zakończenie                    | Zostaje odblokowana gra, której codzienne zadanie dotyczy.                                                |
| Warunek końcowy                | -----------------                                                                                         |

## Diagram sekwencji - Zatwierdź wybrane zadanie

Diagram obrazujący kolejne wywołania metod, które zatwierdzą w systemie wybrane zadanie. Aktorem jest Klient API.

![seq2](https://gist.github.com/assets/126806633/93f1fc0d-8d91-42f0-b042-e913fff3259d)

## Diagram czynności

Diagram obrazuje kolejne czynności wykonywane w ramach wyboru codziennego zadania.

![czynnosci](https://gist.github.com/assets/126806633/d416f468-8b52-47b9-8935-d3860f3dfc8c)

## Diagram maszyny stanowej

Zadanie może znaleźć się w trzech stanach, które diagram maszyny stanowej prezentuje.

![stanowy](https://gist.github.com/assets/126806633/00fd26a3-3027-423f-9287-dda51ae0743a)

## Diagram pakietów

Diagram pakietów obrazujący zależności między pakietami.

![pakiet](https://gist.github.com/assets/126806633/0877b6ae-3cdf-447e-bd89-2b42a3d780e2)

# Moduł rankingu

Autorzy: Jakub Kazimierczak, Marcin Szymajda

Moduł odpowiedzialny jest za wyświetlanie rankingu ogólnego, wyświetlanie rankingu dotyczącego wybranej gry oraz za aktualizację sumy punktów zebranej przez gracza.

## Diagram klas

Klasa UpdateRanking jest rekordem dlatego też nie widać jej powiązania między innymi klasami.

![class](https://gist.github.com/assets/126806633/6c42d03e-a011-4cf4-9b11-d2f38be15ca6)

## Diagram przypadków użycia

Prosty diagram obrazujący możliwe przypadki użycia Klienta API i użytkownika.

![usecase](https://gist.github.com/assets/126806633/666c7f20-8b98-466e-bb75-e588653302ba)

## Scenariusz - Wyświetlenie rankingu ogólnego

| Nazwa                          | Wyświetlenie rankingu ogólnego                                                                                          |
| ------------------------------ | ----------------------------------------------------------------------------------------------------------------------- |
| Autorzy                        | Marcin Szymajda, Jakub Kazimierczak                                                                                     |
| Priorytet                      | Niski                                                                                                                   |
| Typ                            | Ogólny                                                                                                                  |
| Aktorzy                        | Klient API                                                                                                              |
| Opis                           | Przypadek dotyczy wyświetlenie rankingu ogólnego                                                                        |
| Warunek początkowy             | Użytkownik zalogowany                                                                                                   |
| Główny przepływ zdarzeń        | 1. Użytkownik przechodzi na stronę dotyczącą rankingu graczy. </br> 2. Automatycznie zostaje załadowany ranking ogólny. |
| Alternatywne przepływy zdarzeń | -----------------                                                                                                       |
| Zakończenie                    | Użytkownikowi zostaje zaprezentowany ranking najlepszych graczy                                                         |
| Warunek końcowy                | -----------------                                                                                                       |

## Diagram sekwencji - Wyświetlenie rankingu ogólnego

Diagram pokazuje sekwencję wywoływania metod niezbędnych do wyświetlenia ogólnego rankingu

![seq1](https://gist.github.com/assets/126806633/b9c77e18-8a7d-41da-bdf1-3601c8d5ffa4)

## Scenariusz - Wyświetlenie rankingu poszczególnej gry

| Nazwa                          | Wyświetlenie rankingu poszczególnej gry                                                                                                                                                            |
| ------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Autorzy                        | Marcin Szymajda, Jakub Kazimierczak                                                                                                                                                                |
| Priorytet                      | Niski                                                                                                                                                                                              |
| Typ                            | Ogólny                                                                                                                                                                                             |
| Aktorzy                        | Klient API                                                                                                                                                                                         |
| Opis                           | Przypadek dotyczy wyświetlenie rankingu dla wybranej gry                                                                                                                                           |
| Warunek początkowy             | Użytkownik zalogowany                                                                                                                                                                              |
| Główny przepływ zdarzeń        | 1. Użytkownik przechodzi na stronę dotyczącą rankingu graczy. </br> 2. Użytkownikowi zostaje zaprezentowana lista dostępnych gier. 3. Użytkownik wybiera grę, z której chciałby podejrzeć ranking. |
| Alternatywne przepływy zdarzeń | -----------------                                                                                                                                                                                  |
| Zakończenie                    | Użytkownikowi zostaje zaprezentowany ranking najlepszych graczy w danej grze.                                                                                                                      |
| Warunek końcowy                | -----------------                                                                                                                                                                                  |

## Diagram sekwencji - Wyświetlenie rankingu poszczególnej gry

Diagram pokazuje sekwencję wywoływania metod niezbędnych do wyświetlenia rankingu danej gry.

![seq2](https://gist.github.com/assets/126806633/be136827-3d1f-4c56-9aa5-ee3db4cb3d25)

## Diagram czynności

Czynności wykonywane w ramach wyświetlania rankingu.

![czynnosci](https://gist.github.com/assets/126806633/d3a49cab-5eef-40c1-997a-b6dd199a1f84)

## Diagram maszyny stanowej

Diagram pokazuje możliwe stany dla modułu rankingu.

![stany](https://gist.github.com/assets/126806633/4fec8be3-5c38-4bf8-b260-d4f6f68e1e29)

## Diagram pakietów

Diagram pakietów obrazujący zależności między pakietami.

![pakiet](https://gist.github.com/assets/126806633/0877b6ae-3cdf-447e-bd89-2b42a3d780e2)

## Diagram strukturalny

Diagram pokazuje strukturę klas, które odpowiadają za aktualizację rankingu.

![strukt](https://gist.github.com/assets/126806633/5b35fefe-30e0-42d1-bbd8-8953befffc25)

## Diagram harmonogrowania

Przebieg procesu wyświetlania rankingu z uwzględnieniem czasu.

![harm](https://gist.github.com/assets/126806633/90b0bfe7-1a1b-4666-8875-8fbaa80b7eb0)

# Moduł aplikacji

Autorzy: Jakub Kazimierczak, Marcin Szymajda

W naszym rozwiązaniu na cały system możemy patrzeć przez pryzmat modułu aplikacji.
Jest on naszym "centrum dowodzenia", które spaja wszystkie utworzone komponenty w jedną logiczną całość.
Odpowiada on min. za utrzymanie sesji zalogowanego użytkownika oraz dostarczenie informacji właśnie o zalogowanym użytkowniku do innych komponentów, które tego wymagają.

Z racji na istotność tego modułu w całokształcie aplikacji, w jego dokumentacji zawarte również zostały - tyczące się całości systemu - cel i założenia, a także wymagania funkcjonalne i niefunkcjonalne.

## Cel i założenia

### Cel

Celem aplikacji jest kształtowanie zachowań proekologicznych wśród odbiorców gry.

### Założenia

-   [x] Dostęp do gry jest realizowany przez stronę internetową​.
-   [x] Dane użytkowników przechowywane są w bazie danych​.
-   [x] Użytkownicy są w stanie utworzyć swoje konto za pomocą loginu i hasła​.
-   [x] System śledzi postęp użytkownika i przydziela mu codzienne zadania​.
-   [x] Gracz jest nagradzany za swoje akcje za pomocą punktów​.
-   [x] Strona główna umożliwi wgląd w tabelę punktów z poszczególnych gier – tabela liderów danej gry​.

## Wymagania funkcjonalne

-   [x] Rejestracja i logowanie przy użyciu nazwy użytkownika oraz hasła.
-   [x] Zapisywanie postępów w poszczególnych aktywnościach​.
-   [x] Możliwość podglądu wyników pozostałych graczy​.
-   [x] Wynagradzanie codziennego logowania oraz konsekwentną realizację codziennych zadań​.
-   [x] Przypominanie użytkownikowi o realizacji danego zadania w danym dniu​.
-   [x] Możliwość ponownego grania w odblokowane już gry.
-   [x] Gry udostępniają różne poziomy o różnym stopniu trudności.
-   [x] Ukończenie danego poziomu wymaga osiągnięcia minimalnej liczby punktów.
-   [x] Możliwość zbierania monet za wykonywanie codziennych zadań.
-   [x] Możliwość kontynuacji grania w grze po przegraniu w zamian za monety.
-   [x] Zapewnienie użytkownikowi wyboru jednego z dwóch zadań do realizacji na dany dzień.
-   [x] Możliwość podglądu rankingu opartego o punkty.
-   [x] System umożliwia zobaczenie rankingu z poziomu widoku danej gry.
-   [x] Zebrane przez użytkownika monety wpływają na jego pozycję w ranking.
-   [ ] System umożliwia graczom wyzwanie się na pojedynek w ramach danej gry.

## Wymagania niefunkcjonalne

-   [x] Użytkownik nie ma dostępu do panelu aplikacji bez uprzedniego uwierzytelnienia.​
-   [x] Użytkownik nie ma dostępu do danej gry bez jej odblokowania.
-   [x] Dowolna akcja zapisu danych (rankingu, postępu) wymaga załączenia do żądania poświadczeń zalogowanego użytkownika.
-   [x] Serwer aplikacji jest dostępny o każdej porze.
-   [x] Hasła są przechowywane w postaci skrótów algorytmu BCrypt z dodatkiem soli.
-   [x] Codzienne zadania są przydzielane użytkownikowi w sposób losowy.
-   [x] Użytkownik ma możliwość wyboru tylko jednego codziennego zadania danego dnia.

## Diagram klas

Diagram pokazuje klasy niezbędne do działania modułu aplikacji.

![class](https://gist.github.com/assets/126806633/bc653d52-309c-4c4e-8854-db869b30e902)

## Diagram przypadków użycia dla użytkownika niezalogowanego

Diagram prezentujący możliwe ruchy dla użytkownika niezalogowanego.

![przyp1](https://gist.github.com/assets/126806633/28d5939e-db05-48fe-a774-434e0583419d)

## Diagram przypadków użycia dla użytkownika zalogowanego

Diagram prezentujący możliwe ruchy dla użytkownika zalogowanego.

![usecase](https://gist.github.com/assets/126806633/adc2c59f-1237-434b-89c3-107ec7348d67)

## Scenariusz - Logowanie

| Nazwa                          | Logowanie                                                                                                                                                                                                                                                             |
| ------------------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Autorzy                        | Marcin Szymajda, Jakub Kazimierczak                                                                                                                                                                                                                                   |
| Priorytet                      | Wysoki                                                                                                                                                                                                                                                                |
| Typ                            | Ogólny                                                                                                                                                                                                                                                                |
| Aktorzy                        | Klient API                                                                                                                                                                                                                                                            |
| Opis                           | Przypadek dotyczy momentu logowania się użytkownika                                                                                                                                                                                                                   |
| Warunek początkowy             | Użytkownik znajduje się na stronie logowania                                                                                                                                                                                                                          |
| Główny przepływ zdarzeń        | 1. Użytkownik wpisuje swoją nazwę użytkownika oraz hasło. </br> 2. Następuje walidacja wpisanych danych. </br> 3a. W przypadku poprawnych danych, użytkownik zostaje zalogowany </br> 3b. W przypadku błędnych danych, użytkownik otrzymuje stosowny komunikat. </br> |
| Alternatywne przepływy zdarzeń | -----------------                                                                                                                                                                                                                                                     |
| Zakończenie                    | Użytkownik zostaje zalogowany do systemu bądź otrzymuje komunikat o błędnych danych                                                                                                                                                                                   |
| Warunek końcowy                | -----------------                                                                                                                                                                                                                                                     |

## Diagram sekwencji - Logowanie

Widzimy tutaj wywołania odpowiednich metod służących do uwierzytelnienia użytkownika w systemie.

![seqLog](https://gist.github.com/assets/126806633/44927182-5500-46d9-9039-5206caf24b92)

## Scenariusz - Rejestracja

| Nazwa                          | Rejestracja                                                                                                                                                                                                                                                                                                                                                                  |
| ------------------------------ | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Autorzy                        | Marcin Szymajda, Jakub Kazimierczak                                                                                                                                                                                                                                                                                                                                          |
| Priorytet                      | Wysoki                                                                                                                                                                                                                                                                                                                                                                       |
| Typ                            | Ogólny                                                                                                                                                                                                                                                                                                                                                                       |
| Aktorzy                        | Klient API                                                                                                                                                                                                                                                                                                                                                                   |
| Opis                           | Przypadek dotyczy rejestracji nowego użytkownika                                                                                                                                                                                                                                                                                                                             |
| Warunek początkowy             | Użytkownik znajduje się na stronie rejestracji                                                                                                                                                                                                                                                                                                                               |
| Główny przepływ zdarzeń        | 1. Użytkownik wpisuje swoją nazwę użytkownika oraz hasło. </br> 2. Następuje sprawdzenie poprawności wprowadzonych danych. </br> 3a. W przypadku braku konfliktu nazw użytkowników oraz jeżeli hasło spełnia wymogi bezpieczeństwa, konto zostaje utworzone. </br> 3b. W przypadku konfliktu nazw użytkownika bądź słabego hasła, zostaje zwrócony stosowny komunikat. </br> |
| Alternatywne przepływy zdarzeń | -----------------                                                                                                                                                                                                                                                                                                                                                            |
| Zakończenie                    | Użytkownik tworzy konto w systemie lub zostaje poinformowany o błędzie w tym procesie.                                                                                                                                                                                                                                                                                       |
| Warunek końcowy                | -----------------                                                                                                                                                                                                                                                                                                                                                            |

## Diagram sekwencji - Rejestracja

Widzimy tutaj wywołania odpowiednich metod służących do rejestracji użytkownika w systemie.

![seq1](https://gist.github.com/assets/126806633/827cde23-6d09-46b4-ae5e-95b9e397691a)

## Diagram czynności

Diagram czynności pokazujący kolejne kroki w procesie uwierzytelniania użytkownika.

![czynnosci](https://gist.github.com/assets/126806633/5bdd7021-2ef7-4865-a587-ecee446fb698)

## Diagram maszyny stanowej

Diagram pokazuje możliwe stany systemu.

![stany](https://gist.github.com/assets/126806633/bef03241-4b16-444c-92cb-2518d3836754)

## Diagram komponentów

Diagram opisuje relacje między komponentami systemu.

![komponenty](https://gist.github.com/assets/126806633/d8e9d124-973d-465f-b817-56a73ccc276d)

## Diagram wdrożeń

Diagram pokazuje zaprojektowaną infrastrukturę systemu.

![wdrozen](https://gist.github.com/assets/126806633/c0b48a0a-37a6-448e-a7b4-928109ae0b95)

## Diagram pakietów

Diagram pakietów obrazujący zależności między pakietami.

![pakiet](https://gist.github.com/assets/126806633/0aeafc37-c71b-44db-b242-ea6e0960f60b)

## Diagram strukturalny

Poniższy diagram opisuje relacje klas odpowiadających za proces logowania.

![strukturalny](https://gist.github.com/assets/126806633/b11e9c7a-3b52-4db4-bef8-99a6801f5405)

## Diagram harmonogrowania

Diagram harmonogrowania przedstawia proces rejestracji nowego użytkownika rozłożony w czasie.

![harmong](https://gist.github.com/assets/126806633/53cae19c-e3fc-455a-8367-d54001882116)

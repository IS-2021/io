# Moduł aplikacji


## Krótki opis
W naszym rozwiązaniu na cały system możemy patrzeć przez pryzmat modułu aplikacji. 
Jest on naszym "centrum dowodzenia", które spaja wszystkie utworzone komponenty w jedną logiczną całość.
Odpowiada on min. za utrzymanie sesji zalogowanego użytkownika oraz dostarczenie informacji właśnie o zalogowanym użytkowniku do innych komponentów, które tego wymagają.



## Diagram przypadków użycia dla użytkownika niezalogowanego

![przyp1](https://gist.github.com/assets/126806633/28d5939e-db05-48fe-a774-434e0583419d)


## Diagram przypadków użycia dla użytkownika zalogowanego

![useCase](https://gist.github.com/assets/126806633/97d6d388-bc86-4f0f-befc-2c55c75237f1)

## Diagram sekwencji dla przypadku użycia Logowanie

Widzimy tutaj wywołania odpowiednich metod służących do uwierzytelnienia użytkownika w systemie.

![seqLog](https://gist.github.com/assets/126806633/44927182-5500-46d9-9039-5206caf24b92)


## Scenariusz dla przypadku użycia Logowanie

| Nazwa                          | Logowanie                                                                                                                                                                                                                                                             |
|--------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
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

## Diagram sekwencji dla przypadku użycia Rejestracja

Widzimy tutaj wywołania odpowiednich metod służących do rejestracji użytkownika w systemie.


![seq1](https://gist.github.com/assets/126806633/827cde23-6d09-46b4-ae5e-95b9e397691a)


## Scenariusz dla przypadku użycia Rejestracja
| Nazwa                          | Rejestracja                                                                                                                                                                                                                                                                                                                                                                  |
|--------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
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





## Diagram klas

Diagram pokazuje klasy niezbędne do działania modułu aplikacji.

![class](https://gist.github.com/assets/126806633/bc653d52-309c-4c4e-8854-db869b30e902)


## Diagram czynności 

Diagram czynności pokazujący kolejne kroki w procesie uwierzytelniania użytkownika.

![czynnosci](https://gist.github.com/assets/126806633/5bdd7021-2ef7-4865-a587-ecee446fb698)


## Diagram maszyny stanowej

Diagram pokazuje możliwe stany systemu.

![stany](https://gist.github.com/assets/126806633/7eb824c8-bda8-458b-ae48-1c405a328771)


## Diagram komponentów

Diagram opisuje relacje między komponentami systemu

![komponenty](https://gist.github.com/assets/126806633/d8e9d124-973d-465f-b817-56a73ccc276d)


## Diagram wdrożeń

Diagram pokazuje zaprojektowaną infrastrukturę systemu

![wdrozen](https://gist.github.com/assets/126806633/c0b48a0a-37a6-448e-a7b4-928109ae0b95)


## Diagram pakietów

Diagram pakietów obrazujący zależności między pakietami

![pakiety](https://gist.github.com/assets/126806633/6badca80-a0b5-495b-a07a-519f891b1b58)

## Diagram strukturalny

Poniższy diagram opisuje relacje klas odpowiadających za proces logowania.

![strukturalny](https://gist.github.com/assets/126806633/b11e9c7a-3b52-4db4-bef8-99a6801f5405)

## Diagram harmonogrowania

Diagram harmonogrowania przedstawia proces rejestracji nowego użytkownika rozłożony w czasie.

![harmong](https://gist.github.com/assets/126806633/53cae19c-e3fc-455a-8367-d54001882116)

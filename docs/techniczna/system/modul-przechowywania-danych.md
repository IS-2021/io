# Moduł przechowywania danych


Zadaniem tego modułu jest pobieranie oraz zapisywanie danych z/do bazy danych dotyczących gier. Jest to moduł opcjonalny, zatem zespoły projektantów gier nie są zobowiązani do wykorzystywania go w swoich grach.


## Diagram klas
![class](https://gist.github.com/assets/126806633/43bea5aa-6faa-4730-ab04-42895eed3d3b)


Warto zwrócić uwagę na klasę SaveGameData, która jest rekordem DTO (Data Transfer Object).

## Diagram przypadków użycia

![useCase](https://gist.github.com/assets/126806633/7b8237d5-7a6d-4aca-810e-6419fc0f9225)



## Diagramy interakcji 
![interak](https://gist.github.com/assets/126806633/80714ed3-3bf0-4d47-bae6-1854fee507f5)

Diagram przedstawia zapis danych gry do bazy. Aktorem jest Klient API

![seq2](https://gist.github.com/assets/126806633/dd8f519d-8009-459e-8449-6d74fbd4c06e)

Diagram przedstawia odczyt danych gry z bazy. Aktorem jest Klient API


## Diagram czynności

![czynn](https://gist.github.com/assets/126806633/d0d2c4cd-b42f-47e1-9222-7420e9907421)

Prosty diagram czynności przedstawiający proces pobierania danych gry z bazy danych.


## Scenariusz dotyczący przypadku użycia - Pobierz dane gry

| Nazwa                          | Pobierz dane gry                                                                                                                                                                                                                                                            |
|--------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Autorzy                        | Marcin Szymajda, Jakub Kazimierczak                                                                                                                                                                                                                                         |
| Priorytet                      | Niski                                                                                                                                                                                                                                                                       |
| Typ                            | Ogólny                                                                                                                                                                                                                                                                      |
| Aktorzy                        | Klient API                                                                                                                                                                                                                                                                  |
| Opis                           | Przypadek dotyczący pobierania danych gry z bazy danych przez klienta API                                                                                                                                                                                                   |
| Warunek początkowy             | Użytkownik zalogowany, który dokonał wyboru gry.                                                                                                                                                                                                                            |
| Główny przepływ zdarzeń        | 1. Użytkownik wybiera grę. <br/> 2. System rozpoznaje wybór i sprawdza, czy w bazie gra zapisała swoje informację <br/> 3a. W przypadku istnienia takich danych, zostają one zwrócone do gry. <br/> 3b. W przypadku braku takich danych, system kończy działanie przypadku. |
| Alternatywne przepływy zdarzeń | -----------------                                                                                                                                                                                                                                                           |
| Zakończenie                    | Zwrócone metadane dotyczące gry                                                                                                                                                                                                                                             |
| Warunek końcowy                | -----------------                                                                                                                                                                                                                                                           |

## Scenariusz dotyczący przypadku użycia - Zapisz dane gry

| Nazwa                          | Zapisz dane gry                                                                                                                                                                                                                                                                                                                                              |
|--------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Autorzy                        | Marcin Szymajda, Jakub Kazimierczak                                                                                                                                                                                                                                                                                                                          |
| Priorytet                      | Niski                                                                                                                                                                                                                                                                                                                                                        |
| Typ                            | Ogólny                                                                                                                                                                                                                                                                                                                                                       |
| Aktorzy                        | Klient API                                                                                                                                                                                                                                                                                                                                                   |
| Opis                           | Przypadek dotyczący zapisu danych gry do bazy danych przez klienta API                                                                                                                                                                                                                                                                                       |
| Warunek początkowy             | Użytkownik zalogowany, który dokonał wyboru gry oraz uzyskał wymagany postęp.                                                                                                                                                                                                                                                                                |
| Główny przepływ zdarzeń        | 1. Użytkownik jest w trakcie gry. <br/> 2. Użytkownik przechodzi grę, bądź decyduje się przerwać rozgrywkę. <br/> 3. Gra dokonuje wysłania odpowiedniego żądania zapisu swoich metadanych. <br/> 4a. W przypadku istnienia już takich danych, stare wartości są aktualizowane. <br/> 4b. W przypadku braku takich danych, dokonywany jest nowy wpis do bazy. |
| Alternatywne przepływy zdarzeń | -----------------                                                                                                                                                                                                                                                                                                                                            |
| Zakończenie                    | Dane gry zostają zapisane do bazy                                                                                                                                                                                                                                                                                                                            |
| Warunek końcowy                | -----------------                                                                                                                                                                                                                                                                                                                                            |

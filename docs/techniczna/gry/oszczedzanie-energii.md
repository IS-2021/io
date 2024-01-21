# Oszczędzanie energii

Autorzy: Dawid Gąsior, Jakub Osypiuk

## Diagramy przypadków użycia
<img src="https://i.imgur.com/9Bq2LPZ.png">
<img src="https://i.imgur.com/9yvnd9K.jpg">

## Diagramy klas
<img src="https://i.imgur.com/HBZDhbm.png">

## Diagramy interakcji i scenariusze

### Przypadek użycia - Odpowiedz na pytanie w quizie

|||
|----|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|Twórcy | Dawid Gąsior I Jakub Osypiuk – projektanci                                                                                                                                                                                                                                                             |
|Typ przypadku użycia | Ogólny                                                                                                                                                                                             |
|Poziom ważności | Wysoki                                                                                                                                                                                             |
|Krótki opis|Gracz podczas danego poziomu odpowiada na pytania|
|Warunki wstępne|Gracz rozpoczął poziom|
|Aktorzy | Gracz                              |
|Warunki końcowe| Inne moduły otrzymały odpowiednie dane                                                                                                                                                             |
|Główny przepływ zdarzeń| <ol><li>Użytkownikowi wyświetla się pytanie</li><li>Gracz odpowiada na pytanie</li><li>Sprawdzana jest poprawność odpowiedzi</li><li>Wyświetlany jest komunikat o poprawności odpowiedzi</li></ol> |
|Alternatywny przepływ zdarzeń| 4a. Wyświetlany jest komunikat o niepoprawności odpowiedzi i odejmowany jest czas na pozostałą rozgrywkę                                                                                           |

<img src="https://i.imgur.com/tFrUfys.png">

### Przypadek użycia - Włącz prąd

|||
|----|--------------------------------------------|
|Twórcy| Dawid Gąsior I Jakub Osypiuk – projektanci |
|Typ przypadku użycia| Szczegółowy                                |
|Poziom ważności|Wysoki|
|Aktorzy|Gracz|
|Krótki opis|Gracz uruchamia główny przełącznik przepływu prądu|
|Warunki wstępne|Gracz rozpoczął poziom|
|Warunki końcowe|Inne moduły otrzymały odpowiednie dane|
|Główny przepływ zdarzeń| <ol><li>Gracz włączył główny przełącznik.</li><li>Poziom odczytuje stan przełacznika.</li><li>Przełącznik pokazuje swój stan.</li><li>EventListener w Grze widzi zmieniony stan poziomu.</li><li>Level sprawdza, czy urządzenie jest zasilane.</li><li>Urządzenie odczytuje na podstawie WIreIntersection czy jest zasilane.</li><li>WireIntersection zwraca stany przecięć.</li><li>Urządzenie zwraca swój stan.</li><li>Level sprawdza, czy urządzenie powinno być włączone.</li><li>Urządzenie zwraca wartość, czy powinno być włączone.</li><li>Level zwraca liczbę błędów poziomowi.</li><li>Poziom ustawia wynik.</li><li>Wynik się wyświetla.</li></ol>                |
|Alternatywny przepływ zdarzeń|brak|

<img src="https://i.imgur.com/QzZirE1.png">

## Diagram czynności
<img src="https://i.imgur.com/OqVpI9p.png">

## Diagram maszyny stanowej
<img src="https://i.imgur.com/urCkPcV.png">

## Diagram komponentów
<img src="https://i.imgur.com/BSjyGdL.png">

## Diagram przeglądu interakcji
<img src="https://i.imgur.com/lLCHdf7.png">

## Diagram strukturalny
<img src="https://i.imgur.com/VwkPpBK.png">

## Diagram harmonogramowania
<img src="https://i.imgur.com/WJiIsjv.png">
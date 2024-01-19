# Recykling - dokumentcja techniczna

## Cel i założenia

### Cel

- Wdrożenie u odbiorcy idei segregacji odpadów poprzez grę.

### Założenia

- Użytkownik ma do wyboru kilka poziomów trudności
- Użytkownik otrzymuje adekwatną liczbę punktów cd. prędkości z jaką wykonał zadania i tego jaki poziom trudności wybrał
- Moduł przesyła informacje o otrzymanym wyniku do innych modułów
- Edukacja użytkownika w zakresie recyklingu przez zabawę
- Gra opiera się na „Space Invaders”

## Zmiany założeń względem początkowej prezentacji projektu

## Wymagania funkcjonalne

- Możliwość wyboru poziomu trudności
- Możliwość wyświetlenia instrukcji
- Możliwość rozpoczęcia rozgrywki

- Przebieg rozgrywki:
1. Sterowanie statkiem
2. Unikanie przeciwników
3. Strzelanie

- Gra wyświetla komunikat o wyniku rozgrywki po jej zakończeniu
- Możliwość ponownego rozpoczęcia rozgrywki po jej zakończeniu

## Wynagania niefunkcjonalne

- Gra pobiera postęp gracza z modułu przechowywania danych 
- Gra po zakończonej powodzeniem rozgrywce wysyła do modułów rankingu i przechowywania 
danych informacje o wyniku rozgrywki
- Jeżeli użytkownik nie przejdzie pomyślnie poziomu, to jego wynik nie jest zapisywany w 
rankingu
- Dany rodzaj odpadów jest możliwy do zestrzelenia tylko konkretnym kolorem lasera
- Działo po zbyt częstym strzelaniu przegrzewa się
- Ukończenie poziomu wymaga osiągnięcia minimalnego przedziału punktów
- Poziom kończy się gdy przeciwnicy dojdą do wysokości gracza
- Z odpadów co jakiś czas będzie wychodziło coś czego gracz powinien unikać

## Zmiany wymagań

## Diagram przypadków użycia - 1

![png](https://i.imgur.com/UeA55Ae.png)

opis diagramu
> opis
>
> .

## Diagram przypadków użycia - 2, doprecyzowanie przypadku użycia `przebieg rozgrywki`

![png](https://i.imgur.com/iez0DGS.png)

## Diagram klas

![png](https://i.imgur.com/9268AN4.png)

## Scenariusz - `Wybór poziomu trudności`



## Diagram sekwencji - `Wybór poziomu trudności`

## Scenariusz - `Wysyłanie danych z rozgrywki do modułów`

  <table>
        <tr>
            <th>Nazwa</th>
            <td>Wysłanie danych z rozgrywki do modułów</td>
        </tr>
        <tr>
            <th>Numer</th>
            <td>5</td>
        </tr>
        <tr>
            <th>Twórca</th>
            <td>Patrycja Przybylska, Andrzej Szlaski – Projektanci</td>
        </tr>
        <tr>
            <th>Poziom ważności</th>
            <td>Umiarkowanie wysoki</td>
        </tr>
        <tr>
            <th>Typ przypadku użycia</th>
            <td>Ogólny</td>
        </tr>
        <tr>
            <th>Aktorzy</th>
            <td>Użytkownik</td>
        </tr>
        <tr>
            <th>Krótki opis</th>
            <td>Użytkownik otrzymuje informacje o wyniku rozgrywki i odpowiednie dane są wysyłane do innych modułów</td>
        </tr>
        <tr>
            <th>Warunki wstępne</th>
            <td>Rozgrywka została ukończona</td>
        </tr>
        <tr>
            <th>Warunki końcowe</th>
            <td>Inne moduły otrzymały odpowiednie dane</td>
        </tr>
        <tr>
            <th>Główny przepływ zdarzeń</th>
            <td>
                <ol>
                    <li>W oparciu o uzyskany wynik do modułu rankingu przesyłane są odpowiednie wartości</li>
                    <li>Do modułu przechowywania danych wysyłane są informacje o postępach gracza</li>
                </ol>
            </td>
        </tr>
        <tr>
            <th>Alternatywne przepływy zdarzeń</th>
            <td>
                <ol>
                    <li>Jeśli gra nie została zakończona to dane nie są wysyłane do modułu rankingu.</li>
                    <li>Jeśli gra została przegrana to dane nie są wysyłane do modułu rankingu.</li>
                    <li>Jeśli gra nie została zakończona to dane nie są wysyłane do modułu przechowywania danych.</li>
                    <li>Jeśli gra została przegrana to dane nie są wysyłane do modułu przechowywania danych.</li>
                </ol>
            </td>
        </tr>
        <tr>
            <th>Specjalne wymagania</th>
            <td>Połączenie z innymi modułami</td>
        </tr>
        <tr>
            <th>Notatki i kwestie</th>
            <td>Brak</td>
        </tr>
    </table>

## Diagram sekwencji - `Wysyłanie danych z rozgrywki do modułów`

## Diagram czynności

## Diagram maszyny stanowej

## Diagram komponentów

## Diagram pakietów

## Diagram przeglądu interakcji

## Diagram strukturalny

## Diagram harmonogramowia






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

## Wymagania niefunkcjonalne

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

Diagram przedstawiający akcje, które są możliwe do wykonania przed rozpoczęciem rozgrywki i po jej zakończeniu.

![png](https://i.imgur.com/UeA55Ae.png)


## Diagram przypadków użycia - 2, doprecyzowanie przypadku użycia `przebieg rozgrywki`

Diagram przedstawiający akcje, które są możliwe do wykonania w trakcie rozgrywki.

![png](https://i.imgur.com/iez0DGS.png)

## Diagram klas

Diagram przedstawiający klasy użyte do stworzenia gry.

![png](https://i.imgur.com/9268AN4.png)

## Scenariusz - `Wybór poziomu trudności`

 <table>
        <tr>
            <th>Poziom ważności</th>
            <td>Wysoki</td>
        </tr>
        <tr>
            <th>Typ przypadku użycia</th>
            <td>Szczegółowy</td>
        </tr>
        <tr>
            <th>Aktorzy</th>
            <td>Użytkownik</td>
        </tr>
        <tr>
            <th>Krótki opis</th>
            <td>Użytkownik wybiera wybór trudności za pomocą przycisku myszki.</td>
        </tr>
        <tr>
            <th>Warunki wstępne</th>
            <td>Znajomość zasad i przebiegu rozgrywki przez użytkownika</td>
        </tr>
        <tr>
            <th>Warunki końcowe</th>
            <td>Użytkownik rozpoczął rozgrywkę na wybranym poziomie.</td>
        </tr>
        <tr>
            <th>Główny przepływ zdarzeń</th>
            <td>
                <ol>
                    <li>Gracz może wybrać jeden z trzech poziomów trudności klikając na niego myszką.</li>
                </ol>
            </td>
        </tr>
        <tr>
            <th>Alternatywne przepływy zdarzeń</th>
            <td>
                <ol>
                    <p>1a. Poziom trudności nie zmieni się jeśli gracz nie ma do niego dostępu.
                </ol>
            </td>
        </tr>
        <tr>
            <th>Specjalne wymagania</th>
            <td>Posiadanie odpowiednich danych z serwera żeby sprawdzić czy gracz ma odblokowane wszystkie poziomy.</td>
        </tr>
        <tr>
            <th>Notatki i kwestie</th>
            <td>Brak</td>
        </tr>
</table>

## Diagram sekwencji - `Wybór poziomu trudności`

Można tutaj zauważyć wywołania odpowiednich metod, które służą do wyboru poziomu trudności.

![png](https://i.imgur.com/mzazY8e.png)

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
                    <p>1. W oparciu o uzyskany wynik do modułu rankingu przesyłane są odpowiednie wartości</p>
                    <p>2. Do modułu przechowywania danych wysyłane są informacje o postępach gracza</p>
                </ol>
            </td>
        </tr>
        <tr>
            <th>Alternatywne przepływy zdarzeń</th>
            <td>
                <ol>
                    <p>1a. Jeśli gra nie została zakończona to dane nie są wysyłane do modułu rankingu.</p>
                    <p>1b. Jeśli gra została przegrana to dane nie są wysyłane do modułu rankingu.</p>
                    <p>2a. Jeśli gra nie została zakończona to dane nie są wysyłane do modułu przechowywania danych.</p>
                    <p>2b. Jeśli gra została przegrana to dane nie są wysyłane do modułu przechowywania danych.</p>
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

Można tutaj zauważyć wywołania odpowiednich metod, które służą do wyboru poziomu trudności.

![png](https://i.imgur.com/enHSYoq.png)

## Diagram czynności

![png](https://i.imgur.com/K9nB1i4.png)

## Diagram maszyny stanowej

![png](https://i.imgur.com/GgCRa9Q.png)

## Diagram komponentów

Diagram opisuje relacje między komponentami systemu

![png](https://i.imgur.com/IxNoikL.png)

## Diagram pakietów

![png](https://i.imgur.com/eHxXzW7.png)

## Diagram przeglądu interakcji

![png](https://i.imgur.com/JeHk3Ga.png)

## Diagram strukturalny

Diagram opisuje relacje klas. Klasą centralną jest klasa "Game". Jest ona bezpośrednio powiązana z klasami: 'Enemy', 'Player', 'Rubbish', 'Laser'. 
- Relacja z 'Enemy' wskazuje, że jedna gra może mieć wiele wrogów (1 do 0...*).
- Relacja z 'Player' oznacza, że gra zawiera jednego gracza (1 do 1).
- Relacja z 'Rubbish' i 'Laser' oznacza, że gra może generować różne rodzaje śmieci i laserów.

![png](https://i.imgur.com/ETVTRVm.png)

## Diagram harmonogramowania

Diagram harmonogramowania

![png](https://i.imgur.com/n1khsnf.png)


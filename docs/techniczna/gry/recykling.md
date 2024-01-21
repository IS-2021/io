# Recykling

Autorzy: Patrycja Przybylska, Andrzej Szlaski

## Cel i założenia

### Cel

- Wdrożenie u odbiorcy idei segregacji odpadów poprzez grę.

### Założenia

- Użytkownik ma do wyboru kilka poziomów trudności
- Użytkownik otrzymuje adekwatną liczbę punktów cd. tego jaki poziom trudności wybrał
- Moduł przesyła informacje o otrzymanym wyniku do innych modułów
- Edukacja użytkownika w zakresie recyklingu poprzez zabawę
- Gra opiera się na „Space Invaders”

## Wymagania funkcjonalne

- Możliwość wyboru poziomu trudności
- Możliwość wyświetlenia instrukcji
- Możliwość rozpoczęcia rozgrywki

- Przebieg rozgrywki:
	1. Sterowanie statkiem
	2. Unikanie przeciwników
	3. Strzelanie
	4. Schładzanie broni
	5. Eliminacja odpadów

- Gra wyświetla komunikat o wyniku rozgrywki po jej zakończeniu
- Możliwość ponownego rozpoczęcia rozgrywki po jej zakończeniu

## Wymagania niefunkcjonalne

- Gra pobiera postęp gracza z modułu przechowywania danych 
- Gra po zakończonej powodzeniem rozgrywce wysyła do modułów rankingu i przechowywania danych informacje o wyniku rozgrywki
- Jeżeli użytkownik nie przejdzie pomyślnie poziomu, to jego wynik nie jest zapisywany w rankingu
- Dany rodzaj odpadów jest możliwy do zestrzelenia tylko konkretnym kolorem lasera
- Działo w miarę strzelania przegrzewa się
- Gra kończy się gdy przeciwnicy dojdą do wysokości gracza lub gry wejdzie on w kontakt z przeciwnikiem
- Z odpadów co jakiś czas będą wychodzili przeciwnicy, którch gracz powinien unikać

## Zmiany wymagań i założeń względem prezentacji początkowej modułu

- Ukończenie poziomu wymaga już osiągnięcia minimalnego przedziału punktów a zlikwidowania wszystkich odpadów
- Zrezygnowano z klas Filed oraz Game, obecnie tfunkcjonalności tych klas zaimplementowane są w funkcjach i zmiennych globalnych

## Diagram przypadków użycia - 1

Diagram przedstawiający akcje, które są możliwe do wykonania przed rozpoczęciem rozgrywki i po jej zakończeniu.

![png](https://i.imgur.com/UeA55Ae.png)


## Diagram przypadków użycia - 2, doprecyzowanie przypadku użycia `przebieg rozgrywki`

Diagram przedstawiający akcje, które są możliwe do wykonania w trakcie rozgrywki.

![png](https://i.imgur.com/iez0DGS.png)

## Diagram klas

Diagram przedstawiający klasy, funckje i zmienne użyte do stworzenia modułu.

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

Można tutaj zauważyć wywołania odpowiednich metod, które służą do wysładnia danych na z rozgrywki do modułu przechowywania danych i rankingu.

![png](https://i.imgur.com/enHSYoq.png)

## Diagram czynności

Diagram w uproszczony sposób pokazuje z jakie czynności może podjąć gracz do zakończenia rozgrywki

![png](https://i.imgur.com/K9nB1i4.png)

## Diagram maszyny stanowej

Diagram opisuje różne stany jakie może przyjść system gry podczas wyboru poziomu trudności

![png](https://i.imgur.com/GgCRa9Q.png)

## Diagram komponentów

Diagram opisuje relacje między komponentami systemu

![png](https://i.imgur.com/IxNoikL.png)

## Diagram pakietów

Diagram opisuje uproszczone zgrupowanie funkcji i klas w pakiety, oraz które z powstałych w ten sposób grup ze sobą współpracują.

![png](https://i.imgur.com/eHxXzW7.png)

## Diagram przeglądu interakcji

Diagram opisuje jak przegląd uproszczonych diagramów interakcji dotyczących strzału

![png](https://i.imgur.com/JeHk3Ga.png)

## Diagram strukturalny

Diagram opisuje relacje klas. Klasą centralną jest klasa "Game". Jest ona bezpośrednio powiązana z klasami: 'Enemy', 'Player', 'Rubbish', 'Laser'.
 
- Relacja z `Player` oraz `Difficulty` oznacza, że gra zawiera jedną instancje klasy gracza (1 do 1), oraz poziomu trudności
- Relacja z `Enemy`, `Rubbish` i `Laser` oznacza, że w grze mogą być generowanie różne rodzaje śmieci i laserów (1 do 0...*)
- Relacja z `LaserType`, `RubbishType` i `EnemyType` ozancza, że odpowiednie instancje klasy z nic korzystające używają jedynie jednego typu (enumeration) natomiast dany typ może być używany przez wiele instancji (1 do 0...*)

![png](https://i.imgur.com/ETVTRVm.png)

## Diagram harmonogramowania

Diagram harmonogramowania przedstawia stany gry w czasie

![png](https://i.imgur.com/n1khsnf.png)

W przypadku tego modułu niemożliwe jest dokładne rozłożenie konkretnych stanów w czasie. Jest to spowodowane brakiem możliwości pomiaru ze względu na krótki czas trwania danych interwałów czasowych. Nie możliwe jest również zmierzenie jak długo gracz będzie znajdował się w danej cześći programu ze względu na to, że może wykonać daną akcje po sekundzie, a możę zostawić on gre w danym stanie na kilka godzin.

## Realizacja założeń i wymagań

Zaprojektowany moduł gry spełnia założenia oraz wyamgania nałożone na niego przy prezentacji początkowej. 

- Gracz otrzymuje punkty za postęp w grze a ilość punktów jest normowana przez poziom trudności.
- Moduł jest połączony z innymi modułami projektu
- Gra posiada 3 poziomy trudności
- Stworzona została instrukcja możliwa do wyświetlenia w menu gry
- Gra posiada prostą w obsłudze nawigacje
- Kryteria wygranej i przegranej zostały zaimplentowane zgodnie z założeniamai
- Odpady niszczone są jedynie odpowiednim rodzajem lasera
- Na strzelanie nałożona jest restrykcja w postaci poziomu przegrzania

## Realizacja powiązań z innymi modułami

Moduł powiązany jest z innymi modułami projektu. W module przechowywania danych przechowywane są postępy gracza w grze stworzonej na rzecz tego modułu. W module rankingu przechowywane są wyniki graczy.
W przypadku obu modułów dane są do nich przesyłane jedynie w przypadku zakończenia gry w sposób pomyślny. Połączenie z modułem aplikacji realizowane jest przez system stworzony przez architektów grupy i nawigowanie jest obługiwane przez główną aplikacje.

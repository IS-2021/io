# Spożywanie produktów ekologicznych

Autorzy: Joanna Sadowska, Maciej Obszański

## Cel i założenia:
### Cel
Przekonanie dzieci i młodzieży do sięgania po ekologiczne produkty i dbania o swoje zdrowie dzięki ciekawej grze zręcznościowej.
### Założenia
- Dostęp do gry realizowany jest dzięki stronie internetowej (tylko dla zalogowanych użytkowników)
- System śledzi postęp w grze
- Gra skupia się na wybraniu ekologicznego produktu spośród wyświetlonych na ekranie
- Gra posiada 3 poziomy trudności, które można wybrać przed rozpoczęciem rozgrywki. Z każdym kolejnym poziomem użytkownik ma mniej czasu na reakcję, a liczba wyświetlanych produktów jest większa
- Gracz będzie sterował myszką żeby ukończyć rozgrywkę
- Gracz za skończenie gry nagradzany jest punktami
- Grę można zresetować w celu poprawienia swojego wyniku
- Gry nie można zatrzymać
- Po skończonej rozgrywce, wynik jest zapisywany do bazy danych
## Wymagania:
### Funkcjonalne
- Możliwość wyboru poziomu trudności gry
- Gracz może kliknąć prawidłowy produkt (eko) i uzyskać za to punkty
- Gracz może kliknąć nieprawidłowy produkt (niezdrowy) i nie uzyskać za to punktów
- W zależności od poziomu trudności i szybkości wybrania prawidłowego produktu, użytkownik dostaje więcej punktów
- Możliwość zresetowania gry po jej zakończeniu w celu poprawienia swojego wyniku
- Podczas rozgrywki system mierzy czas od pojawienia się produktów do kliknięcia produktu
- Po zakończeniu rozgrywki wynik gracza jest wyświetlany na ekranie, a po zatwierdzeniu zapisywany w bazie danych
### Niefunkcjonalne
- Gra posiada 3 poziomy, a każdy kolejny jest trudniejszy od poprzedniego
- Gracz może kliknąć tylko jeden z produktów wyświetlonych na ekranie
- Ukończenie danego poziomu wymaga ukończenia 7 rund
- Gra posiada czytelny i miły dla oka interfejs
- Gracz odblokowuje kolejne poziomy trudności sekwencyjnie, po pomyślnym ukończeniu poprzedniego poziomu (uzyskania odpowiedniej liczby punktów)
- Gracz może wybrać produkt tylko wtedy, gdy są one widoczne na ekranie
- Dostęp do gry ma tylko użytkownik zalogowany
- Użytkownik ma maksymalnie 3 sekundy na wybór produktu, inaczej dostaje 0 punktów
- W zależności od poziomu trudności na ekranie pojawia się 3/4/8 produktów
## Zmiany wymagań
- Po skończonej rozgrywce gracz może zresetować grę bez użycia monet w celu poprawienia swojego wyniku
- Konkurowanie z innymi graczami odbywa się na zasadzie pozycji w rankingu, a nie na pojedynku
- Użytkownik ma nielimitowany czas na kliknięcie produktu – powyżej 2 sekund dostanie punkty jedynie za wybranie prawidłowego produktu (punkty za czas reakcji przyznawane są jedynie za przedział od 0 do 2 sekund)
- Nie ma możliwości zrestartowania gry po zakończonej rozgrywce – na końcu wyświetlany jest alert z przyciskiem OK, po naciśnięciu go wynik jest przesyłany do bazy danych, a strona odświeżania (tutaj użytkownik może zagrać jeszcze raz)
## Diagram przypadków użycia
![Przypadki użycia](https://i.imgur.com/tDMGozW.png){ width="900" }
## Powiązania z innymi modułami
![Inne moduły](https://i.imgur.com/PVHiei7.png){ width="900" }
## Diagram klas
![Klasy](https://i.imgur.com/t08EDCB.png){ width="900" }
## Scenariusz przypadku użycia: Zapisanie wyniku użytkownika w bazie danych i przeładowanie strony
![Scenariusz zapisanie](https://i.imgur.com/ZjI0JCI.png){ width="900" }
## Diagram sekwencji - zapisanie wyniku w bazie danych i przeładowanie strony
![Sekwencji zapisanie](https://i.imgur.com/YPCrUOO.png){ width="900" }
## Scenariusz przypadku użycia: Przebieg gry
![Scenariusz przebieg](https://i.imgur.com/MQb0JuS.png){ width="900" }
## Diagram sekwencji - Przebieg gry
![Sekwencji przebieg](https://i.imgur.com/x87qtUv.png){ width="900" }
## Diagram maszyny stanowej
![Maszyna stanowa](https://i.imgur.com/HGWsLhx.png){ width="900" }
## Diagram czynności
![Czynności](https://i.imgur.com/hQ6Jkxo.png){ width="900" }
## Diagram pakietów
![Pakiety](https://i.imgur.com/Qlh0qn4.png){ width="900" }
## Diagram przeglądu interakcji
![Przegląd interakcji](https://i.imgur.com/AJ85Cgn.png){ width="900" }
## Diagram strukturalny
![Struktura](https://i.imgur.com/MByKxvZ.png){ width="900" }
## Diagram harmonogramowania
![Harmonogramowanie](https://i.imgur.com/rTrGb8P.png){ width="900" }
## Realizacja założeń i wymagań
Zaprojektowana przez nasz zespół gra spełnia postawione przez architektów założenia i wymagania, co można zaobserwować na utworzonych wyżej diagramach. Dostęp do gry jest realizowany przez stronę internetową, dane użytkowników będą przechowywane w bazie danych, po zakończeniu gry wynik zostanie przesłany do bazy danych. Gracz jest nagradzany za skończenie gry ilością punktów adekwatną do jego wyników. W grę można ponownie zagrać w celu poprawy wyniku. Gra posiada 3 poziomy trudności, z których każdy kolejny jest coraz trudniejszy i oferuje więcej punktów za rozgrywkę. Na końcu gry gracz jest informowany o swoim wyniku i może kliknąć „ok” na alercie w celu zakończenia gry i przesłania swojego wyniku do bazy danych. Gra działa na podobnej zasadzie do wielu innych gier badających czas reakcji i ma przyjemny i zrozumiały interfejs, dzięki czemu jest prosta i odpowiednia dla graczy w każdym wieku oraz dostarcza minuty satysfakcjonującej rozgrywki. 
## Realizacja powiązań z innymi modułami
Projekt jest powiązany z innymi modułami. W module rankingu przechowywane są wyniki danego gracza, a jego najlepszy wynik wyświetlany jest w module rankingu. W przypadku poprawienia wyniku i przesłania go do bazy danych, poprawiony wynik jest wyświetlany w module rankingu. Dzięki modułowi aplikacji gracz ma dostęp do danej gry, a z jej poziomu może wyjść do interfejsu aplikacji. Postęp gracza jest zapisywany w module przechowywania danych.
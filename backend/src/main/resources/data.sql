INSERT INTO greengame.game (game_id, name, description, image_slug_name) VALUES
(1, 'Recykling', '', 'recykling'),
(2, 'Oszczędzanie wody', '', 'oszczedzanie-wody'),
(3, 'Oszczędzanie energii', '', 'oszczedzanie-energii'),
(4, 'Ponowne wykorzystanie', '', 'ponowne-wykorzystanie'),
(5, 'Korzystanie z transportu publicznego', '', 'korzystanie-z-transportu-publicznego'),
(6, 'Prawidłowe pozbywanie się odpadów', '', 'prawidlowe-pozbywanie-sie-odpadow'),
(7, 'Spożywanie produktów ekologicznych', '', 'spozywanie-produktow-ekologicznych');


INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (1, 'Zielony Strzelec', 'Zapewnij ochronę planecie, strzelając do nadlatujących odpadów kolorowym laserem. Każdy kolor oznacza inny rodzaj śmieci. Sprawdź swoją precyzję i zrównoważ swoje siły w walce z zanieczyszczeniem.', 1, 3);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (2, 'Recyklingowa Furia', 'Zagrożenie nadchodzi! Wybierz jedną z trzech broni: żółtą, niebieską lub zieloną, aby zneutralizować fale nadchodzących odpadów. Twoje wybory wpływają na skuteczność walki - bądź sprytny w recyklingu!', 1, 4);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (3, 'Labirynt Wodny', 'Wydostań się z labiryntu rurociągów, zanim woda wyczerpie się! Zamykaj i otwieraj zawory, aby utrzymać płyn w ruchu. Twój cel to dotrzeć do końca, unikając utraty kropli wody. Oszczędność to klucz!', 2, 2);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (4, 'Zachowaj Żródło', 'Twoje miasto desperacko potrzebuje wody! Steruj przepływem wodociągu, minimalizując straty wody. Każda kropla ma znaczenie - oszczędzaj zasoby, aby zapewnić dostęp do wody dla wszystkich.', 2, 5);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (5, 'Energia w Twoich Rękach', 'Klikaj na elementy obwodu, aby dostarczyć energię do urządzeń. Uważaj, aby nie przeciążać systemu! Zmierz się z wyzwaniem zoptymalizowania obwodu, by jednocześnie zaspokoić potrzeby energii.', 3, 1);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (6, 'Ognisty Zasilacz', 'Przeżyj wyścig z czasem! Zadanie polega na dostarczeniu energii do urządzeń elektronicznych, zanim bateria wyczerpie się. Optymalizuj ścieżki obwodów, by uniknąć utraty energii i oszczędzić zasoby.', 3, 4);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (7, 'Ochrona Odnawialna', 'Wzmocnij swoją obronę! Wykorzystaj odnawialny kompost do zakupu roślin, które bronią przed falami śmieci. Zadbaj o ekosystem, wybierając mądrze, które rośliny zasadzić.', 4, 2);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (8, 'Bitwa Kompostu', 'Wpadłeś w pułapkę śmieci! Ocal się, budując obronę z kompostu. Każdy rodzaj śmieci to inny rodzaj przeciwnika - zastosuj swoje kompostowe umiejętności, aby wyjść z tego zwycięsko!', 4, 3);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (9, 'Skacz na Fali', 'Wsiądź do autobusu i ruszaj w miasto! Zbieraj pasażerów, omijaj przeszkody i dostarcz ich na czas. Im więcej osób zabierzesz, tym więcej punktów zdobędziesz. Zostań mistrzem transportu publicznego!', 5, 5);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (10, 'Przeszkody na Torach', 'Twój autobus zmierza w kierunku pełnego wyzwań. Omijaj przeszkody, zbieraj pasażerów i utrzymuj równowagę między prędkością a bezpieczeństwem. Każda podróż to szansa na zdobycie punktów!', 5, 2);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (11, 'Sortowanie Superstar', 'Rozłóż śmieci na frakcje, unikając błędów! Zbieraj punkty za poprawne sortowanie i spraw, aby odpady trafiły tam, gdzie powinny. Twoje umiejętności sortowania będą kluczowe!', 6, 3);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (12, 'Śmieciowy Sprint', 'Ratuj planetę przed śmieciami! Wprowadź poprawne odpady do odpowiednich kontenerów, zanim czas dobiegnie końca. Szybkie myślenie i precyzja są kluczowe w tym wyzwaniu utrzymania porządku!', 6, 1);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (13, 'Ekologiczna Wybór', 'Zjedz zdrowo i ekologicznie! W tej grze point-and-click musisz wybrać produkty, które są ekologiczne. Zdobywaj punkty za trafne wybory i dbaj o zdrowie swojego awatara.', 7, 4);
INSERT INTO greengame.daily_task (`daily_task_id`, `name`, `description`, `game_id`, `coins_reward`) VALUES (14, 'Smak Ekologii', 'W sklepie pełnym produktów musisz wybierać te, które wspierają ekologię. Unikaj produktów przetworzonych i postaw na zdrową żywność. Zdobądź punkty za odpowiednie decyzje żywieniowe!', 7, 5);
## Meny Test
Formål:

- Kontrollere at du kan starte spillet
- Kontrollere at du kan avslutte spillet
Test 1:

1. Kjør programmet fra Main.java
2. Klikk på knappen som viser 'Play'
3. Trykk på Esc-knappen på tastaturet ditt
Resultat:Å klikke på spill-knappen skal starte spillet
Å trykke på Esc skal avslutte spillet

## Test 2:

1. Kjør programmet fra Main.java
2. Klikk på knappen som viser 'Quit'
Resultat:
Å klikke på avslutt-knappen skal avslutte spillet
---

## Test spillerbevegelse
Formål:

- Kontrollere at spilleren kan bevege seg til venstre
- Kontrollere at spilleren kan bevege seg til høyre
- Kontrollere at spilleren kan hoppe
- Kontrollere at spilleren påvirkes av tyngdekraften
- Kontrollere at spilleren kan angripe

Test:

1. Kjør programmet fra Main.java
2. Trykk på 'Spill'-knappen
3. Trykk på tasten 'd'
4. Trykk på tasten 'a'
5. Trykk på tasten 'w'
6. Trykk på tasten 's'
7. Trykk på venstre museknapp
Resultat:
Å trykke på tasten 'a' får spilleren til å bevege seg til venstre
Å trykke på tasten 'd' får spilleren til å bevege seg til høyre
Å trykke på tasten 'w' får spilleren til å hoppe
Å trykke på tasten 's' får spilleren til å gå fortere ned igjen mot bakken
Etter hopping kommer spilleren ned til bakken igjen
Å trykke på venstre museknapp starter angrepsanimasjonen
---


## Test Fiendekollisjon
Formål:

   Kontrollere at kollisjon med en fiende påvirker spillerens helse og dør ved tap av alle tre liv
   Kontrollere at hvis man angriper fiende 2 ganger, så dør den
Test:
- Kjør programmet fra Main.java
- Beveg spilleren nærme en fiende
- Angrip fiende 2 ganger ved å trykke left click i nærheten av den
- Observer spillerens helse(hjertene i høyre hjørne) eller liv etter kollisjonen.
 Resultat:
- Fiende dør etter å ha blitt truffet 2 ganger
- Spillerens helse reduseres
---
# Rapport – innlevering 4
**Team:** *Snille menn* – *Runar, Tommy, Viktor, Marius*

#### Runar: TEAMLEAD, Kommunikasjonsansvarlig og Obligskribent
Ansvar for at vi held oss til tidsfrister og at oppgåver blir kommunisert innad i gruppa. Tek avgjerder for gruppa dersom nødvendig. 

#### Tommy: Testansvarlig, Klassediagramskapar	
Passar på at testar blir skrive, og at dei fungerer som dei skal. 

#### Marius: Produktutvikler
Ansvar for å drive produktet framover slik at vi møter produksjonskrav. 

#### Viktor: Kunstansvarleg og Mapdesigner
Står for animering og teksturar. Skal også ha ansvar for mapdesign.

### Har vi erfaringer team-messig eller mtp prosjektmetodikk som er verdt å nevne? 	
Vi har lite erfaring team-messig, så nei. 

### Prosjektverktøy: 
* Vi har holdt oss til bruk av discord for å gi beskjedar og holde møter. 
* Github for å samkøyre prosjektet.

### Liker vi valgene vi har tatt underveis?
Stort sett, ja. Vi har definitivt lært ein del om viktigheten av å planlegging av prosjekt. 

### Hvordan er gruppedynamikken? Uenigheter?
Vi får gjort det vi må, og det er ikkje ueinigheit. 

### Hvordan har kommunikasjon fungert for oss?
Kommunikasjonen har våre tilfredsstillande frå dei som har tatt del i prosjektet. Over tid har det vist seg å vere vanskeleg å ha med alle som var med i gruppa i utgangspunktet. 

### Kort retrospektiv om hva som er bra og hva som kan forbedres. Hva har vi fått til det nå? 
Spelet er ferdig. Vi har laga eit spel som fungerer. 

## Konsept

Vi har laga ein platformer uten noko inspirasjon. Det er eit kart, der du skal klare å hoppe opp til ei prinsesse uten å dø. På vegen opp må ein passe seg for å ikkje dette ned frå mappen, missilar og spøkelse. Spelaren kan drepe spøkelse
og øydelegge missilar ved å slå dei. Ein kan hoppe to gonger på rad så sant ein ikkje er borti ein slope. 

## Velg og tilpass en prosess for teamet

Vi har i utgangspunktet hatt eit møte kvar veke (tirsdag kl 10:00 eller 12:00), og ved behov har vi hatt eit ekstra møte i veka.
Så har vi brukt discord-chatten vår til å planlegge/endre tidspunkt om det ikkje skulle passe for nokon og når det var behov for fleire møter.
Discord har også blitt brukt til å dele nyttig informasjon og beskjeder.

Vi har fordelt arbeidsoppgaver i møtene utifra kva som møtte gjerast for å drive prosjektet framover.

Alt av kode og dokumenter er samla på gitlab.
Dokumentasjon som møte-referat(team-meetings.md) og klassediagram(klassediagram.png) er i 'doc' folderen.

Det har blitt en del individuelt arbeid siden vi har hatt ganske få møter, men når vi har hatt møter har vi tatt opp problemer med koden og løst det felles.
Dette har gitt gruppemedlemmene mye frihet og fleksibilitet til å arbeide med prosjektet til eget tidsskjema frem til kommende møter.
Samtidig har dette gjort prosjektet mer uoversiktelig enn det trengte å være.

## Beskrivelse av innholdet i spillet

- Spelfigur som kan bevege seg todimensjonelt i alle retningar.

- Todimensjonal verden:
 - Sidescrolling-perspektiv - Vi ser inn på karakteren vår frå sida
 - Eit område spelaren kan bevege seg innafor, avgrensa av veggar
 - Laga i eit rutenett oppdelt i blokker
 - Enkelte blokker vil ha ei innvirkning på spelaren si evne til å bevege seg (slopes)

- Fiendar som beveger seg, følg og kan skade spelaren ved berøring

- Projectiles som kan skade spelaren

- Spelaren kan skade fiendar ved å slå dei med sverdet sitt

- Spelaren kan falle ut av bana og dø

- Spelaren vinn så sant han kjem seg til prinsessa

- Spelet har ein start meny, der du kan:
 - Starte eit nytt spel
 - Avslutte spelet

## Teknisk dokumentasjon

Kodestrukturen er vist i klassediagrammet: Klassediagram.png



Screens inneholder alle skjermene:

    MainMenyScreen
    InstuctionsScreen
    CreditScreen
    GameOverScreen
    Shop
    View

View klassen er hovedskjermen til spillet. Det er her verdenen tegnes med spiller, fiender, prosjektiler og alle andre objekter. Vi har ikke klart å skille modellen og view helt fordi mange av klassene er avhengig av å ha et TiledMap og det var vanskelig å skille dem fullstendig fra hverandre i libGDX. Men vi har flyttet tegningen av map-et ut av map-klassene og til view slik at vi kan lage map og alle objektene som trenger map uten å måtte tegne map på skjermen. På den måten har vi kunnet teste mye mer av koden med JUnit tester.

View henter inn MonsterInterface, PlayerInterface, AbstractGameObject, ProjectileInterface, MapInterface og ItemImpl som den bruker til å tegne objektene på skjermen. I view lager vi også MonsterFactories som lager fiendene (Monsters).

AbstractGameObject inneholder alle feltvariablene og metodene som er felles for Monster, Player og Prosjectiles. I tillegg implementerer klassene et interface som beskriver hvordan implementasjonen deres. På den måten kan vi gi interfaces til view i stedet for klassene. Dette har gjort det veldig enkelt å legge til nye fiender og nye prosjektiler.

MapInterface og ItemImpl beskriver henholdsvis hva et map og en item skal være. I spillet har vi bare en item: HealthPotion, men stukturen gjør det enkelt å legge til flere items senere hvis vi ønsker det.

Controlleren tar imot inputs fra brukeren og sender det til SouthGame og View slik at vi kan oppdatere modellen.

# Avsluttande rapport på prosjektet og refleksjonar

Dette prosjektet har våre utfordrande på mange måtar, men vi har klart å produsere eit spel som fungerer godt. Vi har fått laga ein platformer, som vi planla i utgangspunktet. Spelet har mange flotte funksjonar og mekanikkar som vi er 
nøgde med. 
Eg (Runar) har som teamleiar og kommunikasjonsansvarleg opplevd gruppa som veldig grei og vi har hatt få til ingen konflikter. Dette er jo flott for harmonien i gruppa, men eg skulle på ein måte ynskje vi hadde diskutert litt meir.
Meiningane til medlemmar ikkje kom ut gjennom verbal kommunikasjon, men heller vart kommunisert gjennom arbeidet dei gjorde. Dette fekk konsekvensar etter kvart, spesielt for testing av koden. 
Det var på eit tidspunkt tydeleg at vi ikkje gjorde nok for å utvikle spelet vårt. I staden for å ta opp denne frustrasjonen og prøve å utgreie litt oss imellom, begynte enkelte å jobbe med å skrive kode sjølv. 
At koden blir skrive av nokon andre er jo veldig deilig for resten av oss, men det fører til ujamn arbeidsfordeling og gir prosjektet ein uregulert kodestil. Igjen, det skal vere klart at vi ikkje kan klandre dei som skreiv koden, sidan
dei berre har handla som følgje av mangel på handleevne hos resten av gruppa. Vi er dessutan veldig nøgde med sluttproduktet, og det skal seiast at vi klarte å jamne ut arbeidsfordelinga seinare i prosjektet. 

I retrospekt ser eg på dette som ein feil frå mi eiga side og resten av gruppa. Eg skulle ynskje vi tok oss meir tid tideleg i prosjektet til å legge ein klar plan for framgangsmetoden for kva vi skulle gjere. Samstundes vart det tydeleg
at enkelte gruppemedlemmar ikkje hadde tenkt å ta del i prosjektet i nokon stor grad, og at dette har hatt ei rimeleg stor innvirkning på evna til resten av gruppa til å gjere deira oppgåver. 

## Test-problemet

Som skrive tidelegare vart tideleg kode skrive i stor grad av eitt gruppemedlem. Han har gjort ein veldig god jobb med funksjonalitet, og gitt spelet liv. Samstundes vart det tydeleg når vi skulle lage testar seinare i prosjektet at 
koden var skrive på ein måte som gjorde å lage t.d. headless application til eit grådig styr. Resultatet er at vi ikkje har klart å oppfylle testkravet av oppgåva.
Dette kan vi nok ein gong klandre oss sjølve for. Vi satt ikkje opp testar underveis (som var anbefalt), dette fekk vi svi for i etterkant. Dette kom av rein latskap, sidan vi ikkje orka å finne ut av korleis vi skulle sette opp Maven tideleg i prosjektet. 

## Lærdom

Dette faget har gitt innsikt i gleden og utfordingane med gruppeprosjekt, og sånn sett gitt verdifull erfaring. Vi har merka innvirkninga av dårleg kommunikasjon. Det er også tydeleg at gode arbeidsrutiner og ein klar retning er essensielt
for eit prosjekt. 

## Brukerhistorier, akseptansekriterier og arbeidsoppgaver til MVP


		
**1. Som spelar ynskjer eg å sjå spelbrettet på skjermen slik at eg kan sjå kor eg kan bevege karakteren min.** 
* Akseptansekriterier:
	1. Spelet startar og viser spelbrettet
		
* Arbeidsoppgaver:
	1. Vi må lage et brett under maps
	2. Brettet må visast på skjermen
	
		
**2. Som spelar ynskjer eg å sjå karakteren min på skjermen**
* Akseptansekriterier:
	1. Spelaren er synleg på spelbrettet
	
* Arbeidsoppgaver:
	1. Vi må ha en spiller-klasse
	2. Karakteren må ha en posisjon
	3. Karakteren må teiknast på skjermen
		
**3. Som spelar ynskjer eg å bevege karakteren min ved å trykke på tastene slik at jeg kan unngå at fiender skader meg.**
* Akseptansekriterier:
	1. Spelaren skal kunne bevege seg på skjermen i åtte retninger ved å bruke tastene "w", "a", "s", "d"

* Arbeidsoppgaver:
	1. Vi må ha en actionListener som registrerer tastetrykk
	2. Vi må sette opp controller slik at spilleren beveger seg opp, venstre, ned og høgre.
	3. Modellen må oppdateres med den nye posisjonen
	4. Vi må teikne karakteren i den nye posisjonen
		
**4. Som spelar ynskjer eg at det er lett å skille områda spelaren kan bevege seg på frå hindringer og veggar slik at det er lett å se hvor jeg kan bevege meg på brettet**
* Akseptansekriterier:
	1. Brettet skal vises med vegger og hindringer
	2. Karakteren skal ikke kunne passere vegger og hindringer
	
* Arbeidsoppgaver:
	1. Spillbrettet må vise ulike tiles
	2. Vi må skille mellom tiles karakteren kan bevege seg over og tiles som karakteren ikke kan passere
	3. Karakteren, vegger og hindringer må ha kollisjonsbokser
	4. Vi må sørge for at karakteren har lov til å flytte seg i riktig retning.
	5. Modellen må ha informasjon om de ulike tiles-ene og hvor de er.
	6. View må vite hva som skal tegnes hvor og hvordan tiles ser ut

**5. Som spelar vil eg ha ein gameover og startmeny slik at eg kan starte spelet på nytt dersom eg dør.**

* Akseptansekriterier:
	1. Vi har en startmeny der spilleren kan starte spelet på nytt.
	2. Vi har en Active Game skjerm, som er skjermen der spillet kjører.
	3. Når spelaren dør kjem han til Game Over skjermen og kan starte spelet på nytt.
	
* Arbeidsoppgaver:
	1. Lage ein Startmeny
	2. Ha ein keyListener som registrerer når spelaren trykker på start
	3. Lage ein metode som registrerer når spelaren er død/har null hit points.


**6. Som spelar ønsker jeg at spillet har fiendar, spelet er kjedeleg utan.**
* Akseptansekriterier:
	1. Spelet må ha ein fiende som visast på skjermen
	

* Arbeidsoppgaver:
	1. Vi må ha et interface for fiender
	2. Vi må ha ein abstrakt klasse som utvider interfacet og implementerer alle metodene som er felles for alle fiendene
	3. Vi må ha ein(eller flere) fiende klasse(r) 
	4. Ein fiende må ha ein startposisjon
	5. Ein fiende må ha ein hitbox
	6. Fienden må ha eit bevegelsesmønster
	7. Fienden må teiknast på skjermen

**7. Som spelar ynskjer eg at spelet har lydar for å gjere det meir interessant**
* Akseptansekriterier:
	1. Tilpassa lydar må spele av knytta til handlingar gjort i spelet
	

* Arbeidsoppgaver:
	1. Vi må ha lydfiler
	2. Vi må ha ein lydspelar-klasse
	3. Klassen og funksjonane må kunne kallast på i andre klassar
	4. Rett lyd må spelast av i forhold til kva som skjer på skjermen

**8. Som spelar ønsker eg at spelet har meir utfordrande baner**
* Akseptansekriterier:
	1. Banene må vere større og gi spelaren ei utfordring
	

* Arbeidsoppgaver:
	1. Vi må lage større baner med større utfordringar
	2. Banene må teiknast inn

**9. Som spelar ønsker eg å kunne bruke powerups**
* Akseptansekriterier:
	1. Spelaren må kunne få nye eigenskapar ved å gjere noko i spelet
	

* Arbeidsoppgaver:
	1. Legge til objekt som vil endre variablar knytta til spelaren si oppleving av spelet

# Krav og spesifikasjon

1. Vise et spillebrett
2. Vise spiller på spillebrett
3. Flytte spiller (vha taster e.l.)
4. Spiller interagerer med terreng
5. Vise fiender/monstre; de skal interagere med terreng og spiller
6. Spiller kan dø (ved kontakt med fiender, eller ved å falle utfor skjermen)
7. Mål for spillbrett, å komme seg til enden av bana
8. Nytt spillbrett når forrige er ferdig
9. Start-skjerm ved oppstart / game over

### Bugs
* Footsteps spelar opp på kvarandre



### Hvordan styre karateren i spillet
* Du styrer karateren med "w", "a", "s", "d"

## Produkt og kode:
Dette har vi ordna sidan sist:
* Mindre endringar i kode for å gjere den betre å jobbe med
* Gjort koden meir lesbar
* Lagt til testar
* Fjerna unødvendig kode

### Lisensar

Lisensar ligg i license.md


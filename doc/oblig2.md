# Rapport – innlevering 2
**Team:** *Snille menn* – *Runar, Karsten, Tommy, Viktor, Marius, Borgar*...

### Hvordan fungerer rollene i teamet? 
Rollene fungerer OK. Det har våre litt mangel på kommunikasjon frå enkelte medlemmar. Difor har vi ikkje fått tatt dei forskjellige rollene i bruk. Det er sannsyneleg at vi må lage til nye/modifisere rollene.

#### Runar: TEAMLEAD, Kommunikasjonsansvarlig og Obligskribent
Ansvar for at vi held oss til tidsfrister og at oppgåver blir kommunisert innad i gruppa. Tek avgjerder for gruppa dersom nødvendig. 

#### Karsten: GitInator 
Har orden på Git dersom nokon har problem/spørsmål angåande Github. 

#### Borgar: Møtedokumentasjonsansvarlig
Skal loggføre møter og skrive ned evt. avgjerder som blir gjort i desse.
	
#### Tommy: Testansvarlig, Klassediagramskapar	
Passar på at testar blir skrive, og at dei fungerer som dei skal. 

#### Marius: Produktutvikler
Ansvar for å drive produktet framover slik at vi møter produksjonskrav. 

#### Viktor: Kunstansvarleg og Mapdesigner
Står for animering og teksturar. Lager desse sjølv. (Imponerande) Skal også ha ansvar for mapdesign.

### Trenger vi andre roller?


Produktutviklar:
Ein som tek ansvar for at koden er funksjonell, lett å lese og lett å skrive.

Marius har tatt på seg ansvaret for dette gjennom hardt arbeid. Gratulerer med den nye posisjonen!

Klassediagramskapar:
Ein som kan ordne med klassediagram som skal lagast for kvar oblig.

Tommy ordnar dette. Gratulerer med den nye posisjonen!

Obligskribent:
Ein som har hovudansvar for å skrive obligtekstane.

Runar ordnar dette. Gratulerer med den nye posisjonen!

### Har vi erfaringer team-messig eller mtp prosjektmetodikk som er verdt å nevne? 	
Vi har lite erfaring team-messig, så nei. 

### Prosjektverktøy: 
* Vi har holdt oss til bruk av discord for å gi beskjedar og holde møter. 
* Github for å samkøyre prosjektet.

### Liker vi valgene vi har tatt underveis?
Ja. Vi er einige om kva retning spelet skal ha reint teknisk. Det virkar å vere lite input på design enn så lenge, så dette har Viktor som oppgåve å ordne. 

### Hvordan er gruppedynamikken? Uenigheter?
Det er få ueinigheiter, men vi må jobbe med kommunikasjonen. 

### Hvordan har kommunikasjon fungert for oss?
Kommunikasjonen er heilt middels. Vi får kommunisert det mest essensielle, men det må jobbast med responstid og utfyllande svar. Når vi kommuniserer er det ikkje teikn til dårleg stemning på noko vis.

### Kort retrospektiv om hva som er bra og hva som kan forbedres. Hva har vi fått til det nå? 
Det er noko manglande på kommunikasjon, som må forbedrast for å auke effektivitet og samkøyre gruppa. Vi har hatt ei noko ujamn arbeidsfordeling spesielt ang. kodeskrivinga som har gått mest på Marius. gNår dette er sagt skal det poengterast at han har gjort mykje av dette tideleg i prosjektet slik at vi har eit solid utangspunkt å jobbe ifrå. Vi forventar at dette jamnar seg ut når vi fortsetter å jobbe. Det er også ein del testar som er uskrive som vi må ordne. Dette må andre enn Marius ta hand om. 
Samtidig skal det seiast at vi har komt bra igong med prosjektet. Folk har mange gode/interessante idear for prosjektet. Det er potensiale for eit godt sluttprodukt dersom alle idear blir realitet.

### Bli enige om maks 3 forbedringspunkter som skal følges opp.
1. Skrive meir utfyllande på oblig enn første gong.
2. Jobbe med kommunikasjon. Dersom vi skal få mest mogeleg ut av prosjektet må vi kommunisere betre. 
3. Jamne ut arbeidsmengda med koden, passe på at alle får skrive litt kode kvar.


## Brukerhistorier, akseptansekriterier og arbeidsoppgaver til MVP (som vi har jobbet med)


		
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

Alt bortsett frå 5, 6 og 8 er oppfylt. Vi har ikkje laga fiendar endå, og ikkje laga fleire kart. Foreløpig er kontroller handtert i Player.java. Vi har tenkt å endre dette slik at det er enklare å differansiere mellom klassane. Brettet visast og oppdaterast. Spelar kan flyttast og han kan stå/krasje med terreng. Start og game over-skjerm er ferdig. Ein skal kunne dø av fiendar.


### Bugs
* Ukjent. Ingen vi har merka endå.


### Prioriteringer framover

Lage testar for å auke dekningsgrad. Ny kode som blir skrive bør bli gjort med testar som samasvarer med dette. Ordne med at karakteren sklir av skrå overflater på ein tilfredsstillande måte. Arbeide med kommuinikasjon innad i gruppa. Potensielt lage ein eigen klasse for controller for å strukturere koden betre. Vi må også diskutere/reflektere over mindre tilleggsfunksjonar som ein butikk med powerups osv. 

### Brukerhistorier, akseptansekriterier og arbeidsoppgaver til neste innlevering (foreløpig liste)

**1. Som spelar vil eg ha ein gameover og startmeny slik at eg kan starte spelet på nytt dersom eg dør.**

* Akseptansekriterier:
	1. Vi har en startmeny der spilleren kan starte spelet på nytt.
	2. Vi har en Active Game skjerm, som er skjermen der spillet kjører.
	3. Når spelaren dør kjem han til Game Over skjermen og kan starte spelet på nytt.
	
* Arbeidsoppgaver:
	1. Lage ein Startmeny
	2. Ha ein keyListener som registrerer når spelaren trykker på start
	3. Lage ein metode som registrerer når spelaren er død/har null hit points.


**2. Som spelar ønsker jeg at spillet har fiendar, spelet er kjedeleg utan.**
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


### Hvordan styre karateren i spillet
* Du styrer karateren med "w", "a", "s", "d"

## Produkt og kode:
Dette har vi ordna sidan sist:
* Det finst ein fiende og collision detection mellom fienden og spelaren. Ein kan dø dersom ein kjem i kontakt med fienden.
* Vi har lagt til en start-meny.
* Vi har lagt til en game-over-meny når ein dør.
* Vi har endra bane og gjort den større

manuell testing og brukermanualer ligger i readme.md filen.
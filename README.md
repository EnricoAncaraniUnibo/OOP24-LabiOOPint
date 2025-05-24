# LabiOOPint

LabiOOPint è un gioco in Java ispirato a *Labyrinth*, dove da 2 a 4 giocatori esplorano un labirinto modificabile raccogliendo obiettivi, evitando un nemico e utilizzando PowerUp.

# Macro componenti
+ [Alocchi Simone] Menù pre-gioco, gestione PowerUp e gestione obiettivi
+ [Ancarani Enrico] Turno del giocatore, gestione dinamica del labirinto
+ [Baiano Stefano] Salvataggio/caricamento partite, interazioni personaggi-labirinto
+ [Monti Andrea] Turno del nemico, gestione dei turni generali

## Inizializzazione della partita

Il gioco supporta un numero variabile di partecipanti, da un minimo di due a un massimo di quattro giocatori. Tutti i giocatori condividono lo stesso schermo: non esiste supporto per il multiclient, e tutte le informazioni di gioco sono sempre visibili a tutti.
Nel menù pre-gioco è possibile scegliere  il numero di obiettivi da distribuire e la presenza del nemico o meno. Nel caso in cui si scelga il nemico, è possibile scegliere la difficoltà con la quale cerca di "catturare" i giocatori e può essere facile, media o difficile.
Le dimensioni del labirinto sono fisse e, in base al numero di giocatori sono calibrate per garantire partite fluide e ben leggibili, evitando eccessive dispersioni o fasi di stallo.

## Regole di gioco e dinamiche principali

All'inizio della partita, gli obiettivi sono posizionati nel labirinto e richiedono l’esplorazione e il movimento attento da parte dei giocatori. Non è possibile selezionare un obiettivo preferito: tutti competono per quelli visibili sul tabellone.

I turni si svolgono in maniera sequenziale. Durante il proprio turno, un giocatore deve prima modificare la configurazione del labirinto tramite un’azione di shift di una riga o colonna inserendo una tessera che può ruotare.
A questo punto si può procedere con il proprio movimento, che può coprire più caselle purché siano collegate da passaggi liberi. 
Una volta raggiunta la posizione desiderata, il giocatore può raccogliere un obiettivo o attivare eventuali PowerUp in suo possesso.

Il labirinto, a ogni turno, può cambiare conformazione: questa caratteristica rende ogni partita diversa e strategicamente stimolante. A complicare ulteriormente la sfida, entra in gioco un nemico che può avere difficoltà differenti e può penalizzare i giocatori colpendoli: chi viene colpito perde un obiettivo.

La partita termina quando gli obiettivi sono finiti e il giocatore che ne possiede di più è il vincitore. 
In caso di parità, viene inserito all'interno del labirinto un'altro obbiettivo in modo tale da avere un solo vincitore.

## Rendering grafico e interazione

L’interfaccia grafica del gioco è realizzata interamente utilizzando Java Swing. L’aspetto visivo del gioco è stato progettato per essere semplice ma funzionale, con una rappresentazione chiara del labirinto, dei giocatori, del nemico e degli obiettivi.
Ogni tessera del labirinto è visualizzata con una grafica che evidenzia le direzioni percorribili (muri e sentieri), e può mostrare, oltre ai giocatori e al nemico, anche i PowerUp disponibili. 

L’interazione con il gioco avviene esclusivamente tramite l'utilizzo del mouse, dove ogni bottone ha la sua funzionalità tra cui il movimento all’interno del labirinto, l'attivazione di PowerUp, la rotazione di tessere o l'interazione con i menu.

All'inizio del turno di ogni giocatore, esso deve scegliere la giusta rotazione della tessera, per poi scegliere una riga o una colonna in cui inserire quella determinata tessera, in modo da far cambiare la conformazione. Sempre con l'uso del mouse è possibile muovere il giocatore tramite quattro bottoni e quando si ha finito il proprio turno è necessario premere il bottone "end turn". 


## Salvataggio della partita

È possibile caricare una partita in qualsiasi momento. Il formato di salvataggio contiene tutte le informazioni necessarie a ripristinare lo stato completo del gioco, inclusa la configurazione del labirinto, le posizioni dei personaggi, lo stato del nemico e i PowerUp raccolti.
La partita si salva in automatico dopo ogni azione.

## Costruzione ed esecuzione

Per eseguire il progetto è necessario avere installato **Java 17 o superiore** e **Gradle**. Una volta clonato il repository, è possibile compilare il gioco tramite il comando:

```bash
./gradlew build
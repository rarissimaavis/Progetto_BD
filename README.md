# Progetto_BD :purple_heart:
Progetto realizzato per il corso di Basi di Dati 2022/23 da [rarissimaavis](https://github.com/rarissimaavis) e [teadesimone](https://github.com/teadesimone)

# Obiettivo
Creare un'applicazione per la gestione delle prenotazioni di strutture ricettive tramite una piattaforma web \
La piattaforma caratterizza le strutture ricettive registrate, le agenzie tramite cui possono essere effettuate le prenotazioni ed i clienti coinvolti nel processo di prenotazione di un viaggio \
L'introduzione di un'applicazione di questo tipo semplifica notevolmente la prenotazione di strutture ricettive evitando problematiche di overbooking

# Funzionalità 
Macro-operazioni:
- registrazione dei dati di tutte le strutture ricettive
- gestione delle prenotazioni
- analisi del prezzo della prenotazione in base alla durata del soggiorno
- ricerca delle strutture ricettive più vicine a specifici punti di interesse
- applicazione sconto sul prezzo in relazione al tipo di fidelizzazione
- ricerca delle strutture ricettive in base alla tipologia di richiesta del cliente

# Struttura
Prima parte:
- introduzione
- descrione dettagliata
- analisi della specifica
- glossario dei termini
- traduzione nello [schema ER](https://github.com/rarissimaavis/Progetto_BD/blob/main/schemi/schema_ER.svg)
- commenti sulle scelte progettuali effettuate 

Seconda parte:
- specifica del carico applicativo
- sviluppo del carico applicativo
- ristrutturazione dello schema
- [schema ER ristrutturato](https://github.com/rarissimaavis/Progetto_BD/blob/main/schemi/schema_ER_ristrutturato.svg)
- [mapping](https://github.com/rarissimaavis/Progetto_BD/blob/main/schemi/mapping.svg) verso lo schema logico relazionale
- [creazione del database](https://github.com/rarissimaavis/Progetto_BD/blob/main/database/database.sql)
- [caricamento dei dati](https://github.com/rarissimaavis/Progetto_BD/blob/main/database/insert.sql)

Terza parte:
- applicazione [piattaforma](https://github.com/rarissimaavis/Progetto_BD/tree/main/piattaforma) che comprende:
  - connessione al database
  - [implementazione delle operazioni](https://github.com/rarissimaavis/Progetto_BD/blob/main/database/query.sql) definite sul database
  - stampa dei risultati di ogni operazione

# Operazioni
Le operazioni realizzate sono le seguenti:
1. registrazione di un cliente
2. registrazione di una struttura ricettiva
3. prenotazione di una struttura ricettiva da parte di un cliente
4. attribuzione ad un cliente di una tessera con fidelizzazione premium
5. visualizzazione di tutte le strutture ricettive per città
6. visualizzazione di tutte le strutture ricettive disponibili in un periodo di tempo specificato
7. visualizzazione di tutte le strutture ricettive disponibili in un periodo di tempo specificato il cui prezzo a notte non superi i €50
8. visualizzazione del numero di prenotazioni effettuate da tutti i clienti nell'ultimo mese
9. visualizzazione dei migliori 10 clienti premium che abbiano effettuato il maggior numero di prenotazioni nelle diverse strutture ricettive
10. visualizzazione degli ostelli per i quali non è stata mai registrata una prenotazione di più di 7 giorni
11. visualizzazione delle strutture ricettive che hanno una distanza di 10km specifica da un punto di interesse
12. visualizzazione della somma degli incassi ottenuti dalle strutture ricettive registrate sulla piattaforma 
13. stampa dei dati dei clienti che hanno prenotato solo appartamenti e ostelli
14. stampa di un report che mostri i dati delle agenzie di viaggio compreso il numero totale di prenotazioni effettuate
15. stampa di un report che mostri i dati delle strutture ricettive per una specifica città e che hanno ricevuto meno di 3 prenotazioni
16. stampa di un report che mostri i dati delle prenotazioni che ancora non sono state effettuate ed il costo di ognuna di esse


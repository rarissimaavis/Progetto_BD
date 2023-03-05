use Piattaforma;

#5. visualizzazione di tutte le strutture ricettive per città

select Nome, Citta
from Struttura
order by Citta;

#6. visualizzazione di tutte le strutture ricettive disponibili in un periodo di tempo specificato

select distinct Composizione.*
from Composizione
where (Composizione.TipoStanza, Composizione.NumOspitiStanza, Composizione.Hotel) not in 
	(select Scelta.TipoStanza, Scelta.NumOspitiStanza, Prenotazione.Struttura
    from Scelta, Prenotazione
	where Scelta.Prenotazione = Prenotazione.Codice
    and ((DataCheckIn > '2023-03-10' and DataCheckIn < '2023-06-10') 
		or (DataCheckOut < '2023-06-10' and DataCheckOut > '2023-03-10')
        or (DataCheckIn < '2023-03-10' and DataCheckOut > '2023-06-10')));

select Struttura
from Ostello
where Struttura not in 
	(select Struttura
    from Prenotazione
	where ((DataCheckIn > '2023-03-10' and DataCheckIn < '2023-06-10') 
		or (DataCheckOut < '2023-06-10' and DataCheckOut > '2023-03-10')
        or (DataCheckIn < '2023-03-10' and DataCheckOut > '2023-06-10')))
or (select count(NumOspitiTotali) 
	from Prenotazione
	where Prenotazione.Struttura = Ostello.Struttura
    and ((DataCheckIn > '2023-03-10' and DataCheckIn < '2023-06-10') 
		or (DataCheckOut < '2023-06-10' and DataCheckOut > '2023-03-10')
        or (DataCheckIn < '2023-03-10' and DataCheckOut > '2023-06-10'))
    group by Prenotazione.Struttura) < Ostello.NumPostiLettoTotali
union
select Struttura
from Appartamento
where Struttura not in 
	(select Struttura
    from Prenotazione
	where ((DataCheckIn > '2023-03-10' and DataCheckIn < '2023-06-10') 
		or (DataCheckOut < '2023-06-10' and DataCheckOut > '2023-03-10')
        or (DataCheckIn < '2023-03-10' and DataCheckOut > '2023-06-10')));

#7. visualizzazione di tutte le strutture ricettive disponibili in un periodo di tempo specificato
# il cui prezzo a notte non superi i 50 euro

select distinct Composizione.*
from Composizione, Stanza
where Composizione.TipoStanza = Stanza.Tipo
and Composizione.NumOspitiStanza = Stanza.NumOspiti
and Stanza.Prezzo <= 50
and (Composizione.TipoStanza, Composizione.NumOspitiStanza, Composizione.Hotel) not in 
	(select Scelta.TipoStanza, Scelta.NumOspitiStanza, Prenotazione.Struttura
    from Scelta, Prenotazione
	where Scelta.Prenotazione = Prenotazione.Codice
    and ((DataCheckIn > '2023-03-10' and DataCheckIn < '2023-06-10') 
		or (DataCheckOut < '2023-06-10' and DataCheckOut > '2023-03-10')
        or (DataCheckIn < '2023-03-10' and DataCheckOut > '2023-06-10')));

select Struttura
from Ostello
where Prezzo <= 50
and Struttura not in 
	(select Struttura
    from Prenotazione
    where ((DataCheckIn > '2023-03-10' and DataCheckIn < '2023-06-10') 
		or (DataCheckOut < '2023-06-10' and DataCheckOut > '2023-03-10')
        or (DataCheckIn < '2023-03-10' and DataCheckOut > '2023-06-10')))
or (select count(NumOspitiTotali) 
	from Prenotazione
	where Prenotazione.Struttura = Ostello.Struttura
    and ((DataCheckIn > '2023-03-10' and DataCheckIn < '2023-06-10') 
		or (DataCheckOut < '2023-06-10' and DataCheckOut > '2023-03-10')
        or (DataCheckIn < '2023-03-10' and DataCheckOut > '2023-06-10'))
    group by Prenotazione.Struttura) < Ostello.NumPostiLettoTotali
union
select Struttura
from Appartamento
where Prezzo <= 50
and Struttura not in 
	(select Struttura
    from Prenotazione
    where ((DataCheckIn > '2023-03-10' and DataCheckIn < '2023-06-10') 
		or (DataCheckOut < '2023-06-10' and DataCheckOut > '2023-03-10')
        or (DataCheckIn < '2023-03-10' and DataCheckOut > '2023-06-10')));

#8. visualizzazione del numero di prenotazioni effettuate dai clienti nell'ultimo mese

select count(*) as NumPrenotazioniUltimoMese
from Prenotazione 
where (DataCheckIn > '2023-04-01' and DataCheckIn < '2023-05-01');

#9. visualizzazione dei migliori 10 clienti premium che abbiano effettuato il maggior numero di prenotazioni 
# nelle diverse strutture ricettive

select Cliente.Nome, Cliente.Cognome, NumPrenotazioni
from Prenotazione, Cliente
where Prenotazione.Cliente = Cliente.CF
and Sconto is not null
order by NumPrenotazioni desc
limit 10;

#10. visualizzazione degli ostelli per i quali non è stata mai registrata una prenotazione di più di 7 giorni

select Ostello.Struttura
from Ostello, Prenotazione
where Ostello.Struttura = Prenotazione.Struttura
and datediff(Prenotazione.DataCheckOut, Prenotazione.DataCheckIn) <= 7;

#11. Visualizzazione delle strutture ricettive che hanno una distanza di 10km specifica da un punto di interesse;

select Nome
from Struttura, Distanza
where Struttura.Codice = Distanza.Struttura
and Distanza.Km = 10;
    
#12. Visualizzazione della somma degli incassi ottenuti dalle strutture ricettive registrate sulla piattaforma;

select Struttura, sum(PrezzoTotale) as Incassi
from Prenotazione
group by Struttura;

#13. Stampa dei dati dei clienti che hanno prenotato solo appartamenti e ostelli;

select distinct Cliente.*
from Cliente, Prenotazione, Ostello, Appartamento
where Cliente.Cf = Prenotazione.Cliente
and (Prenotazione.Struttura = Ostello.Struttura 
	or Prenotazione.Struttura = Appartamento.Struttura);

#14. Stampa di un report che mostri i dati delle agenzie di viaggio compreso il numero totale di prenotazioni effettuate;
    
select Agenzia.*, count(Prenotazione.Agenzia) as NumPrenotazioni
from Agenzia, Prenotazione
where Agenzia.Codice = Prenotazione.Agenzia
group by Agenzia;
    
#15. Stampa di un report che mostri i dati delle strutture ricettive per una specifica città e che hanno ricevuto meno di 3 prenotazioni

select Struttura.*
from Struttura, Prenotazione
where Struttura.Codice = Prenotazione.Struttura
and Struttura.Citta = 'Huangtugang'
group by Prenotazione.Struttura
having count(Prenotazione.Struttura) < 3;

#16. Stampa di un report che mostri i dati delle prenotazioni che ancora non sono state effettuate ed il costo di ognuna di esse

select * 
from Prenotazione
where DataCheckIn > '2023-01-01';
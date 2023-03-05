create schema Piattaforma;
use Piattaforma;

create table Tessera
(
	Codice char(6) primary key,
	Tipo varchar(10) not null, 
	DataDiScadenza date not null,
	PeriodoDiValidita int not null,
    check (Tipo = 'Standard' or Tipo = 'Premium')
);

create table Cliente
(
	CF char(16) primary key,
	Nome varchar(30) not null,
	Cognome varchar(30) not null,
	Telefono varchar(12) not null,
	Email varchar(30) not null,
	NumPrenotazioni int not null,
	NumPrenotazioniAttive int not null,
	CodiceTessera char(6) not null,
    check (NumPrenotazioniAttive <= 2),
    foreign key(CodiceTessera) references Tessera(Codice)
		on update cascade
		on delete restrict
);

create table Agenzia
(
	Codice char(6) primary key,
	Nome varchar(30) not null
);

create table Sito
(
	URL varchar(200) primary key,
    Agenzia char(6) not null,
    foreign key(Agenzia) references Agenzia(Codice)
		on update cascade
        on delete restrict
);

create table Struttura
(
	Codice char(6) primary key,
	Nome varchar(30) not null,
	Via varchar(30) not null,
	CAP char(5) not null,
	Citta varchar(30) not null,
	AnnoDiIscrizione year not null
);

create table Telefono
(
	Numero varchar(12) primary key,
    Struttura char(6) not null,
    foreign key(Struttura) references Struttura(Codice)
		on update cascade
        on delete restrict
);

create table Servizio
(
	Tipo varchar(30) primary key,
    Descrizione varchar(100) not null
);

create table Erogazione
(
	Struttura char(6) not null,
    Servizio varchar(30) not null,
    primary key(Struttura, Servizio),
    foreign key(Struttura) references Struttura(Codice)
		on update cascade
        on delete restrict,
	foreign key(Servizio) references Servizio(Tipo)
		on update cascade
        on delete restrict
);

create table PuntoDiInteresse
(
	Codice char(6) primary key,
	Nome varchar(30) not null,
	Indirizzo varchar(30) not null,
	Città varchar(30) not null,
	Tipo varchar(30) not null,
	Descrizione varchar(100) not null
);

create table Distanza
(
	Struttura char(6) not null,
    PuntoDiInteresse char(6) not null,
    Km float not null,
    check (Km <= 100),
    primary key(Struttura, PuntoDiInteresse),
    foreign key(Struttura) references Struttura(Codice)
		on update cascade
        on delete restrict,
	foreign key(PuntoDiInteresse) references PuntoDiInteresse(Codice)
		on update cascade
        on delete restrict
);

create table Appartamento
(
	Struttura char(6) primary key,
    Prezzo float not null,
    NumOspiti int not null,
    NumVani int not null,
    Mq float not null,
    foreign key(Struttura) references Struttura(Codice)
		on update cascade
        on delete restrict
);

create table Ostello
(
	Struttura char(6) primary key,
    Prezzo float not null,
    NumPostiLettoTotali int not null,
    foreign key(Struttura) references Struttura(Codice)
		on update cascade
        on delete restrict
);

create table Hotel
(
	Struttura char(6) primary key,
    foreign key(Struttura) references Struttura(Codice)
		on update cascade
        on delete restrict
);

create table Stanza
(
	Tipo varchar(15) not null,
    NumOspiti int not null,
    NumStanze int not null,
    Prezzo float not null,
    primary key(Tipo, NumOspiti)
);

create table Composizione
(
	TipoStanza varchar(15) not null,
    NumOspitiStanza int not null,
    Hotel char(6) not null,
    primary key(TipoStanza, NumOspitiStanza, Hotel),
    foreign key(Hotel) references Hotel(Struttura)
		on update cascade
        on delete restrict,
	foreign key(TipoStanza, NumOspitiStanza) references Stanza(Tipo, NumOspiti)
		on update cascade
        on delete restrict
);

create table TipologiaStanza
(
	Tipo varchar(15) not null,
    NumOspiti int not null,
    NumStanze int not null,
    primary key(Tipo, NumOspiti),
    foreign key(Tipo, NumOspiti) references Stanza(Tipo, NumOspiti)
		on update cascade
        on delete restrict
);

create table Prenotazione
(
	Codice char(6) primary key,
    DataCheckIn date not null,
    DataCheckOut date not null,
    NumOspitiTotali int not null,
    PrezzoTotale float not null,
    Note varchar(100),
    Sconto int,
    Cliente char(16) not null,
    Agenzia char(6) not null,
    Struttura char(6) not null,
    check (DataCheckOut > DataCheckIn),
    check (Sconto > 0 and Sconto < 100),
    foreign key(Cliente) references Cliente(CF)
		on update cascade
        on delete restrict,
	foreign key(Agenzia) references Agenzia(Codice)
		on update cascade
        on delete restrict,
	foreign key(Struttura) references Struttura(Codice)
		on update cascade
        on delete restrict
);

delimiter //
create trigger ControllaIscrizione before insert on Prenotazione for each row
begin
if (year(new.DataCheckIn) < (select AnnoDiIscrizione 
	from Struttura where new.Struttura = Struttura.Codice))
then signal sqlstate '35000' 
	set message_text = 'La Data di Check-In deve essere successiva all Anno di Iscrizione';
end if;
end //
delimiter ;

delimiter //
create trigger ControllaSconto before insert on Prenotazione for each row
begin
if (new.Cliente in (select Cliente.CF
	from Cliente, Tessera
	where Tessera.Tipo = 'Standard' and Tessera.Codice = Cliente.CodiceTessera))
then set New.Sconto = null;
end if;
end //
delimiter ;

create table Scelta
(
	Prenotazione char(6) not null,
    TipoStanza varchar(15) not null,
    NumOspitiStanza int not null,
    primary key(Prenotazione, TipoStanza, NumOspitiStanza),
    foreign key(Prenotazione) references Prenotazione(Codice)
		on update cascade
        on delete restrict,
	foreign key(TipoStanza, NumOspitiStanza) references TipologiaStanza(Tipo, NumOspiti)
		on update cascade
        on delete restrict
);
        

-- TABELLA UTENTE

CREATE TABLE utente (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE
);

-- TABELLA PORTAFOGLIO (UC3)

CREATE TABLE portafoglio (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    capitale REAL NOT NULL,
    utente_id INTEGER NOT NULL,
    FOREIGN KEY (utente_id) REFERENCES utente(id)
);

-- TABLLE ASSET (UC5)

CREATE TABLE asset (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    tipo TEXT NOT NULL,          -- azione, obbligazione, etf
    settore TEXT,
    durata INTEGER,
    is_storico BOOLEAN NOT NULL DEFAULT 0, -- 1 = Reale (Storico), 0 = Inventato
    isin TEXT,

    -- Vincolo sulla durata
    CONSTRAINT check_obbligazione_durata CHECK (
        (tipo = 'OBBLIGAZIONE' AND durata IS NOT NULL) 
        OR 
        (tipo != 'OBBLIGAZIONE' AND durata IS NULL)
    ),

    -- Vincolo sull'ISIN
    CONSTRAINT check_storico_isin CHECK (
        is_storico = 0 
        OR 
        (is_storico = 1 AND isin IS NOT NULL)
    )
);

-- Utente demo
INSERT INTO utente (username) VALUES ('demo');

-- === AZIONI (Rischio Alto / Durata NULL) ===
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Apple Inc.', 'AZIONE', 'Tecnologia', NULL, 0, 'US0378331005');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Microsoft Corp', 'AZIONE', 'Tecnologia', NULL, 0, 'US5949181045');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('NVIDIA Corp', 'AZIONE', 'Tecnologia', NULL, 0, 'US67066G1040');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Tesla Motors', 'AZIONE', 'Tecnologia', NULL, 0, 'US88160R1014');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Intesa Sanpaolo', 'AZIONE', 'Finanza', NULL, 0, 'IT0000072618');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('UniCredit', 'AZIONE', 'Finanza', NULL, 0, 'IT0004781412');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Pfizer Inc', 'AZIONE', 'Sanità', NULL, 0, 'US7170811035');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Moderna', 'AZIONE', 'Sanità', NULL, 0, 'US60770K1079');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Ferrari NV', 'AZIONE', 'Privato', NULL, 0, 'NL0011585146');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Enel SpA', 'AZIONE', 'Privato', NULL, 0, 'IT0003128367');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Amazon.com', 'AZIONE', 'Tecnologia', NULL, 0, 'US0231351067');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('JPMorgan Chase', 'AZIONE', 'Finanza', NULL, 0, 'US46625H1005');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Johnson & Johnson', 'AZIONE', 'Sanità', NULL, 0, 'US4781601046');

-- === OBBLIGAZIONI GOVERNATIVE (Rischio Basso / Durata Definita) ===
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('BTP Italia 2025', 'OBBLIGAZIONE', 'Governativo', 1, 0, 'IT0005410001');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('BTP Futura 2026', 'OBBLIGAZIONE', 'Governativo', 2, 0, 'IT0005420002');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Bund Germania 2027', 'OBBLIGAZIONE', 'Governativo', 3, 0, 'DE0001102301');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('US Treasury 5Y', 'OBBLIGAZIONE', 'Governativo', 5, 0, 'US912828Z948');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('BTP Lungo Termine 2034', 'OBBLIGAZIONE', 'Governativo', 10, 0, 'IT0005430003');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('OAT Francia 2030', 'OBBLIGAZIONE', 'Governativo', 6, 0, 'FR0011317783');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('BTP Green 2045', 'OBBLIGAZIONE', 'Governativo', 21, 0, 'IT0005440004');

-- === OBBLIGAZIONI CORPORATE (Rischio Medio-Basso / Durata Definita) ===
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Eni Bond 2026', 'OBBLIGAZIONE', 'Privato', 2, 0, 'XS1234567890');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Apple Bond 2028', 'OBBLIGAZIONE', 'Tecnologia', 4, 0, 'US037833AF82');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Goldman Sachs Bond', 'OBBLIGAZIONE', 'Finanza', 5, 0, 'US38141GVM31');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Coca Cola Bond 2030', 'OBBLIGAZIONE', 'Privato', 6, 0, 'US191216AZ43');

-- === ETF (Rischio Medio / Durata NULL) ===
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('iShares Core S&P 500', 'ETF', 'Tutti', NULL, 0, 'IE00B5BMR087');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Vanguard All-World', 'ETF', 'Tutti', NULL, 0, 'IE00B3RBWM25');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Invesco Solar Energy', 'ETF', 'Tecnologia', NULL, 0, 'US46138G7060');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('iShares Global Clean Energy', 'ETF', 'Privato', NULL, 0, 'IE00B1XNHC34');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Lyxor Robotics & AI', 'ETF', 'Tecnologia', NULL, 0, 'LU1838002480');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Xtrackers Health Care', 'ETF', 'Sanità', NULL, 0, 'IE00BM67HK77');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('WisdomTree Cloud Computing', 'ETF', 'Tecnologia', NULL, 0, 'IE00BJGWDN33');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Vanguard Bond Global', 'ETF', 'Governativo', NULL, 0, 'IE00B18GC888');

-- === ASSET "STORICI" (Per testare che non appaiano nella ricerca normale se filtri) ===
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Parmalat 2003 (Delisted)', 'AZIONE', 'Privato', NULL, 1, 'IT000000000X');
INSERT INTO asset (nome, tipo, settore, durata, is_storico, isin) VALUES ('Lehman Brothers Bond', 'OBBLIGAZIONE', 'Finanza', 0, 1, 'US5249081002');
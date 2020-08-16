DROP TABLE IF EXISTS EXCHANGE_RATE;

CREATE TABLE EXCHANGE_RATE
{
  ID INT AUTO_INCREMENT  PRIMARY KEY;
  CURRENCY_ORIGIN VARCHAR(3) NOT NULL,
  CURRENCY_DESTINY VARCHAR(3) NOT NULL,
  VALUE_CURRENCY DECIMAL(6,2) NOT NULL
}
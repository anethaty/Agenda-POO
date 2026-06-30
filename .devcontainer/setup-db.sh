#!/bin/bash

# Aguarda o PostgreSQL iniciar
until pg_isready -h localhost -p 5432; do
  echo "Aguardando o PostgreSQL ligar..."
  sleep 2
done

# Altera a senha do usuário postgres para 1106 (igual ao seu persistence.xml)
psql -h localhost -U postgres -c "ALTER USER postgres WITH PASSWORD '1106';"

# Cria o banco de dados Agenda-POO
psql -h localhost -U postgres -c "CREATE DATABASE \"Agenda-POO\";"

echo "Banco de dados Agenda-POO configurado com sucesso!"
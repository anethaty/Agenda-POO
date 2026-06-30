#!/bin/bash

# Garante que o serviço do PostgreSQL está rodando localmente
sudo service postgresql start

# Aguarda 3 segundos para o banco acordar completamente
sleep 3

# Altera a senha do usuário padrão 'postgres' para a do seu persistence.xml
sudo -u postgres psql -c "ALTER USER postgres WITH PASSWORD '1106';"

# Cria o banco de dados do seu projeto
sudo -u postgres psql -c "CREATE DATABASE \"Agenda-POO\";"

echo "Banco de dados Agenda-POO configurado nativamente com sucesso!"
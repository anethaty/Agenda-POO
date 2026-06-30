#!/bin/bash

# Inicia o PostgreSQL nativo
sudo service postgresql start
sleep 2

# Configura o usuário e banco de dados com a senha do projeto
sudo -u postgres psql -c "ALTER USER postgres WITH PASSWORD '1106';"
sudo -u postgres psql -c "CREATE DATABASE \"Agenda-POO\";"

# Cria a tela virtual em segundo plano na porta 5901 para o Swing rodar
Xvfb :1 -screen 0 1024x768x16 &
export DISPLAY=:1
fluxbox &
x11vnc -display :1 -nopw -listen localhost -xkb -forever &

echo "Ambiente pronto e configurado com sucesso!"
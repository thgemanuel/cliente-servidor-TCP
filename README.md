# Conexao Cliente Servidor TPC

Trabalho proposto na disciplina de Programação em redes, desenvolvido pelo aluno Thiago Emanuel Silva Antunes Lopes, estudante do curso de Bacharelado em Ciência da Computação do IFNMG Campus Montes Claros.

## 📋 Pré-requisitos

Para executar o projeto é nescessario ter instalado a NetBeans IDE.

## 🚀 Começando

O enunciado deste trabalho é:

Escreva um conjunto de programas cliente/servidor em Java que usa o protocolo TCP. Neste
programa o servidor sorteia uma palavra secreta e um número natural aleatório entre 1 e 1000
assim que estabelece a conexão com o cliente.

### Tarefa 1 - Servidor

O servidor deve enviar o número para o cliente, e o cliente deve retornar a fatoração em primos
deste número.

Caso 1: Caso a fatoração esteja correta o servidor deve enviar a palavra secreta ao cliente, e
encerrar a conexão.

Caso 2: Caso a fatoração esteja incorreta o servidor deve informar o cliente que a fatoração
está incorreta, e deve permitir que o cliente tente novamente sem encerrar a conexão.

Caso 3: O cliente pode informar que quer encerrar a conexão sem saber a palavra secreta
informando apenas o fator 0.

O servidor deve ser capaz de tratar diversas conexões simultâneas de clientes. A palavra e
número de cada conexão deverá ser sorteada independentemente.

### Tarefa 2 - Cliente Manual

Este cliente deve se conectar com o servidor desenvolvido na tarefa 1. Ele deve informar ao
usuário qual o número sorteado pelo servidor e permitir que o usuário tente efetuar a fatoração
por sua conta. Este cliente deve ser compatível com os três casos que o servidor trata.

### Tarefa 3 - Cliente Automático

Este cliente deve se conectar com o servidor desenvolvido na tarefa 1. Ele deve informar ao
usuário apenas a palavra secreta e encerrar a conexão

## 🛠️ Construído com

* [NetBeans](https://netbeans.apache.org/download/index.html) - NetBeans

## ✒️ Autores

* **Thiago Emanuel** - *Trabalho Inicial* - [thgemanuel](https://github.com/thgemanuel)

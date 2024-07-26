# Sistema Bancário em Java

Este projeto é um sistema bancário simples implementado em Java, utilizando conceitos de Programação Orientada a Objetos (POO). O sistema permite que os usuários realizem operações básicas como saque, depósito, transferência e consulta de extrato. 

## Estrutura do Projeto

O projeto está organizado em várias classes, cada uma com responsabilidades específicas:

- **Banco**: Gerencia uma lista de contas e clientes.
- **Cliente**: Representa um cliente do banco.
- **Conta**: Classe abstrata que define métodos para operações bancárias e mantém informações sobre a conta.
- **ContaCorrente** e **ContaPoupanca**: Subclasses de `Conta` que implementam tipos específicos de contas bancárias.
- **FuncaoConta**: Interface que define as operações básicas para uma conta bancária.
- **InterfaceUsuario**: Classe responsável pela interação com o usuário, permitindo que ele realize operações bancárias através do console.

## Funcionalidades

- **Criar Conta**: O usuário pode criar uma conta fornecendo seu nome.
- **Saque**: O usuário pode sacar um valor de sua conta, desde que tenha saldo suficiente.
- **Depósito**: O usuário pode depositar um valor em sua conta.
- **Transferência**: O usuário pode transferir um valor para outra conta, desde que tenha saldo suficiente e a conta de destino seja válida.
- **Extrato**: O usuário pode consultar o extrato de sua conta.
- **Proteção contra Transferência para a Própria Conta**: O sistema impede que um usuário transfira dinheiro para sua própria conta.

## Como Usar

### Compilação e Execução

1. Certifique-se de ter o Java Development Kit (JDK) instalado.
2. Compile o projeto utilizando o comando `javac`.
3. Execute a aplicação utilizando o comando `java`.

### Operações Disponíveis

Após iniciar o sistema, digite seu nome para criar uma conta. Utilize os números correspondentes para realizar as operações desejadas:

- `1`: Sacar
- `2`: Transferir
- `3`: Depositar
- `4`: Consultar Extrato
- `0`: Sair




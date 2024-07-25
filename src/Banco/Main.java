package Banco;

public class Main {
    public static void main(String[] args) throws Exception {
        Cliente listaClientes = new Cliente();
        listaClientes.setNome("Alex");

        Conta cc = new ContaCorrente(listaClientes);
        cc.depositar(1000);
        cc.sacar(500);

        Conta poupanca = new ContaPoupanca(listaClientes); 

        cc.imprimirExtrato();
        poupanca.imprimirExtrato();
    }
}

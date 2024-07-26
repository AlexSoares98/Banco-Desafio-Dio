public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // Usuários de exemplo e teste de transferência 
        Cliente cliente1 = new Cliente("Maria"); // Conta 1 - Agência 1 
        Cliente cliente2 = new Cliente("Arthur"); // Conta 2 - Agência 1 

        Conta conta1 = new ContaCorrente(cliente1); 
        Conta conta2 = new ContaPoupanca(cliente2);

        banco.adicionarCliente(cliente1);
        banco.adicionarCliente(cliente2);

        banco.adicionarConta(conta1);
        banco.adicionarConta(conta2);

        InterfaceUsuario interfaceUsuario = new InterfaceUsuario(banco) {};
        interfaceUsuario.iniciar();
    }
}

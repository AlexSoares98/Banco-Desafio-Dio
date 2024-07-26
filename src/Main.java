
public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // Usuários de exemplo
        Cliente cliente1 = new Cliente("Maria");
        Cliente cliente2 = new Cliente("Heitor");

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

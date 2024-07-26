public class Main {
    public static void main(String[] args) throws Exception {
        Banco banco = new Banco();
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario(banco) {};
        interfaceUsuario.iniciar();
    }
}

import java.util.HashSet;
import java.util.Set;


public class Banco {
    private Set<Conta> contas = new HashSet<>();
    private Set<Cliente> clientes = new HashSet<>();
 
    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Set<Conta> getContas() {
        return contas;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }
}

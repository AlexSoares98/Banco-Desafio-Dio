import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Conta> contas = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Conta> getContas() {
        return contas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}


import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private List<Cliente> clientes = new ArrayList<>();

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void removerCliente(String cpf) {
        clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }
}
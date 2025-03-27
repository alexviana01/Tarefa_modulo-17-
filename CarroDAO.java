
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {
    private List<Carro> carros = new ArrayList<>();

    public void adicionarCarro(Carro carro) {
        carros.add(carro);
    }

    public void removerCarro(String placa) {
        carros.removeIf(carro -> carro.getPlaca().equals(placa));
    }

    public List<Carro> listarCarros() {
        return carros;
    }
}
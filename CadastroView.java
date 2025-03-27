
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroView extends JFrame {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private CarroDAO carroDAO = new CarroDAO();
    
    private DefaultListModel<String> clienteListModel = new DefaultListModel<>();
    private DefaultListModel<String> carroListModel = new DefaultListModel<>();
    
    private JList<String> clienteList = new JList<>(clienteListModel);
    private JList<String> carroList = new JList<>(carroListModel);

    public CadastroView() {
        setTitle("Cadastro de Clientes e Carros");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        JPanel clientePanel = criarPainelClientes();
        JPanel carroPanel = criarPainelCarros();

        add(clientePanel);
        add(carroPanel);
    }

    private JPanel criarPainelClientes() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Clientes"));

        JTextField nomeField = new JTextField(10);
        JTextField cpfField = new JTextField(10);
        JButton addButton = new JButton("Adicionar");
        JButton removeButton = new JButton("Remover");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("CPF:"));
        inputPanel.add(cpfField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(clienteList), BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            if (!nome.isEmpty() && !cpf.isEmpty()) {
                Cliente cliente = new Cliente(nome, cpf);
                clienteDAO.adicionarCliente(cliente);
                clienteListModel.addElement(cliente.toString());
                nomeField.setText("");
                cpfField.setText("");
            }
        });

        removeButton.addActionListener(e -> {
            int index = clienteList.getSelectedIndex();
            if (index >= 0) {
                String selected = clienteListModel.get(index);
                String cpf = selected.split(" - CPF: ")[1];
                clienteDAO.removerCliente(cpf);
                clienteListModel.remove(index);
            }
        });

        return panel;
    }

    private JPanel criarPainelCarros() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Carros"));

        JTextField modeloField = new JTextField(10);
        JTextField placaField = new JTextField(10);
        JButton addButton = new JButton("Adicionar");
        JButton removeButton = new JButton("Remover");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Modelo:"));
        inputPanel.add(modeloField);
        inputPanel.add(new JLabel("Placa:"));
        inputPanel.add(placaField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(carroList), BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            String modelo = modeloField.getText();
            String placa = placaField.getText();
            if (!modelo.isEmpty() && !placa.isEmpty()) {
                Carro carro = new Carro(modelo, placa);
                carroDAO.adicionarCarro(carro);
                carroListModel.addElement(carro.toString());
                modeloField.setText("");
                placaField.setText("");
            }
        });

        removeButton.addActionListener(e -> {
            int index = carroList.getSelectedIndex();
            if (index >= 0) {
                String selected = carroListModel.get(index);
                String placa = selected.split(" - Placa: ")[1];
                carroDAO.removerCarro(placa);
                carroListModel.remove(index);
            }
        });

        return panel;
    }
}
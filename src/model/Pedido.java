package model;

public class Pedido {
    private int id;
    private String dataRequisicao;
    private String dataEntrega;
    private String itens; // pode ser uma string separada por vírgulas pra simplificar por enquanto
    private int clienteId;

    public Pedido() {}

    public Pedido(int id, String dataRequisicao, String dataEntrega, String itens, int clienteId) {
        this.id = id;
        this.dataRequisicao = dataRequisicao;
        this.dataEntrega = dataEntrega;
        this.itens = itens;
        this.clienteId = clienteId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDataRequisicao() { return dataRequisicao; }
    public void setDataRequisicao(String dataRequisicao) { this.dataRequisicao = dataRequisicao; }

    public String getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(String dataEntrega) { this.dataEntrega = dataEntrega; }

    public String getItens() { return itens; }
    public void setItens(String itens) { this.itens = itens; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
}

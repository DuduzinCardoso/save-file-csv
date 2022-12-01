public class Brinquedo {

    private int id;
    private String nome;
    private Double valorUnitario;
    private Integer quantidadeEstoque;
    private String descricao;

    public Brinquedo(int id, String nome, Double valorUnitario, Integer quantidadeEstoque, String descricao) {
        this.id = id;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
        this.descricao = descricao;
    }

    public double precoTotalEstoque(){
        double valorTotal;
        return valorTotal = (double) quantidadeEstoque * valorUnitario;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Brinquedo{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", valorUnitario=").append(valorUnitario);
        sb.append(", quantidadeEstoque=").append(quantidadeEstoque);
        sb.append(", descricao='").append(descricao).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

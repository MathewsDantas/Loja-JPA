package modelo;

import javax.persistence.*;


@Entity
public class VendaItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long qtd;

    @Column(nullable = false)
    private double preco;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Pedido pedido;

    public VendaItem() {
    }

    public VendaItem(long qtd, Produto produto, Pedido pedido) {
        this.qtd = qtd;
        this.setProduto(produto);
        this.pedido = pedido;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQtd() {
        return qtd;
    }

    public void setQtd(long qtd) {
        this.qtd = qtd;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setProduto(Produto produto) {
        if ( produto != null){
            this.preco = produto.getPreco();
        }
        this.produto = produto;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return "VendaItem{" +
                "id=" + id +
                ", qtd=" + qtd +
                ", preco=" + preco +
                ", produto=" + produto +
                ", pedido=" + pedido +
                '}';
    }
}

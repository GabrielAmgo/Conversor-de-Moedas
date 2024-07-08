import java.math.BigDecimal;

public class Moeda {

    String codigo;
    int quantidade;

    public Moeda(String codigo, int quantidade) {
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Moeda{" +
                "codigo= " + codigo +
                "quantidade= " + quantidade +
                '}';
    }
}



package Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tikects")
public class Tikects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario")
    private Usuarios usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nombre_atraccion", referencedColumnName = "nombre")
    private Atracciones nombreAtraccion;

    @Column(name = "precio_entrada")
    private BigDecimal precioEntrada;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Atracciones getNombreAtraccion() {
        return nombreAtraccion;
    }

    public void setNombreAtraccion(Atracciones nombreAtraccion) {
        this.nombreAtraccion = nombreAtraccion;
    }

    public BigDecimal getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(BigDecimal precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    @Override
    public String toString() {
        return "Tikects{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", nombreAtraccion=" + nombreAtraccion +
                ", precioEntrada=" + precioEntrada +
                '}';
    }
}
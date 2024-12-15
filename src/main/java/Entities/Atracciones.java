package Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "atracciones")
public class Atracciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    @OneToMany(mappedBy = "nombreAtraccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Entities.Tikects> tikects = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Set<Entities.Tikects> getTikects() {
        return tikects;
    }

    public void setTikects(Set<Entities.Tikects> tikects) {
        this.tikects = tikects;
    }



}
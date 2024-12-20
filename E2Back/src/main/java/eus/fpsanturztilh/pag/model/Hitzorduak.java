package eus.fpsanturztilh.pag.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.*;
import java.time.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "hitzorduak")
public class Hitzorduak implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer eserlekua;

    @Column(nullable = false)
    private LocalDate data;

    @Column(name = "hasiera_ordua", nullable = false)
    private LocalTime hasieraOrdua;

    @Column(name = "amaiera_ordua", nullable = false)
    private LocalTime amaieraOrdua;

    @Column(name = "hasiera_ordua_erreala")
    private LocalTime hasieraOrduaErreala;

    @Column(name = "amaiera_ordua_erreala")
    private LocalTime amaieraOrduaErreala;

    @Column(nullable = false, length = 100)
    private String izena;

    @Column(length = 9)
    private String telefonoa;

    @Column(length = 250)
    private String deskribapena;

    @Column(nullable = false)
    private Character etxekoa;

    @Column(name = "prezio_totala", precision = 10, scale = 2)
    private BigDecimal prezioTotala;

    @Column(name = "ordaindutakoa", precision = 10, scale = 2)
    private BigDecimal ordaindutakoa;

    @ManyToOne
    @JoinColumn(name = "id_langilea")
    private Langileak langilea;

    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;

}


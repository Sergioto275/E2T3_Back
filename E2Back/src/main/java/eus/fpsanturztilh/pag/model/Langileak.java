package eus.fpsanturztilh.pag.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "langileak")
public class Langileak implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String izena;

    @Column(nullable = false)
    private String abizenak;

    @ManyToOne
    @JoinColumn(name = "kodea", nullable = false)
    private Taldeak taldea;

    @OneToMany(mappedBy = "langile")
    private List<Produktu_mugimenduak> mugimenduak;

    @OneToMany(mappedBy = "langileak", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Txandak> txandak;

    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;
}

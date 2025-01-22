package eus.fpsanturztilh.pag.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "kolore_historialak")
public class Kolore_historiala implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   // @OneToOne
   // @JoinColumn(name = "id_bezeroa")
   // private Bezeroak bezeroa;

    //@OneToOne
    //@JoinColumn(name = "id_produktua")
    //private Produktuak produktua;
    
    @Column(nullable = false)
    private LocalTime data;
    
    @Column(nullable = false)
    private double kantitatea;
    
    @Column(nullable = false)
    private double bolumena;
    
    private String oharrak;

    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;

}


package eus.fpsanturztilh.pag.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "materialak")
public class Materialak implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String etiketa;
    
    @Column(nullable = false)
    private String izena;

    //@ManyToOne
    //@JoinColumn(name = "material_kategoria", nullable = false)
    //private Material_kategoria materialKategoria;

    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;
}

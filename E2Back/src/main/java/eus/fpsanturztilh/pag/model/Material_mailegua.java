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
@Table(name = "material_maileguaK")
public class Material_mailegua implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   // @OneToOne
   // @JoinColumn(name = "id_material")
   // private Materialak materiala;

    //@OneToOne
    //@JoinColumn(name = "id_langilea")
    //private Langileak langilea;
    
    @Column(nullable = false)
    private LocalTime hasiera_data;
    
    @Column(nullable = false)
    private LocalTime amaiera_data;

    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;

}


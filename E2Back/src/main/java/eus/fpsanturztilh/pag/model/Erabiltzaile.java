package eus.fpsanturztilh.pag.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "erabiltzaileak")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Erabiltzaile {

    @Id
    private String username;
    private String pasahitza;
    private String rola;
    
    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;
}

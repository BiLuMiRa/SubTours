package main.java.com.subtours.entity;

import java.time.LocalTime;
import javax.annotation.processing.Generated;

@AllArgsConstructor
@Data
@Entity
@Table(name = "planoSeguranca")
public class PlanoSeguranca {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "codPlanSeg")
    private short id;

    @Column(name = "procedsEvac", nullable = false, lenght = 300)
    private String procedsEvacuacao;

    @Column(name = "pontoEnc", nullable = false, lenght = 50)
    private String pontoEncontro;

    @Column(name = "tempoSemComunic", nullable = false)
    private LocalTime tempoSemComunic;

    @Column(name = "telefoneEmerg", nullable = false, lenght = 11)
    private String telefoneEmerg;

    @Column(name = "precisaMedico")
    private boolean precisaMedico;

    @Column(name = "mapa", columnDefinition = "bytea")
    private byte[] mapa;
}

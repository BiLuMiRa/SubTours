import java.math.BigDecimal;
import java.time.LocalDate;

import com.subtours.enums.datumGeodesico;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
@Entity 
@Table(name = "caverna")
public class Caverna {
    @Id 
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "num_caverna")
    private short numCaverna;

    @Column(name = "nome_caverna", nullable = false)
    private String nomeCaverna;

    @Column(name = "cod_cadAmbiental", nullable = false)
    private String codCadAmbiental;

    @Column(name = "municipio", nullable = false)
    private String municipio;

    @Column(name = "uf", length = 2, nullable = false)
    private String uf;

    @Column(name = "ultima_insp", nullable = false)
    private LocalDate ultimaInsp;

    @Column(name = "ind_acesso", nullable = false)
    private Boolean indAcesso;

    @Column(name = "extensao", nullable = false)
    private BigDecimal extensao;

    @Embedded 
    private Localizacao localizacao;

    @Embeddable 
    public static class Localizacao {
        @Column(name = "latitude", nullable = false)
        private BigDecimal latitude;

        @Column(name = "longitude", nullable = false)
        private BigDecimal longitude;

        @Enumerated(EnumType.STRING)
        @Column(name = "datum_geodesico", nullable = false)
        public datumGeodesico datum;

    }
}
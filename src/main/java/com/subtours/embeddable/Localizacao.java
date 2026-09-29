package com.subtours.embeddable;

import java.math.BigDecimal;
import com.subtours.enums.datumGeodesicoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@Embeddable 
public class Localizacao {
    
        @Column(name = "latitude", nullable = false, precision = 9, scale = 6)
        private BigDecimal latitude;

        @Column(name = "longitude", nullable = false, precision = 10, scale = 6)
        private BigDecimal longitude;

        @Enumerated(EnumType.STRING)
        @Column(name = "datum_geodesico", nullable = false)
        public datumGeodesicoEnum datum;
}

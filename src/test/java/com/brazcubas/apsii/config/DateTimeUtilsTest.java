package com.brazcubas.apsii.config;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTimeUtilsTest {

    @Test
    @DisplayName("Deve converter com sucesso uma data no formato ISO-8601 com sufixo Z")
    void parseUtc_deveConverterFormatoIsoComZ() {
        // Arrange (Preparação)
        String inputIso = "2026-10-22T18:00:00Z";

        // Act (Execução)
        Instant resultado = DateTimeUtils.parseUtc(inputIso);

        // Assert (Verificação)
        assertNotNull(resultado, "O instante retornado não deve ser nulo");
        assertEquals("2026-10-22T18:00:00Z", resultado.toString(), "A data convertida deve corresponder ao UTC esperado");
    }

    @Test
    @DisplayName("Deve lançar exceção ao receber uma string de data malformatada ou inválida")
    void parseUtc_deveLancarExcecaoQuandoEntradaInvalida() {
        // Arrange (Preparação)
        String entradaInvalida = "data-invalida-2026";

        // Act & Assert (Execução e Verificação)
        assertThrows(
            java.time.format.DateTimeParseException.class, 
            () -> DateTimeUtils.parseUtc(entradaInvalida),
            "Esperado que o método lance DateTimeParseException para entradas inválidas"
        );
    }
}
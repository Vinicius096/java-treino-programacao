package br.com.bank.service;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SistemaBancarioTest {

    @InjectMocks
    private SistemaBancario sistemaBancario;

    @Mock
    private Bacen bacen;

	@Test
	public void validar_metodo_cadastrarBanco() {
        Banco itau = new Banco("Itau");
        sistemaBancario.registrarBanco(itau);
        verify(bacen, times(1)).cadastrarBanco(itau);
	}

}
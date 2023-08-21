package br.com.bank.service;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SistemaBancarioTest {

    @InjectMocks
    private SistemaBancario sistemaBancario;

    @Mock
    private Bacen bacen;

    //verify
	@Test
	public void validar_metodo_cadastrarBanco() {
        Banco itau = new Banco("Itau");
        sistemaBancario.registrarBanco(itau);
        verify(bacen, times(1)).cadastrarBanco(itau);
	}

    //mock
    @Test
    public void nao_deve_cadastrar_se_houver_problema_de_comunicacao() {
        Banco bb = new Banco("bb");
        Mockito.doThrow(BancoNaoCadastradoException.class)
            .when(bacen)
            .cadastrarBanco(bb);

        assertThrows(BancoNaoCadastradoException.class, () -> sistemaBancario.registrarBanco(bb));
    }

    //mock
    @Test
    public void deve_cadastrar_banco_com_mock() {
        Banco bb = new Banco("bb");
        Mockito.doReturn(1l).when(bacen).cadastrarBanco(bb);

        long numeroDoRegistro = sistemaBancario.registrarBanco(bb);
        assertEquals(1, numeroDoRegistro);
    }

    //fake
    @Test
    public void deve_cadastrar_banco_com_fake() {
        sistemaBancario = new SistemaBancario(new BacenFake());

        Banco itau = new Banco("itau");
        Banco bradesco = new Banco("bradesco");
        
        long numeroDoRegistro = sistemaBancario.registrarBanco(itau);
        assertEquals(1, numeroDoRegistro);

        numeroDoRegistro = sistemaBancario.registrarBanco(bradesco);
        assertEquals(2, numeroDoRegistro);

    }

    //stub
    @Test
    public void deve_cadastrar_banco_com_stub() {
        sistemaBancario = new SistemaBancario(new BacenStub());
        Banco itau = new Banco("itau");
        long numeroDoRegistro = sistemaBancario.registrarBanco(itau);
        assertEquals(1, numeroDoRegistro);
    }

}
package br.com.bank.model;

import java.util.List;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BancoTest {
        
    private Banco Itau = new Banco("Itau");
    private String cpf = "13302237642";
    private Conta conta_cliente = new Conta(cpf);

    @BeforeEach
    public void init(){
        Itau.adicionarConta(conta_cliente);
    }

    @Test
    public void adiciona_conta() {
        Assertions.assertEquals(cpf, conta_cliente.getCpf());
    }

    @Test
    public void pesquisa_conta_do_cliente() {
        Assertions.assertEquals(conta_cliente, Itau.pesquisarContaDoCliente(cpf));
    }

    @Test
    public void lista_conta_alta_renda(){
        Assertions.assertEquals(0, Itau.listarContasAltaRenda().size());
    }

    
    
}

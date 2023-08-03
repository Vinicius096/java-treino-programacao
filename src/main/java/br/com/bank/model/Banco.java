package br.com.bank.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Banco {

    private String nome;

    public Banco(String nome) {
        this.nome = nome;
    }

    private List<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }
    public Conta pesquisarContaDoCliente(String cpf) {
        Conta c = null;
        List<Conta> f = filtrarContas(ctn -> ctn.getCpf() == cpf);
        if(f.size() != 0) {
            c = f.get(0);
        }
        return c;
    }

    public List<Conta> listarContasAltaRenda() {
        return filtrarContas(c -> c.getSaldo() >= 10000);
    }

    private List<Conta> filtrarContas(Predicate<Conta> filtro) {
        return contas.stream().filter(filtro).collect(Collectors.toList());
    }
}

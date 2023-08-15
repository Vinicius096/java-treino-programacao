package br.com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {


    private Path diretorio;

    private Path arquivo;

    private CaminhoArquivo(Path diretorio, Path arquivo) {
        super();
        this.diretorio = diretorio;
        this.arquivo = arquivo;
    }

    public Path getDiretorio() {
        return diretorio;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public static CaminhoArquivo getInstance(Integer id) {
        String diretorio = null;
        String arquivo = null;

        try {
            Integer numeroDiretorio = (int) Math.ceil((double)id / 1000);
            diretorio = "/tmp/" + numeroDiretorio + "/";
            arquivo = diretorio + id;
        } catch (Exception e) {
            diretorio = "/tmp";
            arquivo = "";
        }
        
        return new CaminhoArquivo(Paths.get(diretorio), Paths.get(arquivo));

    }

}

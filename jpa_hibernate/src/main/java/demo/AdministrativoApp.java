package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdministrativoApp {

    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        // 1) Criando um produto
        produtoModel.create(p1);

        //2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        p1.setPreco(305.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        produtoModel.update(p1, p1.getId());

        System.out.println("Produto atualizado : " + p1);

        System.out.println("Buscando produto: " + produtoModel.findById(p1.getId()));

        produtoModel.delete(p1);
        System.out.println("Excluindo produto: " + p1);

        System.out.println("Produtos encontrados : " +  produtoModel.findAll());


        PessoaModel pessoaModel = new PessoaModel();

        Pessoa pess1 = new Pessoa();
        pess1.setNome("TV");
        pess1.setCpf("461.318.152-55");
        pess1.setEmail("pessoa_da_silva@email.com");
        pess1.setNome("Pessoa da Silva");
        pess1.setDataNascimento(new Date(2001, Calendar.SEPTEMBER, 11));
        pess1.setIdade(23);

        // 1) Criando um produto
        pessoaModel.create(pess1);

        //2) Buscando todos os produtos na base de dados
        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontradas : " + pessoas.size());

        pess1.setNome("Person Johnson");

        pessoaModel.update(pess1, pess1.getId());

        System.out.println("Pessoa atualizada : " + pess1);

        System.out.println("Buscando pessoa: " + pessoaModel.findById(pess1.getId()));

        pessoaModel.delete(pess1);
        System.out.println("Excluindo pessoa: " + pess1);

        System.out.println("Pessoas encontradas : " +  pessoaModel.findAll());

    }
}
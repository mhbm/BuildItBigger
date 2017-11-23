package com.udacity.gradle.builditbigger;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lsitec101.macedo on 20/11/17.
 */

public class JokeFactory {

    private ArrayList<String> jokes = new ArrayList<>();

    public JokeFactory() {
        jokes.add("Do que o diabo morreu? De diabetes");
        jokes.add("O que fala o livro de Matemática para o livro de História? Não me venha com história que eu já estou cheio de problemas");
        jokes.add("Porque o louco toma banho com o chuveiro desligado? Por que ele comprou um shampoo para cabelos secos");
        jokes.add("Porque a roda de trem é de ferro e o não de borracha? Por que se fosse de borracha apagaria a linha");
        jokes.add("Qual estado do Brasil que queria ser carro? Sergipe");
        jokes.add("Qual o único pais que não tem padaria? O Jápão");
    }

    public String returnOneJoke() {
        int position  = new Random().nextInt(jokes.size() - 1);
        return jokes.get(position);
    }

    public ArrayList<String> returnAllJokes() {
        return jokes;
    }

}

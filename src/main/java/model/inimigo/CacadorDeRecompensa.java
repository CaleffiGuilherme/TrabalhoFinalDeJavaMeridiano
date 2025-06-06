package model.inimigo;

import model.Atacavel;

public class CacadorDeRecompensa extends Inimigo{
    public CacadorDeRecompensa(String nome) {
        super(nome, 80, 80, 20, 10, 75, "Revólver Lemat");
        this.habilidades[0] =  "Mira Calculada";
        this.habilidades[1] = "Posicionamento Tático";
        this.habilidades[2] = "Foco no Alvo";
        this.habilidadeEspecial = "Caçada Mortal";
    }

    @Override
    public void usarHabilidade1 (Atacavel alvo) {
        System.out.println(this.nome + " usa " + habilidades[0] + "!");
        this.precisao += 15; // por 2 turnos Bernardo
    }

    @Override
    public void usarHabilidade2 (Atacavel alvo) {
        System.out.println(this.nome + " fica em um " + habilidades[1] + "!");
        this.defesa += 12; // por 3 turno bernardo
    }

    @Override
    public void usarHabilidade3 (Atacavel alvo) {
        System.out.println(this.nome + " " + habilidades[2]);
        this.ataque += 10;
    }

    @Override
    public void usarHabilidadeEspecial(Atacavel alvo) {
        System.out.println(this.nome + " começa sua  " + habilidadeEspecial);
        this.ataque += 20;
        this.precisao += 25;
    }

}

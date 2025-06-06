package model.inimigo;

import model.Atacavel;

public class Bebado extends Inimigo{
    public Bebado(String nome) {
        super(nome, 80, 80, 20, 10, 75, "Revólver Lemat");
        this.habilidades[0] =  "Coragem Líquida";
        this.habilidades[1] = "Sorte de Bêbado";
        this.habilidades[2] = "Tônus Etílico";
        this.habilidadeEspecial = "Bêbado Imortal";
    }

    @Override
    public void usarHabilidade1 (Atacavel alvo) {
        System.out.println(this.nome + " usa " + habilidades[0] + "!");
        this.ataque += 12; // por 2 turnos Bernardo
    }

    @Override
    public void usarHabilidade2 (Atacavel alvo) {
        System.out.println(this.nome + " está com " + habilidades[1] + "!");
        this.precisao += 20; // por 1 turno bernardo
    }

    @Override
    public void usarHabilidade3 (Atacavel alvo) {
        System.out.println(this.nome + " bebe " + habilidades[2]);
        this.defesa += 8;
    }

    @Override
    public void usarHabilidadeEspecial(Atacavel alvo) {
        System.out.println(this.nome + "Se torna o " + habilidadeEspecial);
        this.ataque += 10;
        this.precisao += 15;
        this.defesa += 10;
    }

}

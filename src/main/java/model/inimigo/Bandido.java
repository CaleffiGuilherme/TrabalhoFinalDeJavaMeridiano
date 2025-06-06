package model.inimigo;

import model.Atacavel;

public class Bandido extends Inimigo{
    public Bandido(String nome) {
        super(nome, 80, 80, 20, 10, 75, "Revólver Lemat");
        this.habilidades[0] =  "Faca de Arremesso";
        this.habilidades[1] = "Shotgun";
        this.habilidades[2] = "Tiro Rápido";
        this.habilidadeEspecial = "Poeira";
    }

    @Override
    public void usarHabilidade1 (Atacavel alvo) {
        System.out.println(this.nome + " dispara " + habilidades[0] + "!");
        int dano = this.ataque + 10 - alvo.getDefesa();
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidade2 (Atacavel alvo) {
        System.out.println(this.nome + " Atira três vezes com " + habilidades[1] + "!");
        for (int i = 0; i < 3; i++) {
            int dano = this.ataque + 10 - alvo.getDefesa();
            alvo.receberDano(dano);
        }
    }

    @Override
    public void usarHabilidade3 (Atacavel alvo) {
        System.out.println(this.nome + " dispara " + habilidades[2] + " com velocidade mortal!");
        int precisao = this.precisao * 2 - alvo.getDefesa();
        this.precisao = precisao;
    }

    @Override
    public void usarHabilidadeEspecial(Atacavel alvo) {
        System.out.println(this.nome + "Se enfurece!");
        int dano = this.ataque * 2 + alvo.getDefesa();
        alvo.receberDano(dano);
    }

}

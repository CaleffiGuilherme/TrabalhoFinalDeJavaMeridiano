package model.inimigo;

import model.Atacavel;

public class Bandido extends Inimigo {

    public Bandido(String nome) {
        super(nome, 80, 80, 20, 10, 75, "Revólver");
        this.habilidades[0] = "Faca de Arremesso";
        this.habilidades[1] = "Shotgun";
        this.habilidades[2] = "Tiro Rápido";
    }

    @Override
    public void usarHabilidade1(Atacavel alvo) {
        System.out.println(this.nome + " dispara " + habilidades[0] + "!");
        int dano = this.ataque + 10 - alvo.getDefesa(); // habilidade com dano extra
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidade2(Atacavel alvo) {
        System.out.println(this.nome + " Atira com sua " + habilidades[1] + "!");
        for (int i = 0; i < 3; i++) { // atira três vezes
            int dano = this.ataque - alvo.getDefesa();
            if (dano < 0) dano = 0;
            alvo.receberDano(dano);
        }
    }

    @Override
    public void usarHabilidade3(Atacavel alvo) {
        System.out.println(this.nome + " dispara " + habilidades[2] + " com velocidade mortal!");
        int dano = this.ataque * 2 - alvo.getDefesa(); // dano dobrado
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
    }
}

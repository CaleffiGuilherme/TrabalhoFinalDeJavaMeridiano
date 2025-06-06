package model.inimigo.boss;

import model.Atacavel;
import model.inimigo.Inimigo;

public class Xerife extends Inimigo {

    public Xerife(String nome) {
        super(nome, 80, 80, 20, 10, 75, "Revólver");
        this.habilidades[0] = "Tiro Certeiro";
        this.habilidades[1] = "Rajada de Balas";
        this.habilidades[2] = "Olho da águia";
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
        System.out.println(this.nome + " executa " + habilidades[1] + "!");
        for (int i = 0; i < 3; i++) { // atira três vezes
            int dano = this.ataque - alvo.getDefesa();
            if (dano < 0) dano = 0;
            alvo.receberDano(dano);
        }
    }

    @Override
    public void usarHabilidade3(Atacavel alvo) {
        System.out.println(this.nome + " dispara " + habilidades[2] + " com precisão mortal!");
        int dano = this.ataque * 2 - alvo.getDefesa(); // dano dobrado
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidadeEspecial(Atacavel alvo) {
        System.out.println(this.nome + " ativa 'Olho da Morte'! Sua precisão aumenta temporariamente!");
        this.precisao += 20;
        if (this.precisao > 100) this.precisao = 100;
    }
}

package model.player;

import model.Atacavel;
import utils.GlobalLogger;
import java.util.logging.Logger;

public class ForaDaLei extends Personagem {

    private static final Logger logger = GlobalLogger.getLogger();

    public ForaDaLei(String nome) {
        super(nome);
        this.vidaMaxima = 90;
        this.vidaAtual = 90;
        this.ataque = 35;
        this.defesa = 5;
        this.esquiva = 25;
        this.precisao = 80;
        this.arma = "Espingarda";

        this.habilidades[0] = "Disparo Selvagem";
        this.habilidades[1] = "Explosão de Chumbo";
        this.habilidades[2] = "Tiro Mortal";

        logger.info("[ForaDaLei] Criado: " + nome);
    }

    @Override
    public void atacar(Atacavel inimigo) {
        logger.info(this.nome + " ataca " + inimigo.getNome() + " com " + this.arma);
        System.out.println(this.nome + " tenta atacar " + inimigo.getNome() + " com " + this.arma);

        double chance = Math.random() * 100;

        if (chance <= this.precisao) {
            int dano = this.ataque;
            if (Math.random() < 0.25) {  // 25% de chance de crítico
                dano *= 2;
                System.out.println("💥 ATAQUE CRÍTICO! 💥");
            }
            dano -= inimigo.getDefesa();
            if (dano < 0) dano = 0;
            inimigo.receberDano(dano);
            System.out.println(this.nome + " acertou causando " + dano + " de dano!");
        } else {
            System.out.println(this.nome + " errou o ataque!");
        }
    }

    @Override
    public void usarHabilidade1(Atacavel inimigo) {
        System.out.println(this.nome + " usa " + habilidades[0] + "!");
        int dano = this.ataque + 10 - inimigo.getDefesa(); // dano extra
        if (dano < 0) dano = 0;
        inimigo.receberDano(dano);
    }

    @Override
    public void usarHabilidade2(Atacavel inimigo) {
        System.out.println(this.nome + " usa " + habilidades[1] + "!");
        for (int i = 0; i < 3; i++) { // 3 tiros
            int dano = this.ataque - 5 - inimigo.getDefesa(); // um pouco mais fraco por tiro
            if (dano < 0) dano = 0;
            inimigo.receberDano(dano);
        }
    }

    @Override
    public void usarHabilidade3(Atacavel inimigo) {
        System.out.println(this.nome + " usa " + habilidades[2] + "!");
        int dano = (this.ataque * 2) - inimigo.getDefesa(); // dano dobrado
        if (dano < 0) dano = 0;
        inimigo.receberDano(dano);
    }

    @Override
    public void usarHabilidadeEspecial(Atacavel inimigo) {
        System.out.println(this.nome + " ativa 'Olho da Morte'! Sua precisão aumenta temporariamente!");
        this.precisao += 20;
        if (this.precisao > 100) this.precisao = 100;
    }
}
